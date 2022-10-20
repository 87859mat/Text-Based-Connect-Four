package connectfour;


/**
 * TextUI: 
 * 
 * @author Eyoel Matiwos
 */
public class TextUI{

    @Override
    public String toString() {

        return null;
    }

    public void printLoadingErrorMessage() {
        System.out.println("ERROR: it seems like something went wrong when loading your saved game");
        System.out.println("Starting new game...");
    }
}