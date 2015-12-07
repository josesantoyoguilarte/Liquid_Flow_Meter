
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.awt.event.*;
import javax.swing.*;

public class NumberGUITest{
    private ComponentTester tester;
    private String clickType;
    public NumberGUITest()
    {
    }

    @Before
    public void setUp() throws Exception
    {
         // Suppose MyComponent has a text field and a button...
         tester= new ComponentTester();
         // Display a frame containing the given component
         showFrame(tester);
    }

    @After
    public void tearDown() throws Exception
    {
        // Default JUnit test runner keeps references to Tests for its
        // lifetime, so TestCase fields won't be GC'd for the lifetime of the
        // runner
        tester = null;
    }
    //use abbot lib to test ecah button be click or not and test the when the buttom be clicked, does it work on right way
    @test
    public void testClick() {
        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                clickType = ev.getActionCommand();                            
            }
        };
        
        ArrowButton 1 = new ArrowButton(ArrowButton.1);
        ArrowButton 2 = new ArrowButton(ArrowButton.2);
        ArrowButton 3 = new ArrowButton(ArrowButton.3);
        ArrowButton 4 = new ArrowButton(ArrowButton.4);
        ArrowButton 5 = new ArrowButton(ArrowButton.5);
        ArrowButton 6 = new ArrowButton(ArrowButton.6);
        ArrowButton 7 = new ArrowButton(ArrowButton.7);
        ArrowButton 8 = new ArrowButton(ArrowButton.8);
        ArrowButton 9 = new ArrowButton(ArrowButton.9);
        ArrowButton 0 = new ArrowButton(ArrowButton.0);
        ArrowButton Beverage = new ArrowButton(ArrowButton.Beverage);
        ArrowButton Alcohol = new ArrowButton(ArrowButton.Alcohol);

        1.addActionListener(al);
        2.addActionListener(al);
        3.addActionListener(al);
        4.addActionListener(al);
        5.addActionListener(al);
        6.addActionListener(al);
        7.addActionListener(al);
        8.addActionListener(al);
        9.addActionListener(al);
        0.addActionListener(al);
        Beverage.addActionListener(al);
        Alcohol.addActionListener(al);
        
        JPanel pane = new JPanel();
        pane.add(1);
        pane.add(2);
        pane.add(3);
        pane.add(4);
        pane.add(5);
        pane.add(6);
        pane.add(7);
        pane.add(8);
        pane.add(9);
        pane.add(0);
        pane.add(Beverage);
        pane.add(Alcohol);
        // ComponentTestFixture provides the frame
        showFrame(pane);

        clickType = null;
        tester.actionClick(1);        
        assertEquals("Action failed (1)", ArrowButton.1, clickType);
        clickType = null;
        tester.actionClick(2);        
        assertEquals("Action failed (2)", ArrowButton.2, clickType);
        clickType = null;
        tester.actionClick(3);        
        assertEquals("Action failed (3)", ArrowButton.3, clickType);
        clickType = null;
        tester.actionClick(4);        
        assertEquals("Action failed (4)", ArrowButton.4, clickType);
        clickType = null;
        tester.actionClick(4);        
        assertEquals("Action failed (4)", ArrowButton.4, clickType);
        clickType = null;
        tester.actionClick(5);        
        assertEquals("Action failed (5)", ArrowButton.5, clickType);
        clickType = null;
        tester.actionClick(6);        
        assertEquals("Action failed (6)", ArrowButton.6, clickType);
        clickType = null;
        tester.actionClick(7);        
        assertEquals("Action failed (7)", ArrowButton.7, clickType);
        clickType = null;
        tester.actionClick(8);        
        assertEquals("Action failed (8)", ArrowButton.8, clickType);
        clickType = null;
        tester.actionClick(9);        
        assertEquals("Action failed (9)", ArrowButton.9, clickType);
        clickType = null;
        tester.actionClick(0);        
        assertEquals("Action failed (0)", ArrowButton.0, clickType);
        clickType = null;
        tester.actionClick(Alcohol);        
        assertEquals("Action failed (Alcohol)", ArrowButton.Alcohol, clickType);
        clickType = null;
        tester.actionClick(Beverage);        
        assertEquals("Action failed (Beverage)", ArrowButton.Beverage, clickType);
        
    }
       
}


