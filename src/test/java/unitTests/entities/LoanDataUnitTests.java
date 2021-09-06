package unitTests.entities;

import entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LoanDataUnitTests {
    LoanData loanData;

    @Before
    public void init() {
        loanData = new LoanData(new LoanKey(new Bank("HDFC"), new Borrower("ADAM")), 2, 2.0, 1000.0);
    }

    @Test
    public void testGetTotalAmount() {
        Double amountExpected = 1040.0;
        Assert.assertEquals(loanData.getTotalAmount(), amountExpected);
    }

    @Test
    public void testGetInterestAmount() {
        Double expectedInterest = 40.0;
        Assert.assertEquals(loanData.getInterest(), expectedInterest);
    }

    @Test
    public void testGetEMIAmount() {
        Integer expectedEMI = 44;
        Assert.assertEquals(loanData.getEMIAmount(), expectedEMI);
    }

    @Test
    public void testGetTotalAmountToBePaidUptoEMiNumberWithNoLumsum() {

        Integer emiNumber = 5;
        Double expectedAmount = 220.0;
        Assert.assertEquals(loanData.totalAmountPaidUptoEmiNumber(emiNumber), expectedAmount);

    }

    @Test
    public void testGetTotalAmountToBePaidUptoEMiNumberWithLumpSum() {

        Integer emiNumber = 5;
        loanData.getLumpSumData().addData(new LumpSumData.LumpSumEntry(100.0, 3));
        loanData.getLumpSumData().addData(new LumpSumData.LumpSumEntry(50.0, 5));
        Double expectedAmount = 370.0;
        Assert.assertEquals(loanData.totalAmountPaidUptoEmiNumber(emiNumber), expectedAmount);

    }

    @Test
    public void testEMISLEFTWithNoLumpSum() {
        Integer emiNumber = 5;
        Integer expectedEMISLeft = 19;
        Assert.assertEquals(loanData.eMIPaymentsRemaining(emiNumber), expectedEMISLeft);

    }

    @Test
    public void testEMISLEFTWithNoLumpSumCase2() {
        Integer emiNumber = 20;
        loanData.setPrincipal(350.0);
        Integer expectedEMISLeft = 3;
        Assert.assertEquals(loanData.eMIPaymentsRemaining(emiNumber), expectedEMISLeft);
    }


    @Test
    public void testEMISLeftWithLumpSum() {
        loanData.getLumpSumData().addData(new LumpSumData.LumpSumEntry(100.0, 3));
        Integer emiNumber = 5;
        Integer expectedEMISLeft = 17;
        Assert.assertEquals(loanData.eMIPaymentsRemaining(emiNumber), expectedEMISLeft);

    }

}
