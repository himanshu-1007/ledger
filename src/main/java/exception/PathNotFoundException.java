package exception;

public class PathNotFoundException extends LedgerApplicationException{

    public PathNotFoundException(String message) {
        super(message);
    }
}
