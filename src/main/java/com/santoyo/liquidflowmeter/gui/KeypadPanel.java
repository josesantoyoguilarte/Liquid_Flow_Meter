package com.santoyo.liquidflowmeter.gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * 3x4 numeric keypad (digits 0-9).
 */
final class KeypadPanel extends JPanel {

    KeypadPanel(ActionListener digitListener) {
        super(new GridLayout(4, 3, 4, 4));

        for (int i = 1; i <= 9; i++) {
            add(digitButton(Integer.toString(i), digitListener));
        }
        add(new JPanel()); // filler
        add(digitButton("0", digitListener));
        add(new JPanel()); // filler
    }

    private static JButton digitButton(String text, ActionListener listener) {
        JButton b = new JButton(text);
        b.setActionCommand(text);
        b.addActionListener(listener);
        return b;
    }
}
