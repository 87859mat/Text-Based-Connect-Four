package connectfour;

import java.util.Scanner;


/**
 * TextUI: 
 * 
 * @author Eyoel Matiwos
 */
public class TextUI{

    @Override
    public String toString() {
        String uIString = "Instance of TextUI class which takes input from: " 
                        + "System.in" + " and gives output to " + "System.in"
                        + "\nID: @" + '@' + Integer.toHexString(hashCode());
        return uIString;
    }

    public void WelcomeUser() {
        System.out.println("Welcome to Eyoel's Connect Four game!\n");
        System.out.println("Would you like to load a previously saved game?");
        System.out.println("If so, please enter the relative path of the file from the \"A2\" directory");
        System.out.println("Otherwise, type enter 'n'\n");

        // = readFileName();

    }

    public String readFileName() {
        Scanner inputScanner = new Scanner(System.in);
        String userInput = inputScanner.nextLine();
        inputScanner.close();
        return userInput;
    }

    /**
     * Prints message to user indicating that a loaded game could not be properly
     * loaded from the given file and that a new game will be started instead
     */
    public void printLoadingErrorMessage() {
        System.out.println("ERROR: it seems like something went wrong when loading your saved game");
        System.out.println("Starting new game...");
    }

    public void printSaveErrorMessage() {
        System.out.println("ERROR: it seems like your game could not be saved for some reason");
    }
}