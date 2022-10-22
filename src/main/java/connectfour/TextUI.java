package connectfour;

import java.util.Scanner;


/**
 * TextUI: 
 * 
 * @author Eyoel Matiwos
 */
public class TextUI{

    public void welcomeUser() {
        System.out.println("Welcome to Eyoel's Connect Four game!\n");
        System.out.println("Would you like to load a previously saved game?");
        System.out.println("If so, please enter the relative path of the file from the \"A2\" directory");
        System.out.println("Otherwise, if you would like to start a new game, type enter 'n'\n");

        // = readFileName();

    }

    public String readUserInput() {
        Scanner inputScanner = new Scanner(System.in);
        String userInput;
        userInput = inputScanner.nextLine();
        inputScanner.close();
        return userInput;
    }

    public String readUserInput(Scanner inputScanner) {
        String userInput;
        userInput = inputScanner.nextLine();
        inputScanner.close();
        return userInput;
    }

    public void printBoard(Board gameBoard) {
        System.out.println(gameBoard.toString());
    }

    public int promptPlayerToMove(String player, Board gameBoard) {
        boolean validMove = false;
        String moveString;
        int columnChosen = -1;
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("It is now player " + player + "'s turn\n");

        while(!validMove) {
            System.out.println("Please enter a single digit from 1 - 7 to drop a piece in the corresponding column");
            System.out.println("Or enter 's' to save the current game or 'q' to quit the game");
            moveString = readUserInput(inputScanner);

            moveString = checkForSaveOrQuit(moveString, gameBoard);

            try {
                columnChosen = Integer.parseInt(moveString) - 1;
                if(gameBoard.columnIsFull(columnChosen)) {
                    System.out.println("ERROR: The chosen column is full\n");
                } else if(columnChosen < 0 || columnChosen > 6){
                    System.out.println("ERROR: non-existant column chosen (there are only 7 columns)\n");
                } else {
                    validMove = true;
                }
            }catch (NumberFormatException e) {
                validMove = false;
            }
        }
        return columnChosen;
    }

    public String checkForSaveOrQuit(String moveString, Board gameBoard) { 
        if(moveString.equalsIgnoreCase("q")) {
            System.out.println("\n\nQuitting Game...\n\n");
            System.exit(0);
        } else if(moveString.equalsIgnoreCase("s")) {
            promptForSaveFile(gameBoard);
            return null;
        }
        
        return moveString;
    }

    public void promptForSaveFile(Board gameBoard) {
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("Enter the file/path you would like to save the game to the relative A2 directory");
        System.out.println("e.g. 'assest/savedGame.csv'");
        
        String fileName = this.readUserInput(inputScanner);
        gameBoard.saveBoard(fileName);

        System.out.println("Your game was saved");
        System.out.println("The game will now continue, feel free to quit");
    }

    /**
     * Prints message to user indicating that a loaded game could not be properly
     * loaded from the given file and that a new game will be started instead
     */
    public void printLoadingErrorMessage() {
        System.out.println("ERROR: it seems like something went wrong when loading your saved game");
        System.out.println("Starting new game...");
    }

    public void printLoadingSuccessMessage(String fileName) {
        System.out.println("Load Successful!\nResuming game saved to \"" + fileName + "\"...\n");
    }

    public void printSaveErrorMessage() {
        System.out.println("ERROR: it seems like your game could not be saved for some reason");
    }

    public void printCustomMessage(String message) {
        System.out.println(message);
    }
    
    @Override
    public String toString() {
        String uIString = "Instance of TextUI class which takes input from: " 
                        + "System.in" + " and gives output to " + "System.in"
                        + "\nID: @" + Integer.toHexString(hashCode());
        return uIString;
    }

}