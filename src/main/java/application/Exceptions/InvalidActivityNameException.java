package application.Exceptions;

public class InvalidActivityNameException extends Exception {
    public InvalidActivityNameException(String message) {
        super(message);
    }
}