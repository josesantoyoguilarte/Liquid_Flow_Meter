package com.santoyo.liquidflowmeter;

import java.util.Objects;

/**
 * Immutable request describing how many millilitres of which drink to dispense.
 */
public record DispenseRequest(DrinkType drink, int millilitres) {

    public DispenseRequest {
        Objects.requireNonNull(drink, "drink");
        if (millilitres <= 0) {
            throw new IllegalArgumentException("millilitres must be > 0, was " + millilitres);
        }
    }
}
