package unitTests.services;

import dataStores.InMemoryBankStore;
import dataStores.InMemoryBorrowerStore;
import dataStores.InMemoryLoanStore;
import dtos.BalanceInputDto;
import dtos.BalanceOutputDto;
import dtos.LoanInputDto;
import dtos.PaymentInputDto;
import enums.ExceptionEnum;
import exception.DuplicateEntryException;
import exception.NotFoundException;
import exception.ValidationException;
import managers.BankManager;
import managers.BorrowerManager;
import managers.LoanManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import services.LedgerService;

/**
 * Will not mock any dependency as everything is inmemory
 */
public class LedgerServiceTest {

    LedgerService ledgerService ;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void init(){
        ledgerService = new LedgerService(
                new LoanManager(new InMemoryLoanStore()),
                new BankManager(new InMemoryBankStore()),
                new BorrowerManager(new InMemoryBorrowerStore())
        );
    }

    @Test
    public void grantLoanSuccessCase(){
        LoanInputDto inputDto = new LoanInputDto("HDFC","Adam",1000.0,2,4.0);
        ledgerService.loan(inputDto);
    }
    @Test
    public void grantLoanFailureDuplicateScenario(){
        thrown.expect(DuplicateEntryException.class);
        thrown.expectMessage(ExceptionEnum.DUPLICATE_LOAN_ENUM.getMessage());
        LoanInputDto inputDto = new LoanInputDto("HDFC","Adam",1000.0,2,4.0);
        ledgerService.loan(inputDto);
        //granting the loan to same user for a given bank would give an exception
        ledgerService.loan(inputDto);
    }
    @Test
    public void payLumpSumSuccess(){
        LoanInputDto inputDto = new LoanInputDto("HDFC","Adam",1000.0,2,4.0);
        ledgerService.loan(inputDto);
        PaymentInputDto pa =new PaymentInputDto("HDFC","Adam",300.0,2);
        ledgerService.pay(pa);

    }
    @Test
    public void payLumpSumFailureScenarioLoanNotSanctioned(){
        thrown.expect(NotFoundException.class);
        thrown.expectMessage(ExceptionEnum.NO_LOAN_FOUND_ENUM.getMessage());
        PaymentInputDto pa =new PaymentInputDto("HDFC","Adam",300.0,2);
        ledgerService.pay(pa);
    }

    @Test
    public void payLumpSumFailureScenarioLumpSumGtThanRemaining(){
        thrown.expect(ValidationException.class);
        thrown.expectMessage(ExceptionEnum.LUMP_SUM_AMOUNT_ENUM.getMessage());
        LoanInputDto inputDto = new LoanInputDto("HDFC","Adam",1000.0,2,4.0);
        ledgerService.loan(inputDto);
        PaymentInputDto pa =new PaymentInputDto("HDFC","Adam",1000.0,10);
        ledgerService.pay(pa);
    }

    @Test
    public void balanceSuccessScenario(){
        LoanInputDto inputDto = new LoanInputDto("HDFC","Adam",1000.0,2,4.0);
        ledgerService.loan(inputDto);

        BalanceInputDto balanceInputDto = new BalanceInputDto("HDFC","Adam",6);
        BalanceOutputDto expectedDto = new BalanceOutputDto("HDFC","Adam",270.0,18);
        BalanceOutputDto observedDto = ledgerService.balance(balanceInputDto);
        Assert.assertTrue(expectedDto.getAmountPaid() == expectedDto.getAmountPaid() &&
                expectedDto.getEMILeft() == observedDto.getEMILeft());

    }





}
