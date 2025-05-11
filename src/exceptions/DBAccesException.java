package exceptions;

public class DBAccesException extends Exception {
    private String description;

    public DBAccesException (String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}