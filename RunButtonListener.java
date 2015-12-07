//gui prat
//qiyang xia
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
// after user input all informatin of he/she want, click run button, all the msg send to other RPi
public class RunButtonListener implements ActionListener
{
    private KeypadPanel b;
    private NumPanel a;
    private ChopadPanel c;
    public RunButtonListener(NumPanel a)
    {
        this.a=a;
    }
   public void actionPerformed(ActionEvent event)
    {
         
      JButton button = (JButton) event.getSource();
        
       a.n=a.afloat;
       
      System.out.println("user need "+ a.n + "ml  with");
      // if the uesr pick the alchol send information to RPi by UDP, to prot 42000
      // if the user pick the Beverage send information to RPi by UDP, to prot 43000
      if(a.cho1==0){
      }else if(a.cho1==1){
            System.out.println(a.cho1);
            System.out.println("Alcohol");
            try{
          
                  DatagramSocket clientSocket = new DatagramSocket();
                  InetAddress IPAddress = InetAddress.getByName("10.0.0.30");
                  byte[] sendData = new byte[1024];
                  byte[] receiveData = new byte[1024];
                  String buff = Float.toString(a.n);
                  sendData = buff.getBytes();
  
          
                 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 42000);
          
                 clientSocket.send(sendPacket);
       
                 System.out.println("user need sent ");
       
            }catch(Exception e){
		         System.err.println(e);
	        }
            
      }else if(a.cho1==10){
            System.out.println(a.cho1);
            System.out.println("Beverage");
             try{
                  DatagramSocket clientSocket = new DatagramSocket();
                  InetAddress IPAddress = InetAddress.getByName("10.0.0.30");
                  byte[] sendData = new byte[1024];
                  byte[] receiveData = new byte[1024];
                  String buff = Float.toString(a.n);
                  sendData = buff.getBytes();
  
          
                 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 43000);
          
                 clientSocket.send(sendPacket);
       
                 System.out.println("user need sent ");
       
            }catch(Exception e){
		         System.err.println(e);
	        }
      }
       
    

    }
}
