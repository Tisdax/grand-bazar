package exceptions;

public class DAOException extends Exception {
    private String description;

    public DAOException(String message, String description) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}