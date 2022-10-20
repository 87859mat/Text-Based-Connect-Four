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

    @Before
    public void setup(){
        //set up for the test
        tester = new Board();

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

}