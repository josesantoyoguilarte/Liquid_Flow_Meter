//gui prat
//qiyang xia

import javax.swing.JFrame;

// this class work for create a window named"water inneed" for user input value
//create a JFrame window for othe panel work on every time when the program turn on runing the main function
public class NumberGUI{
   /**
    * Creates and displays the main program frame.
    */
   public static void main(String[] args){
      JFrame frame = new JFrame("water inneed");
      NumPanel panel = new NumPanel();
      
      frame.getContentPane().add(panel);      
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setVisible(true);
   }
}
