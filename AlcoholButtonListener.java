//gui prat
//qiyang xia

import java.awt.event.*;
import javax.swing.*;

public class AlcoholButtonListener implements ActionListener
{
    private ChopadPanel c;
    private NumPanel a;
    public AlcoholButtonListener(ChopadPanel c)
    {
        this.c=c;
    }
   
    public void actionPerformed(ActionEvent event)
    {
       
       JButton button = (JButton) event.getSource();
      // better for get information for RPi, when user pick alcohol, make the value to 1 means alcohol
       System.out.println("Alcohol");
        c.a.cho1=1;
  
    }
}
