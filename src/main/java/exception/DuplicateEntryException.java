package exception;

import enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class DuplicateEntryException extends LedgerApplicationException{

    private ExceptionEnum exceptionEnum;

    public DuplicateEntryException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;

    }
}
