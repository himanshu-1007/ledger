package enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ExceptionEnum {
    DUPLICATE_LOAN_ENUM("LED001", "Loan cannot be granted to same user for the given bank more than once"),
    NO_LOAN_FOUND_ENUM("LED002", "No Loan sanctioned"),
    LUMP_SUM_AMOUNT_ENUM("LED003","LumpSum amount can't be greater than remaining amount to be paid"),
    BANK_ALREADY_EXISTS("LED04", "Bank already exists");

    ExceptionEnum(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    private String errorCode;
    private String message;
}
