package dataAccess;

public class DBAccesException extends Exception {
    private String message;

    public DBAccesException (String message) {
        super(message);
    }
}