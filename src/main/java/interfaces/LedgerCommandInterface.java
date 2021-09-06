package interfaces;

import dtos.BalanceInputDto;
import dtos.BalanceOutputDto;
import dtos.LoanInputDto;
import dtos.PaymentInputDto;
import exception.DuplicateEntryException;
import exception.NotFoundException;

public interface LedgerCommandInterface {

    void loan(LoanInputDto inputDto) throws DuplicateEntryException;
    void pay(PaymentInputDto inputDto) throws NotFoundException;
    BalanceOutputDto balance(BalanceInputDto inputDto) throws NotFoundException;
}
