package connectfour;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
    private ArrayList<String> testStrArray;

    @Before
    public void setup(){
        //set up for the test
        tester = new Board();
        testStrArray = new ArrayList<String>(Arrays.asList("1,2,0,1,1,2,1", 
                                                                    "2,2,2,1,1,2,1", 
                                                                    "0,0,0,0,0,0,0", 
                                                                    "0,0,0,0,0,0,0", 
                                                                    "1,2,0,1,1,2,1", 
                                                                    "2,2,2,1,1,2,1"));
        tester2 = new Board();
        try {
            tester2.setHoles(tester2.boardStringToArray(testStrArray)); //this should never throw an exception
        } catch(FileFormatException | InvalidContentException e) {
            tester2.setHoles(new int[][]{{0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},
                                         {0,0,0,0,0,0,0},});
        }
            

    }

    @Test
    /* 
     * Tests whether the default constructor initializes it's hole's Attribute properly
     */
    public void defaultConstructorTest() {
        Board actual = tester; //tester was initialized using the default constructor this function tests
        int[][] expectedBoard = new int[][]{{0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0}};
        
        
        Board expected = new Board();
        expected.setHoles(expectedBoard);

        Assert.assertTrue(Arrays.deepEquals(actual.getHoles(), expected.getHoles()));
    }

    @Test
    public void saveBoardTest() {
        String saveFileName = "assets/savetest.csv";
        String tempString;
        String actual = "";
        String expected = "1,2,0,1,1,2,1\n" 
                        + "2,2,2,1,1,2,1\n" 
                        + "0,0,0,0,0,0,0\n" 
                        + "0,0,0,0,0,0,0\n" 
                        + "1,2,0,1,1,2,1\n" 
                        + "2,2,2,1,1,2,1\n";
        tester2.saveBoard(saveFileName);
        
        try (BufferedReader bReader = new BufferedReader(new FileReader(saveFileName))){
            tempString = bReader.readLine();
            while(tempString != null) {
                actual += tempString;
                actual += "\n";
                tempString = bReader.readLine();
            }         
        } catch (Exception e) {
            actual = null;
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void loadBoardTest() {
        String actualString = "assets/exampleboard.csv";
        int[][] actualBoard;
        int[][] expectedBoard = new int[][]{{0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,1,0},
                                            {0,0,0,1,2,2,1}};
        //we use tester2 so it's noticable when the loadBoard sets the board to empty
        tester2.loadBoard(actualString);
        actualBoard = tester2.getHoles();

        //the board being set to an empty board indactes that something unexpected happened
        if(Arrays.deepEquals(actualBoard, new int[][]{{0,0,0,0,0,0,0},
                                                    {0,0,0,0,0,0,0},
                                                    {0,0,0,0,0,0,0},
                                                    {0,0,0,0,0,0,0},
                                                    {0,0,0,0,0,0,0},
                                                    {0,0,0,0,0,0,0}})) {
            actualBoard = null;
        }
        Assert.assertArrayEquals(expectedBoard, actualBoard);
    }

    
    @Test 
    public void loadBoardInvalidFileNameTest() {
        Assert.assertFalse(tester.loadBoard("randomFile123"));
    }

    @Test 
    public void loadBoardTooFewRows() {
        Assert.assertFalse(tester.loadBoard("assests/badFormat.csv"));
    }

    @Test 
    public void loadBoardTooManyRows() {
        Assert.assertFalse(tester.loadBoard("assets/badFormat4.csv"));
    }

    @Test 
    public void loadBoardRowTooShort() {
        Assert.assertFalse(tester.loadBoard("assests/badFormat2.csv"));
    }

    @Test 
    public void loadBoardRowTooLong() {
        Assert.assertFalse(tester.loadBoard("assests/badFormat3.csv"));
    }

    @Test 
    public void loadBoardContainsLetter() {
        Assert.assertFalse(tester.loadBoard("assests/badContent.csv"));
    }

    @Test 
    public void loadBoardContainsInvalidNumber() {
        Assert.assertFalse(tester.loadBoard("assests/badContent2.csv"));
    }
    
    @Test 
    public void loadBoardContainsInvalidCharacter() {
        Assert.assertFalse(tester.loadBoard("assests/badContent3.csv"));
    }
    
    @Test
    public void readBoardFileTest() {
        ArrayList<String> actual;
        String actualString = "assets/exampleboard.csv";
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,0,0");
        expected.add("0,0,0,0,0,1,0");
        expected.add("0,0,0,1,2,2,1");

        File actualFile = new File(actualString);

        try {
            BufferedReader bReader = new BufferedReader(new FileReader(actualFile));
            actual = tester.readBoardFile(bReader);
        } catch(IOException | FileFormatException e) {
            actual = null;
        }

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void dropPieceP1Test() {
        int[][] expected = {{1,2,1,1,1,2,1}, 
                            {2,2,2,1,1,2,1}, 
                            {0,0,0,0,0,0,0}, 
                            {0,0,0,0,0,0,0}, 
                            {1,2,0,1,1,2,1}, 
                            {2,2,2,1,1,2,1}};
        int[][] actual = tester2.getHoles();
        tester2.dropPiece(2, 1);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void dropPieceP2Test() {
        int[][] expected = {{1,2,2,1,1,2,1}, 
                            {2,2,2,1,1,2,1}, 
                            {0,0,0,0,0,0,0}, 
                            {0,0,0,0,0,0,0}, 
                            {1,2,0,1,1,2,1}, 
                            {2,2,2,1,1,2,1}};
        int[][] actual = tester2.getHoles();
        tester2.dropPiece(2, 2);

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void columnIsFullTest() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,1,2,2,1}});
        boolean full = tester2.columnIsFull(5);
        Assert.assertTrue(full);
    }

    @Test
    public void columnNotFullTest() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,0,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,1,2,2,1}});
        boolean full = tester2.columnIsFull(5);
        Assert.assertFalse(full);
    }

    @Test
    public void horizontalWinNotFoundP1Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,2,1,0,1,1}});
        boolean won = tester2.horizontalWinFound(1);
        Assert.assertFalse(won);  
    }

    @Test
    public void verticalWinNotFoundP1Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,2,2,2,2,1}});
        boolean won = tester2.verticalWinFound(1);
        Assert.assertFalse(won);  
    }

    @Test
    public void diagonalWinNotFoundP1Test() {
        tester2.setHoles(new int[][]{{0,1,0,0,0,0,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,0,0,1,0,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,2,2,2,2,1}});
        boolean won = tester2.diagonalWinFound(1);
        Assert.assertFalse(won);  
    }

    
    @Test
    public void horizontalWinNotFoundP2Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,2,1,2,2,1}});
        boolean won = tester2.horizontalWinFound(2);
        Assert.assertFalse(won);  
    }

    @Test
    public void verticalWinNotFoundP2Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,2,1,2,2,1}});
        boolean won = tester2.verticalWinFound(2);
        Assert.assertFalse(won);  
    }

    @Test
    public void diagonalWinNotFoundP2Test() {
        tester2.setHoles(new int[][]{{0,1,0,0,0,0,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,0,2,0,0,0},
                                    {0,0,0,0,1,0,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,2,2,1,2,2}});
        boolean won = tester2.diagonalWinFound(2);
        Assert.assertFalse(won);  
    }

    @Test
    public void horizontalWinFoundP1Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,2,1,1,1,1}});
        boolean won = tester2.horizontalWinFound(1);
        Assert.assertTrue(won);  
    }

    @Test
    public void verticalWinFoundP1Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,2,2,2,2,1}});
        boolean won = tester2.verticalWinFound(1);
        Assert.assertTrue(won);  
    }

    @Test
    public void diagonalWinFoundDownRightP1Test() {
        tester2.setHoles(new int[][]{{0,1,0,0,0,0,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,0,1,0,0,0},
                                    {0,0,0,0,1,0,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,2,2,2,2,1}});
        boolean won = tester2.diagonalWinFound(1);
        Assert.assertTrue(won);  
    }

    @Test
    public void diagonalWinFoundDownLeftP1Test() {
        tester2.setHoles(new int[][]{{0,0,0,1,0,0,0},
                                    {0,0,1,0,0,0,0},
                                    {0,1,0,0,0,0,0},
                                    {1,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,2,2,2,2,1}});
        boolean won = tester2.diagonalWinFound(1);
        Assert.assertTrue(won);  
    }

    
    @Test
    public void horizontalWinFoundP2Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,2,2,2,2,1}});
        boolean won = tester2.horizontalWinFound(2);
        Assert.assertTrue(won);  
    }

    @Test
    public void verticalWinFoundP2Test() {
        tester2.setHoles(new int[][]{{0,0,0,0,0,1,0},
                                    {0,0,0,0,0,1,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,2,1,2,2,1}});
        boolean won = tester2.verticalWinFound(2);
        Assert.assertTrue(won);  
    }

    @Test
    public void diagonalWinFoundDownRightP2Test() {
        tester2.setHoles(new int[][]{{0,1,0,0,0,0,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,0,2,0,0,0},
                                    {0,0,0,0,2,0,0},
                                    {0,0,0,0,0,2,0},
                                    {0,0,2,2,1,2,2}});
        boolean won = tester2.diagonalWinFound(2);
        Assert.assertTrue(won);  
    }

    @Test
    public void diagonalWinFoundDownLeftP2Test() {
        tester2.setHoles(new int[][]{{0,0,0,2,0,0,0},
                                    {0,0,2,0,0,0,0},
                                    {0,2,0,0,0,0,0},
                                    {2,0,0,0,0,0,0},
                                    {0,0,0,0,0,0,0},
                                    {0,0,2,1,2,2,1}});
        boolean won = tester2.diagonalWinFound(2);
        Assert.assertTrue(won);  
    }

    @Test
    public void boardIsFullTest() {
        tester2.setHoles(new int[][]{{1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1}});
        boolean full = tester2.boardIsFull();
        Assert.assertTrue(full);
    }

    @Test
    public void boardIsNotFullTest() {
        tester2.setHoles(new int[][]{{1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,1},
                                    {1,1,2,2,2,2,0}});
        boolean full = tester2.boardIsFull();
        Assert.assertFalse(full);
    }
    
    @Test
    public void resetBoardTest() {
        int[][] expectedBoard = new int[][]{{0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},
                                            {0,0,0,0,0,0,0},};
        tester2.resetBoard();
        int[][] actual = tester2.getHoles();
        Assert.assertArrayEquals(expectedBoard, actual);
    }
    
    @Test
    public void boardStringToArrayTest() {
        int[][] actual;
        int[][] expectedArray = {{1,2,0,1,1,2,1}, 
                                {2,2,2,1,1,2,1}, 
                                {0,0,0,0,0,0,0}, 
                                {0,0,0,0,0,0,0}, 
                                {1,2,0,1,1,2,1}, 
                                {2,2,2,1,1,2,1}};
        try{
            actual = tester.boardStringToArray(testStrArray);
        } catch (FileFormatException | InvalidContentException e) {
            actual = null;
        }
        

        Assert.assertArrayEquals(expectedArray, actual);
        
    }

    @Test
    /*
     * Tests whether or not Board.toString() properly converts array of 
     * 1 digit integeers into a string
     */
    public void toStringTest() {
        String expected =   "---------------\n"
                          + "|1|2|0|1|1|2|1|\n"
                          + "---------------\n"
                          + "|2|2|2|1|1|2|1|\n"
                          + "---------------\n"
                          + "|0|0|0|0|0|0|0|\n"
                          + "---------------\n"
                          + "|0|0|0|0|0|0|0|\n"
                          + "---------------\n"
                          + "|1|2|0|1|1|2|1|\n"
                          + "---------------\n"
                          + "|2|2|2|1|1|2|1|\n"
                          + "---------------\n";
        String actual = tester2.toString();

        Assert.assertEquals(expected, actual);
    }

}