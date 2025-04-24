package dataAccess;

public class DBAccesException extends Exception {
    private String message;

    DBAccesException (String message) {
        super(message);
    }
}