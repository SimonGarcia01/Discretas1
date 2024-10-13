package exceptions;

public class StackException extends Exception {
    public StackException() {
        //Default constructor
    }

    public StackException(String message) {
        super(message);
    }
}