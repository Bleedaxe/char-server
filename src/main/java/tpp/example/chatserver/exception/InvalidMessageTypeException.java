package tpp.example.chatserver.exception;

public class InvalidMessageTypeException extends RuntimeException {

  private static final String FORMAT = "Invalid message type [%s]";

  public InvalidMessageTypeException(String type) {
    super(String.format(FORMAT, type));
  }
}
