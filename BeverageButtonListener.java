//gui prat
//qiyang xia
import java.awt.event.*;
import javax.swing.*;

public class BeverageButtonListener implements ActionListener
{
    private ChopadPanel c;
    private NumPanel a;
    public BeverageButtonListener(ChopadPanel c)
    {
        this.c=c;
    }
   
    public void actionPerformed(ActionEvent event)
    {
       
       JButton button = (JButton) event.getSource();
      // better for get information for RPi, when user pick Beverage, make the value to 10 means Beverage
       System.out.println("Beverage");
       c.a.cho1=10;
      System.out.println(a.cho1);
  
    }
}
