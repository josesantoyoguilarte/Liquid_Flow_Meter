//gui prat
//qiyang xia
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//make one panel for 2 button which is alcohol and beverage
public class ChopadPanel extends JPanel
{
    public NumPanel a;
    private JButton Alcohol;//named alcohol button
    private JButton Beverage;//named beverage button
   
    public ChopadPanel(NumPanel a)
    {
        this.a=a;
         a.cho1=0;

       // ButtonListener listener = new ButtonListener(a);  
        setLayout(new BorderLayout());
        
        Alcohol = new JButton("alcohol"); 
        AlcoholButtonListener listener3 = new AlcoholButtonListener(this);
        Alcohol.addActionListener(listener3);
        add(Alcohol,BorderLayout.NORTH);
        //Alcohol.setBackground(Color.WHITE);
        
        Beverage = new JButton("Beverage"); 
        BeverageButtonListener listener4 = new BeverageButtonListener(this);
        Beverage.addActionListener(listener4);
        add(Beverage,BorderLayout.SOUTH);
       // Beverage.setBackground(Color.WHITE);
       
       //setPreferredSize(new Dimension(200, 300));
    }
    

}