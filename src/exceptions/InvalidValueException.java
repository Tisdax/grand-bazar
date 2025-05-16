package exceptions;

public class InvalidValueException extends Exception {
    private Object invalidValue;

    public InvalidValueException(String message, Object invalidValue) {
        super("La valeur " + invalidValue + " n'est pas valide. " + message);
        this.invalidValue = invalidValue;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }
}
