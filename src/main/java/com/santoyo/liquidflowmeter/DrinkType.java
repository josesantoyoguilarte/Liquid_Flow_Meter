package com.santoyo.liquidflowmeter;

/**
 * Drink type the user can request from the dispenser.
 * Each value carries the UDP port the matching server listens on.
 */
public enum DrinkType {
    ALCOHOL(42_000),
    BEVERAGE(43_000);

    private final int port;

    DrinkType(int port) {
        this.port = port;
    }

    public int port() {
        return port;
    }
}
