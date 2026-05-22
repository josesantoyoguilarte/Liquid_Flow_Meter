package com.santoyo.liquidflowmeter;

import com.santoyo.liquidflowmeter.gui.MainFrame;
import javax.swing.SwingUtilities;

/**
 * Application entry point. Launches the Swing UI on the EDT.
 */
public final class LiquidFlowMeterApp {

    private LiquidFlowMeterApp() {}

    public static void main(String[] args) {
        String host = System.getProperty("dispenser.host", "10.0.0.30");
        UdpDispenseClient client = new UdpDispenseClient(host);
        SwingUtilities.invokeLater(() -> new MainFrame(client).setVisible(true));
    }
}
