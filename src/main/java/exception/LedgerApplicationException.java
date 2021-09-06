package exception;

/**
 * all expection should throw this
 */
public class LedgerApplicationException extends RuntimeException{
    public LedgerApplicationException(String message) {
        super(message);
    }
}
