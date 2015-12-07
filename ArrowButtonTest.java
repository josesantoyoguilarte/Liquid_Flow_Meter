
import junit.extensions.abbot.*;
import abbot.tester.*;

//setup abbot libarary
//allow button be simulated click anb be test

public class ArrowButtonTest extends ComponentTestFixture {

    public ArrowButtonTest(String name) {
        super(name); 
    }

     public static void main(String[] args) {
        TestHelper.runTests(args, ArrowButtonTest.class);
    }
}
