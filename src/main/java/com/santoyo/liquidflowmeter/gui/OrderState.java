package com.santoyo.liquidflowmeter.gui;

/**
 * Mutable buffer holding the user-entered amount and the selected drink type.
 * Exposed to listeners so they can mutate state in response to button presses.
 */
import com.santoyo.liquidflowmeter.DrinkType;

public final class OrderState {

    private final StringBuilder amount = new StringBuilder();
    private DrinkType drink;

    public void appendDigit(char digit) {
        if (digit < '0' || digit > '9') {
            throw new IllegalArgumentException("Not a digit: " + digit);
        }
        if (amount.length() == 0 && digit == '0') {
            return; // ignore leading zeros
        }
        amount.append(digit);
    }

    public void clear() {
        amount.setLength(0);
        drink = null;
    }

    public boolean hasAmount() {
        return amount.length() > 0;
    }

    public int amountMillilitres() {
        return amount.length() == 0 ? 0 : Integer.parseInt(amount.toString());
    }

    public String amountText() {
        return amount.toString();
    }

    public DrinkType drink() {
        return drink;
    }

    public void setDrink(DrinkType drink) {
        this.drink = drink;
    }
}
