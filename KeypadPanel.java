//gui prat
//qiyang xia
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//keypadPanel class is to initilaizes the digits 0-9
public class KeypadPanel extends JPanel
{
    private NumPanel a;
    //public String aString;
    /**
     * Initializes the keypad containing the digits 0-9 
     */
    public KeypadPanel(NumPanel a)
    {
        this.a=a;
        ButtonListener listener = new ButtonListener(a);  

        /* Change the panel's layout manager to a 4-by-3 grid. */
        setLayout(new GridLayout(4, 3));
  
        /* Populate the panel with nine buttons. */
        for (int i = 1; i <= 9; i += 1) {
            JButton b = new JButton("" + i);
                
            /* All of the buttons will share the same listener. */
            b.addActionListener(listener);
                
            /* Place the button in the panel. */
            add(b);
            //b.setBackground(Color.GREEN);
        }
        /* Populate the bottom row. */
        JButton b = new JButton("/");
        b.addActionListener(listener);
        add(b);
        //b.setBackground(Color.RED);
        
        b = new JButton("0");
        b.addActionListener(listener);
        add(b);
       // b.setBackground(Color.GREEN);
        
        b = new JButton("/");
        b.addActionListener(listener);
        add(b);  
       // b.setBackground(Color.RED);
 
       setPreferredSize(new Dimension(200, 300));
    }
    

}
