package managers;

import entities.LoanData;
import entities.LoanKey;
import entities.LumpSumData;
import interfaces.LoanAccessInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LoanManager {

    private LoanAccessInterface loanAccessInterface;

    public LoanData getLoan(LoanKey loanKey) {
        return loanAccessInterface.getLoanDataBasedOnKey(loanKey);
    }

    public void grantLoan(LoanData loanData) {
        loanAccessInterface.grantLoan(loanData);
    }

    public void storeLumSumData(LumpSumData.LumpSumEntry lumpSumEntry, LoanData loanData) {
        loanAccessInterface.storeLumSumData(lumpSumEntry, loanData);
    }
}
