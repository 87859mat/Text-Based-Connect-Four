package connectfour;

import java.util.ArrayList;
import java.util.Arrays;

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
                str.replace(",","");
                for(int i = 0; i < str.length(); i++) {
                    this.getHoles()[row][i] = Character.getNumericValue(str.charAt(i));
                }
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
        //System.out.println(Arrays.deepToString(this.holes));
    }

    //This one's for printing
    @Override
    public String toString() {

        return null;
    }

    //this ones for saving/loading
    public String toCSVFormat() {
        return null;
    }

    //Accessors and Mutators
    public int[][] getHoles() {
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