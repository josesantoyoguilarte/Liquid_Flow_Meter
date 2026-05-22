package com.santoyo.liquidflowmeter.gui;

import com.santoyo.liquidflowmeter.DrinkType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderStateTest {

    @Test
    void appendsDigitsLeftToRight() {
        OrderState s = new OrderState();
        s.appendDigit('1');
        s.appendDigit('2');
        s.appendDigit('5');
        assertEquals("125", s.amountText());
        assertEquals(125, s.amountMillilitres());
        assertTrue(s.hasAmount());
    }

    @Test
    void ignoresLeadingZeros() {
        OrderState s = new OrderState();
        s.appendDigit('0');
        s.appendDigit('0');
        s.appendDigit('5');
        assertEquals("5", s.amountText());
    }

    @Test
    void clearResetsEverything() {
        OrderState s = new OrderState();
        s.appendDigit('7');
        s.setDrink(DrinkType.ALCOHOL);
        s.clear();
        assertFalse(s.hasAmount());
        assertEquals(0, s.amountMillilitres());
        assertNull(s.drink());
    }

    @Test
    void rejectsNonDigit() {
        OrderState s = new OrderState();
        assertThrows(IllegalArgumentException.class, () -> s.appendDigit('a'));
    }
}
