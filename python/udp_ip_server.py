"""UDP dispenser server for the Raspberry Pi.

Listens on one UDP port per drink type, opens a control valve via GPIO for the
amount of milliliters requested by the client, persists the running total in a
JSON file and replies with a short acknowledgement.

Run on a Pi:
    python3 udp_ip_server.py --host 0.0.0.0 \
        --alcohol-port 42000 --beverage-port 43000

Off-Pi (no RPi.GPIO available) a fake GPIO is used so the server still runs for
development and testing.
"""
from __future__ import annotations

import argparse
import json
import logging
import socket
import threading
import time
from dataclasses import dataclass
from pathlib import Path

try:  # pragma: no cover - hardware-only import
    import RPi.GPIO as GPIO  # type: ignore[import-not-found]
except ImportError:  # pragma: no cover
    class _FakeGPIO:
        BCM = OUT = 0

        def setmode(self, *_):
            pass

        def setup(self, *_, **__):
            pass

        def output(self, pin, value):
            logging.debug("GPIO stub: pin=%s value=%s", pin, value)

        def cleanup(self):
            pass

    GPIO = _FakeGPIO()  # type: ignore[assignment]

LOG = logging.getLogger("dispenser")

DEFAULT_LIMIT_ML = 100_000  # 1 L * 100 -> keep original "1 L" semantics in ml
DATA_FILE = Path("data.json")


@dataclass
class DispenserConfig:
    name: str
    port: int
    pin: int
    seconds_per_ml: float  # how long the valve stays open per ml


def load_totals() -> dict:
    if DATA_FILE.exists():
        return json.loads(DATA_FILE.read_text(encoding="utf-8"))
    return {"total_amount_dispensed": 0, "limit": DEFAULT_LIMIT_ML, "remaining": DEFAULT_LIMIT_ML}


def save_totals(totals: dict) -> None:
    DATA_FILE.write_text(json.dumps(totals, indent=2), encoding="utf-8")


_totals_lock = threading.Lock()


def dispense(cfg: DispenserConfig, amount_ml: float) -> None:
    open_seconds = amount_ml * cfg.seconds_per_ml
    LOG.info("[%s] opening valve on pin %s for %.2fs (%s ml)",
             cfg.name, cfg.pin, open_seconds, amount_ml)
    GPIO.output(cfg.pin, 1)
    try:
        time.sleep(open_seconds)
    finally:
        GPIO.output(cfg.pin, 0)
    LOG.info("[%s] dispensed %s ml", cfg.name, amount_ml)


def serve(cfg: DispenserConfig, host: str) -> None:
    GPIO.setup(cfg.pin, GPIO.OUT)
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as sock:
        sock.bind((host, cfg.port))
        LOG.info("[%s] listening on %s:%s", cfg.name, host or "0.0.0.0", cfg.port)
        while True:
            data, addr = sock.recvfrom(1024)
            try:
                amount = float(data.decode("utf-8").strip())
            except ValueError:
                LOG.warning("[%s] ignoring non-numeric payload %r from %s", cfg.name, data, addr)
                sock.sendto(b"ERROR bad payload", addr)
                continue

            with _totals_lock:
                totals = load_totals()
                if totals["total_amount_dispensed"] + amount > totals["limit"]:
                    LOG.warning("[%s] request of %s ml from %s exceeds limit", cfg.name, amount, addr)
                    sock.sendto(f"ERROR limit exceeded ({totals['remaining']} ml left)".encode("utf-8"), addr)
                    continue
                totals["total_amount_dispensed"] += amount
                totals["remaining"] = totals["limit"] - totals["total_amount_dispensed"]
                save_totals(totals)

            sock.sendto(f"Dispensing {amount} ml of {cfg.name}".encode("utf-8"), addr)
            LOG.info("[%s] request %s ml from %s", cfg.name, amount, addr)
            dispense(cfg, amount)


def main() -> None:
    parser = argparse.ArgumentParser(description="UDP liquid dispenser server")
    parser.add_argument("--host", default="", help="Bind address (default: all interfaces)")
    parser.add_argument("--alcohol-port", type=int, default=42_000)
    parser.add_argument("--beverage-port", type=int, default=43_000)
    parser.add_argument("--alcohol-pin", type=int, default=17)
    parser.add_argument("--beverage-pin", type=int, default=27)
    parser.add_argument("--alcohol-sec-per-ml", type=float, default=0.24)
    parser.add_argument("--beverage-sec-per-ml", type=float, default=0.29)
    parser.add_argument("--log-level", default="INFO")
    args = parser.parse_args()

    logging.basicConfig(level=args.log_level, format="%(asctime)s %(levelname)s %(message)s")

    GPIO.setmode(GPIO.BCM)
    dispensers = [
        DispenserConfig("ALCOHOL", args.alcohol_port, args.alcohol_pin, args.alcohol_sec_per_ml),
        DispenserConfig("BEVERAGE", args.beverage_port, args.beverage_pin, args.beverage_sec_per_ml),
    ]

    threads = [threading.Thread(target=serve, args=(cfg, args.host), name=cfg.name, daemon=True)
               for cfg in dispensers]
    for t in threads:
        t.start()
    try:
        for t in threads:
            t.join()
    except KeyboardInterrupt:
        LOG.info("shutting down")
    finally:
        GPIO.cleanup()


if __name__ == "__main__":
    main()
