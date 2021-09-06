package dataStores;

import entities.LoanData;
import entities.LoanKey;
import entities.LumpSumData;
import interfaces.LoanAccessInterface;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory store for storing loans
 */
public class InMemoryLoanStore implements LoanAccessInterface {

    private final Map<LoanKey, LoanData> loanDataMap = new HashMap<>();


    public LoanData getLoanDataBasedOnKey(LoanKey loanKey){
       return loanDataMap.get(loanKey);

    }

    public void grantLoan(LoanData loanData){
        loanDataMap.put(loanData.getLoanKey(),loanData);

    }

    public void storeLumSumData(LumpSumData.LumpSumEntry lumpSumEntry, LoanData loanData){
        loanData.getLumpSumData().addData(lumpSumEntry);
    }







}
