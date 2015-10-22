import java.util.Collection;

public class UnexpectedFieldException extends Exception {

  public UnexpectedFieldException() {
    super();
  }

  public UnexpectedFieldException(Collection<String> unexpectedFields) {
    super(unexpectedFields.toString());
  }

  public UnexpectedFieldException(String message) {
    super(message);
  }

  public UnexpectedFieldException(String message,
                                  Collection<String> unexpectedFields) {
    super(message + ": " + unexpectedFields.toString());
  }

}
