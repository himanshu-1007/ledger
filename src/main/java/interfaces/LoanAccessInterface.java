package interfaces;

import entities.LoanData;
import entities.LoanKey;
import entities.LumpSumData;

public interface LoanAccessInterface {
    LoanData getLoanDataBasedOnKey(LoanKey loanKey) ;
    void grantLoan(LoanData loanData);
    void storeLumSumData(LumpSumData.LumpSumEntry lumpSumEntry, LoanData loanData);
}
