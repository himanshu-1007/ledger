package exception;

import enums.ExceptionEnum;
import lombok.Getter;

@Getter
public class NotFoundException extends LedgerApplicationException{
    private ExceptionEnum exceptionEnum;

    public NotFoundException(ExceptionEnum exceptionEnum){
        super(exceptionEnum.getMessage());
        this.exceptionEnum = exceptionEnum;

    }
}
