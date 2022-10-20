package connectfour;

import java.io.File;
import java.util.ArrayList;

/**
 * Responsible for handling the storage and manipulation of the Connect 4 
 * board's state as well as creating a string representation of that state.
 * @author Eyoel Matiwos
 */
public class Board{

    private int[][] holes;
    private TextUI ui;
    
    /**
     * Default constructor for when new/empty board is desired
     */
    public Board() {
        this.ui = new TextUI();
        this.holes = new int[6][7];
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 7; j++) {
                this.holes[i][j] = 0;
            }
        }
    }
    
    /**
     * Constructor for when board state is already known 
     * (as is the case when a saved board is loaded from a file).
     * <br><br>
     * Note that error checking of the input is assumed to have already been completed
     * and therefore this method operates on the assumption that that format is in
     * proper csv and connect four formats
     * @param boardState an ArrayList of strings where each string represents
     * a row in the board
     */
    public Board(ArrayList<String> boardState) {
        this.ui = new TextUI();
        this.holes = new int[6][7];
        int row = 0;
        try {
            for(String str: boardState) {
                str = str.replace(",","");
                for(int i = 0; i < str.length(); i++) {
                    this.holes[row][i] = Character.getNumericValue(str.charAt(i));
                }
                row++;
            }
    
        } catch(IndexOutOfBoundsException e) {
            this.getUI().printLoadingErrorMessage();
            this.holes = new int[6][7];
            for(int i = 0; i < 6; i++) {
                for(int j = 0; j < 7; j++) {
                    this.holes[i][j] = 0;
                }
            }    
        }
    }

    //This one's for printing
    @Override
    public String toString() {
        int[][] bHoles = this.getHoles();
        StringBuilder boardString = new StringBuilder("---------------\n");
        for(int i = 0; i < bHoles.length; i++) {
            boardString.append("|"); //need to add leftmost horizontal border first
            for(int j = 0; j < bHoles[i].length; j++) {
                boardString.append(bHoles[i][j] + "|");
            }
            boardString.append("\n---------------\n");
        }
        
        return boardString.toString();
    }

    /**
     * Loads a saved board to this Board by updating its {@code holes} attribute;
     * <br>
     * In the case the file name is invalid
     * @param filePath
     */
    private void loadBoard(String filePath) {
        try {
            File inputFile = new File(filePath);
        } catch(NullPointerException e) {

        }
        
    }

    /**
     * Sets all of the board's holes to empty by setting {@code holes}
     * To an (2D) array of all zeros
     */
    private void resetBoard() {
        int[][] emptyBoard = {{0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},};
        this.setHoles(emptyBoard);
    }

    //this ones for saving/loading
    public String toCSVFormat() {
        return null;
    }

    //Accessors and Mutators

    /*note that no access premission modifier (e.g. public) indicates that the class
    is "package private (similar to protected but a little more restrictive)*/
    int[][] getHoles() {
        return this.holes;
    }
    
    private void setHoles(int[][] holeArray) {
        this.holes = holeArray;
    }

    private TextUI getUI() {
        return this.ui;
    }

    private void setUI(TextUI uI) {
        this.ui = uI;
    }

}