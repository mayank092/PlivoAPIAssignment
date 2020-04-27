package tools.exceptions;

public class InvalidAuthorizationTokenException extends RuntimeException {
    public InvalidAuthorizationTokenException(String message) { super(message); }
}
