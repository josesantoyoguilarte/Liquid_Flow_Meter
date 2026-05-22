package com.santoyo.liquidflowmeter.gui;

import static org.assertj.swing.edt.GuiActionRunner.execute;

import com.santoyo.liquidflowmeter.UdpDispenseClient;
import javax.swing.JButton;
import javax.swing.JLabel;
import org.assertj.swing.core.GenericTypeMatcher;
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
        window.button(buttonWithText("1")).click();
        window.button(buttonWithText("2")).click();
        window.button(buttonWithText("5")).click();

        window.label(labelWithText("125")).requireVisible();
    }

    @Test
    void clearResetsDisplay() {
        window.button(buttonWithText("7")).click();
        window.button(buttonWithText("Clear")).click();
        window.label(labelWithText(" ")).requireVisible();
    }

    private static GenericTypeMatcher<JButton> buttonWithText(String text) {
        return new GenericTypeMatcher<>(JButton.class) {
            @Override
            protected boolean isMatching(JButton button) {
                return text.equals(button.getText());
            }
        };
    }

    private static GenericTypeMatcher<JLabel> labelWithText(String text) {
        return new GenericTypeMatcher<>(JLabel.class) {
            @Override
            protected boolean isMatching(JLabel label) {
                return text.equals(label.getText());
            }
        };
    }
}
