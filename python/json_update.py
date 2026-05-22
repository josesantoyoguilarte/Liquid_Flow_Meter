"""CLI helper to update a numeric field in data.json.

Example:
    python3 json_update.py --field totalamount --value 10000
"""
from __future__ import annotations

import argparse
import json
from pathlib import Path


def main() -> None:
    parser = argparse.ArgumentParser(description="Update a field in data.json")
    parser.add_argument("--path", default="data.json", type=Path)
    parser.add_argument("--field", required=True)
    parser.add_argument("--value", required=True)
    args = parser.parse_args()

    data = json.loads(args.path.read_text(encoding="utf-8")) if args.path.exists() else {}
    data[args.field] = args.value
    args.path.write_text(json.dumps(data, indent=2), encoding="utf-8")
    print(f"{args.path}: {args.field} = {args.value}")


if __name__ == "__main__":
    main()
