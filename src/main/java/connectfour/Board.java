package connectfour;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
     * Saves the board in it's current state to the files who's path is specified by
     * {@code saveFileName}
     * @param saveFileName a String representing the relative path (from the
     * 'A2' directory) to the file where the user wants to load a game from
     */
    public void saveBoard(String saveFileName) {
        try (FileWriter fWriter = new FileWriter(saveFileName);){
            int[][] boardState = this.getHoles();
            String toWrite = Board.toCSVFormat(boardState);
            fWriter.write(toWrite);
        } catch(IOException e) {
            this.getUI().printSaveErrorMessage();
        }
    }

    /**
     * Loads a saved board to this Board by updating its {@code holes} attribute
     * and handles all exceptions that may arise throughout that process
     * <br>
     * In the case the file name is invalid
     * @param filePath a String representing the relative path (from the
     * 'A2' directory) to the file where the user wants to load a game from
     */
    public void loadBoard(String filePath) {
        File inputFile = new File(filePath);
        
        try (BufferedReader bReader = new BufferedReader(new FileReader(inputFile))) {
            ArrayList<String> individualLines = readBoardFile(bReader);
            this.setHoles(boardStringToArray(individualLines));
        } catch(IOException | FileFormatException | InvalidContentException e) {
            this.getUI().printLoadingErrorMessage();
            this.resetBoard();
        }    
    }

    /**
     * Read's a file where a saved connect four game is supposedly stored and (if it's
     * in the format of a saved connect four board) returns an arraylist containing the
     * saved game's state
     * @param bReader the buffered reader that will be used to read the saved game's file
     * @return individualLines An arraylist of string where each string represents a row of the board
     * @throws IOException - If an an error occurs concering the BufferedReader
     * @throws FileFormatException - if the file is not in the format of a saved connect four game
     */
    public static ArrayList<String> readBoardFile(BufferedReader bReader) throws IOException, FileFormatException{
        String tempString;
        ArrayList<String> individualLines = new ArrayList<String>();
        int lineCounter = 0;

        tempString = bReader.readLine();
        while(tempString != null) {
            
            if(tempString.replace(",", "").length() != 7) {
                throw new FileFormatException();
            }
            individualLines.add(lineCounter, tempString);
            lineCounter++;
            tempString = bReader.readLine();
        }

        if(individualLines.size() != 6) {
            throw new FileFormatException();
        }

        return individualLines;
    }

    /**
     * Sets all of the board's holes to empty by setting {@code holes}
     * To an (2D) array of all zeros
     */
    public void resetBoard() {
        int[][] emptyBoard = {{0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},
                              {0,0,0,0,0,0,0},};
        this.setHoles(emptyBoard);
    }

    /**
     * Convert's the {@code Board}'s {@code holes} attribute (2D array) into a string in
     * the format of a csv i.e. with each element in a row being seperated by commas and
     * with each row being seperated by a newline
     * @param boardState a 2D integer array indciating the values at each postion of
     * board for the game that is to be saved
     * @return A string which that is going to be copied exactly into into the file
     * designated for saving the game
     */
    private static String toCSVFormat(int[][] boardState) {
        String csv = "";

        for(int i = 0; i < boardState.length; i++) {
            //don't want to have a comma before the first element in a row
            csv += boardState[i][0]; 

            for(int j = 1; j < boardState[0].length; j++) {
                csv += ",";
                csv += boardState[i][j];
            }
            if(i < (boardState.length - 1)) {
                csv += "\n";
            }
        }
        return csv;
    }

    /**
     * Takes an array list that (when it's elements are appended to each other with newlinea
     * between them) is in the format of saved connect four game CSV file and returns a 2D 
     * array that represents an equivelent board state
     * @param boardString String arraylist that contains a board in the CSV expected format
     * @return a 2D integer array representing the contents of each hole of the board
     * @throws FileFormatException - If the comma-sperated board formed by the elements of
     *                               the array list do not form a board in the proper format
     * @throws InvalidContentException - If an unexpected character (characters other than '0',
     *                                  '1', and '2') is found in the board string
     */
    public static int[][] boardStringToArray(ArrayList<String> boardString) throws FileFormatException, InvalidContentException{
        int[][] holesArray = new int[6][7];
        String tempString;
        try {
            for(int i = 0; i < holesArray.length; i++) {
                tempString = boardString.get(i).replace(",","");
                for(int j = 0; j < tempString.length(); j++) {
                    if(tempString.charAt(i) != '0' && tempString.charAt(i) != '1' 
                    && tempString.charAt(i) != '2') {
                        throw new InvalidContentException();
                    }
                    holesArray[i][j] = Character.getNumericValue(tempString.charAt(j));
                }
            }
    
        } catch(IndexOutOfBoundsException e) {
            throw new FileFormatException();
        }

        return holesArray;
    }

    /**
     * Converts Board object's state to the string that will eventually be displayed to
     * the user
     */
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

    //Accessors and Mutators

    /*note that no access premission modifier (e.g. public) indicates that the class
    is "package private (similar to protected but a little more restrictive)*/
    int[][] getHoles() {
        return this.holes;
    }
    
    void setHoles(int[][] holeArray) {
        this.holes = holeArray;
    }

    private TextUI getUI() {
        return this.ui;
    }

    private void setUI(TextUI uI) {
        this.ui = uI;
    }

}