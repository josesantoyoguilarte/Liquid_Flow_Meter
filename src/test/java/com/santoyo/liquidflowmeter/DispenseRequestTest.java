package com.santoyo.liquidflowmeter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DispenseRequestTest {

    @Test
    void rejectsNullDrink() {
        assertThrows(NullPointerException.class, () -> new DispenseRequest(null, 100));
    }

    @Test
    void rejectsNonPositiveAmount() {
        assertThrows(IllegalArgumentException.class, () -> new DispenseRequest(DrinkType.ALCOHOL, 0));
        assertThrows(IllegalArgumentException.class, () -> new DispenseRequest(DrinkType.ALCOHOL, -5));
    }

    @Test
    void carriesDrinkAndAmount() {
        DispenseRequest r = new DispenseRequest(DrinkType.BEVERAGE, 250);
        assertEquals(DrinkType.BEVERAGE, r.drink());
        assertEquals(250, r.millilitres());
    }

    @Test
    void drinkTypeExposesPort() {
        assertEquals(42_000, DrinkType.ALCOHOL.port());
        assertEquals(43_000, DrinkType.BEVERAGE.port());
    }
}
