import java.awt.event.*;
import javax.swing.*;
//this class for clear all the information when the clear button be clicked
public class ClearButtonListener implements ActionListener
{
    private NumPanel a;
    private KeypadPanel b;

    public ClearButtonListener(NumPanel a)
    {
        this.a=a;
    }
   
    public void actionPerformed(ActionEvent event)
    {
         
        JButton button = (JButton) event.getSource();
        //clear the value and the kind of water 
        String key = button.getText();
        a.afloat=0;
        a.Clear();
        a.cho1=0;

      
    }
}
