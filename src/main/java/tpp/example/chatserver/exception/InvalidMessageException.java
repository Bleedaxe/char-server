package tpp.example.chatserver.exception;

public class InvalidMessageException extends RuntimeException {

    private static final String FORMAT = "Invalid message with type [%s] and payload [%s]";

    public InvalidMessageException(String type, String payload) {
        super(String.format(FORMAT, type, payload));
    }
}
