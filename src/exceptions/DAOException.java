package exceptions;

public class DAOException extends Exception {
    private String description;

    public DAOException(String description, String message) {
        super(message);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}