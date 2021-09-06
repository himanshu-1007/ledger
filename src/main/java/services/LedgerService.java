package services;

import dtos.BalanceInputDto;
import dtos.BalanceOutputDto;
import dtos.LoanInputDto;
import dtos.PaymentInputDto;
import entities.LoanData;
import entities.LoanKey;
import entities.LumpSumData;
import enums.ExceptionEnum;
import exception.DuplicateEntryException;
import exception.NotFoundException;
import exception.ValidationException;
import interfaces.LedgerCommandInterface;
import lombok.AllArgsConstructor;
import managers.BankManager;
import managers.BorrowerManager;
import managers.LoanManager;

/**
 * Implements Commands Specified from Input file
 */
@AllArgsConstructor
public class LedgerService implements LedgerCommandInterface {
    private LoanManager loanManager;
    private BankManager bankManager;
    private BorrowerManager borrowerManager;


    /**
     * Issues a new Loan to a user for a specific bank
     * Loan can't be issued twice to the same user for the given bank
     *
     * @param inputDto
     * @throws DuplicateEntryException
     */
    public void loan(LoanInputDto inputDto) throws DuplicateEntryException {
        LoanKey key = getLoanKey(inputDto.getBankName(), inputDto.getBorrowerName());

        if (loanManager.getLoan(key) != null) {
            throw new DuplicateEntryException(ExceptionEnum.DUPLICATE_LOAN_ENUM);
        }
        LoanData loanData = new LoanData(key, inputDto.getTerm(), inputDto.getRate(), inputDto.getPrincipal());
        loanManager.grantLoan(loanData);
    }

    /**
     * User issues a lumpSum payment for the loan given
     *
     * @param inputDto
     * @throws NotFoundException
     */
    public void pay(PaymentInputDto inputDto) throws NotFoundException,ValidationException {

        LoanKey key = getLoanKey(inputDto.getBankName(), inputDto.getBorrowerName());

        LoanData data = loanManager.getLoan(key);

        if (null == data) {
            throw new NotFoundException(ExceptionEnum.NO_LOAN_FOUND_ENUM);
        } else {
            Double remainingAmount = data.getTotalAmount() - data.totalAmountPaidUptoEmiNumber(inputDto.getEMINumber());
            if (remainingAmount < inputDto.getLumpSum()) {
                throw new ValidationException(ExceptionEnum.LUMP_SUM_AMOUNT_ENUM);
            }
            loanManager.storeLumSumData(new LumpSumData.LumpSumEntry(inputDto.getLumpSum(), inputDto.getEMINumber()), data);
        }


    }

    /**
     * Output the amount paid and number of EMIs remaining for the loan issued to the user
     * by the given bank after specified number of EMIS are paid
     *
     * @param inputDto
     * @return
     * @throws NotFoundException
     */
    public BalanceOutputDto balance(BalanceInputDto inputDto) throws NotFoundException {
        LoanKey key = getLoanKey(inputDto.getBankName(), inputDto.getBorrowerName());
        LoanData data = loanManager.getLoan(key);
        if (null == data) {
            throw new NotFoundException(ExceptionEnum.NO_LOAN_FOUND_ENUM);
        } else {
            return new BalanceOutputDto(
                    inputDto.getBankName(),
                    inputDto.getBorrowerName(),
                    data.totalAmountPaidUptoEmiNumber(inputDto.getEMINumber()),
                    data.eMIPaymentsRemaining(inputDto.getEMINumber())
            );
        }

    }

    /**
     * private utility
     *
     * @return
     */
    private LoanKey getLoanKey(String bankName, String borrowerName) {
        return new LoanKey(
                bankManager.getOrAddBank(bankName),
                borrowerManager.getOrAddBorrower(borrowerName)
        );
    }


}
