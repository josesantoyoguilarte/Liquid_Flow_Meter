//gui prat
//qiyang xia
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
//NumPanel class work for crate one panel, add some buttons and label on this panel
public class NumPanel extends JPanel{
    private JLabel b;// named label b
    private JButton Clear;//named clear button
    private JButton Run;//named run and send button
    private KeypadPanel a; //named new panel which work for input ‘0’ to ‘9’ 
    public ChopadPanel c;//named new panel which work foe input kind of water
    private String k;
    public int out;
    public float afloat=0;
    public float n=0;
    public int cho1=0;
    
    public NumPanel(){
        //make new label on the top of panel
       b=new JLabel(" ");
       setLayout(new BorderLayout());
       add(b,BorderLayout.NORTH);
       //creat clear buttom and set it on button
       Clear = new JButton("Clear"); 
       ClearButtonListener listener1 = new ClearButtonListener(this);
       Clear.addActionListener(listener1);
       add(Clear,BorderLayout.SOUTH);
       //Clear.setBackground(Color.YELLOW);
       
       // creat run buttom  and set up on the right
       Run = new JButton("Run&Send"); 
       RunButtonListener listener2 = new RunButtonListener(this);
       Run.addActionListener(listener2);
       add(Run,BorderLayout.EAST);
       //Run.setBackground(Color.YELLOW);
       
       //create one new panel work for the number and put it in the mid
       a= new KeypadPanel(this);
       add(a,BorderLayout.CENTER);

       //creat one new panel work for user choose kind of water and put it on the left
       c= new ChopadPanel(this);
       add(c,BorderLayout.WEST);
       //c.setBackground(Color.BLACK);
   
    }
    
    //refresh the vlaue on Jlabel
    public void refreshUI(String text){
       b.setText(b.getText()+text);
     
    }
    //one function work for clear all the information, go back to orignal
    public void Clear(){
        b.setText(" ");
    }
    

}