package exception;

import enums.ExceptionEnum;

public class ValidationException extends LedgerApplicationException{
    private ExceptionEnum exceptionEnum;

    public ValidationException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;

    }
}
