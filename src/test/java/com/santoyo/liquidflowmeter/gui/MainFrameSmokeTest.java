package com.santoyo.liquidflowmeter.gui;

import static org.assertj.swing.edt.GuiActionRunner.execute;

import com.santoyo.liquidflowmeter.UdpDispenseClient;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfSystemProperty;

/**
 * Smoke test: clicks digits and verifies the display label updates.
 * Skipped in headless CI by setting {@code -Djava.awt.headless=true}.
 */
@DisabledIfSystemProperty(named = "java.awt.headless", matches = "true")
class MainFrameSmokeTest {

    private FrameFixture window;

    @BeforeAll
    static void setUpOnce() {
        FailOnThreadViolationRepaintManager.install();
    }

    @AfterAll
    static void tearDownOnce() {
        FailOnThreadViolationRepaintManager.uninstall();
    }

    @BeforeEach
    void setUp() {
        MainFrame frame = execute(() -> new MainFrame(new UdpDispenseClient("127.0.0.1")));
        window = new FrameFixture(frame);
        window.show();
    }

    @AfterEach
    void tearDown() {
        window.cleanUp();
    }

    @Test
    void typingDigitsUpdatesDisplay() {
        window.button(b -> "1".equals(b.target().getText())).click();
        window.button(b -> "2".equals(b.target().getText())).click();
        window.button(b -> "5".equals(b.target().getText())).click();

        window.label(l -> "125".equals(l.target().getText())).requireVisible();
    }

    @Test
    void clearResetsDisplay() {
        window.button(b -> "7".equals(b.target().getText())).click();
        window.button(b -> "Clear".equals(b.target().getText())).click();
        window.label(l -> " ".equals(l.target().getText())).requireVisible();
    }
}
