package exceptions;

public class InvalidAddressException extends Exception {
    private Object invalidValue;

    public InvalidAddressException(String message, Object invalidValue) {
        super("La valeur " + invalidValue + " n'est pas valide. " + message);
        this.invalidValue = invalidValue;
    }

    public Object getInvalidValue() {
        return invalidValue;
    }
}
