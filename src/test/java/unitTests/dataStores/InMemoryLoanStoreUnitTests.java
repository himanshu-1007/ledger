package unitTests.dataStores;

import dataStores.InMemoryLoanStore;
import entities.Bank;
import entities.Borrower;
import entities.LoanData;
import entities.LoanKey;
import org.junit.Assert;
import org.junit.Test;

public class InMemoryLoanStoreUnitTests {
    private InMemoryLoanStore inMemoryLoanStore = new InMemoryLoanStore();


    @Test
    public void testDataAddition(){
      LoanData loanData = new LoanData(new LoanKey(new Bank("HDFC"),new Borrower("ADAM")),2,2.0,1000.0);
      inMemoryLoanStore.grantLoan(loanData);
      //check if data is added in memory or not
      Assert.assertTrue(inMemoryLoanStore.getLoanDataBasedOnKey(new LoanKey(new Bank("HDFC"),new Borrower("ADAM"))) !=null);
    }
}
