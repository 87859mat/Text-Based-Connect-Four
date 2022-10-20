package connectfour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.Arrays;

/* you will need to add test methods and likely change the
setup method as well.  The samples that are here are just so that
you can see how junit works.

Tests are run on build unless specifically excluded with -x test.
The test results are reported in the reports subfolder of the build directory */


public class BoardTest{
    private Board tester;
    private Board tester2;

    @Before
    public void setup(){
        //set up for the test
        tester = new Board();
        ArrayList<String> testStrArray = new ArrayList<String>(Arrays.asList("1,2,3,4,5,6,7", 
                                                                    "9,8,7,6,5,4,3", 
                                                                    "0,0,0,0,0,0,0", 
                                                                    "0,0,0,0,0,0,0", 
                                                                    "9,8,7,6,5,4,3", 
                                                                    "1,2,3,4,5,6,7"));
        tester2 = new Board(testStrArray);

    }

    @Test
    /* 
     * Tests whether the default constructor initializes it's hole's Attribute properly
     */
    public void defaultConstructorTest() {
        Board actual = tester; //tester was initialized using the default constructor this function tests
        ArrayList<String> expectedBoard = new ArrayList<String>(Arrays.asList("0,0,0,0,0,0,0", 
                                                                            "0,0,0,0,0,0,0", 
                                                                            "0,0,0,0,0,0,0", 
                                                                            "0,0,0,0,0,0,0", 
                                                                            "0,0,0,0,0,0,0", 
                                                                            "0,0,0,0,0,0,0"));
        
        
        Board expected = new Board(expectedBoard);

        Assert.assertTrue(Arrays.deepEquals(actual.getHoles(), expected.getHoles()));
    }

    @Test
    /*
     * Tests whether or not Board.toString() properly converts array of 
     * 1 and two digit integeers into a string
     */
    public void toStringTest() {
        String expected =   "---------------\n"
                          + "|1|2|3|4|5|6|7|\n"
                          + "---------------\n"
                          + "|9|8|7|6|5|4|3|\n"
                          + "---------------\n"
                          + "|0|0|0|0|0|0|0|\n"
                          + "---------------\n"
                          + "|0|0|0|0|0|0|0|\n"
                          + "---------------\n"
                          + "|9|8|7|6|5|4|3|\n"
                          + "---------------\n"
                          + "|1|2|3|4|5|6|7|\n"
                          + "---------------\n";
        String actual = tester2.toString();

        Assert.assertEquals(expected, actual);
    }

}