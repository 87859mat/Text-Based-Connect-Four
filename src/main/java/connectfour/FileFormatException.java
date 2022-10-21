package connectfour;


/**
 * Signals that a file being read was not in the expected
 * 6 lines x 7 values/line comma-seperated format
 * @author Eyoel Matiows
 */
public class FileFormatException extends Exception {
    public FileFormatException() {
        System.out.print("ERROR: the file you are trying to load from is not ");
        System.out.println("correctly formatted for a connect four board\n");
    }
}