package connectfour;

public class InvalidContentException extends Exception{
    public InvalidContentException() {
        System.out.println("ERROR: The file you are trying to load contains invalid characters");
        System.out.println("(i.e. they contain characters that are not '0', '1', '2', or ','\n");
    }
}
