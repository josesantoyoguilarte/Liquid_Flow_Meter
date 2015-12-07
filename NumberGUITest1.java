//unittest prat
//qiyang xia
//work only with org.fest.swing.fixture.FrameFixture lib

import static org.junit.Assert.*;
import org.fest.swing.fixture.FrameFixture; 
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 

//use framefixture lib to test ecah button be click or not and test the when the buttom be clicked, does it work on right way
public class NumberGUITest1 { 
  private FrameFixture frame;   
    @Before
    public void setUp() throws Exception{
        frame = new FrameFixture(new NumberGUI()); 
        frame.show();
    }
    
    @After
    public void tearDown() throws Exception{
         frame.cleanUp(); 
    }
    
   @Test 
  public void testCopyTextToLabel() { 
   // frame.textBox("1").enterText("1"); 
    frame.button("1").click(); 
    frame.label("1").requireText("1"); 
  } 
    @Test 
  public void testCopyTextToLabel() { 
    frame.button("2").click(); 
    frame.label("2").requireText("2"); 
  } 
      @Test 
  public void testCopyTextToLabel() { 
    frame.button("3").click(); 
    frame.label("3").requireText("3"); 
  } 
      @Test 
  public void testCopyTextToLabel() { 
    frame.button("4").click(); 
    frame.label("4").requireText("4"); 
  } 
      @Test 
  public void testCopyTextToLabel() { 
    frame.button("5").click(); 
    frame.label("5").requireText("5"); 
  } 
      @Test 
  public void testCopyTextToLabel() { 
    frame.button("6").click(); 
    frame.label("6").requireText("6"); 
  } 
      @Test 
  public void testCopyTextToLabel() {  
    frame.button("7").click(); 
    frame.label("7").requireText("7"); 
  } 
      @Test 
  public void testCopyTextToLabel() { 
    frame.button("8").click(); 
    frame.label("8").requireText("8"); 
  } 
      @Test 
  public void testCopyTextToLabel() { 
    frame.button("9").click();
    frame.label("9").requireText("9"); 
  } 
        @Test 
  public void testCopyTextToLabel() { 
    frame.button("0").click(); 
    frame.label("0").requireText("0");
  }
      @Test 
  public void testCopyTextToLabel() {
    frame.button("Beverage").click(); 
    frame.label("Beverage").requireText("Beverage"); 
  } 
      @Test 
  public void testCopyTextToLabel() { 
    frame.button(" Alcohol").click(); 
    frame.label("Alcohol").requireText("Alcohol"); 
  } 
}

