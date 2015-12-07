//gui prat
//qiyang xia
import java.awt.event.*;
import javax.swing.*;

//this class work for the label, show the value every time after the number button be clicked
public class ButtonListener implements ActionListener
{   
    private NumPanel a;
    private String S;
  
    public ButtonListener(NumPanel a)
    {
        this.a=a;
    }
   
    public void actionPerformed(ActionEvent event)
    {

        /* Get a reference to the button that was clicked. */ 
        JButton button = (JButton) event.getSource();
        
        /* Get the text that is displayed on the button. */
        String key = button.getText();
        S=key;
        //tranfor the string value on label, and calcaute the actual value after refuse every time
        //but the value into new int or double, better for send informatin to RPi bater
        a.afloat = a.afloat*10+ Float.parseFloat(S);

        System.out.println(a.afloat);

        System.out.println("The " + key + " key was clicked.");

        a.refreshUI(key);
    
    }
}
