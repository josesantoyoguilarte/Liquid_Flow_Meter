package com.santoyo.liquidflowmeter.gui;

import com.santoyo.liquidflowmeter.DrinkType;

import java.awt.GridLayout;
import java.util.function.Consumer;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Lets the user pick a drink type.
 */
final class DrinkChoicePanel extends JPanel {

    DrinkChoicePanel(Consumer<DrinkType> onChoice) {
        super(new GridLayout(2, 1, 4, 4));

        JButton alcohol = new JButton("Alcohol");
        alcohol.addActionListener(e -> onChoice.accept(DrinkType.ALCOHOL));
        add(alcohol);

        JButton beverage = new JButton("Beverage");
        beverage.addActionListener(e -> onChoice.accept(DrinkType.BEVERAGE));
        add(beverage);
    }
}
