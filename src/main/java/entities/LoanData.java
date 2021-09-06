package entities;

import lombok.Data;


@Data
public class LoanData {
    private LoanKey loanKey;
    private Integer term;
    private Double rate;
    private Double principal;
    private LumpSumData lumpSumData;

    public LoanData(LoanKey loanKey, Integer term, Double rate, Double principal) {
        this.loanKey = loanKey;
        this.principal = principal;
        this.term = term;
        this.rate = rate;
        lumpSumData = new LumpSumData();
    }

    /**
     * Gives the total amount, (interest + principal)
     * @return
     */
    public Double getTotalAmount() {
        return this.principal + getInterest();
    }

    /**
     * Evaluates the interest
     * @return
     */
    public Double getInterest() {
        return (this.principal * this.term * this.rate) / 100;
    }

    /**
     * evaulate the EMI amount per monthly basis,ceiled to the nearest integer
     * @return
     */
    public Integer getEMIAmount() {

        return (int) Math.ceil(getTotalAmount() / (12 * term));
    }

    /**
     * Evaluate Total Amount paid up to the EMI Number
     * @param emiNumber
     * @return
     */

    public Double totalAmountPaidUptoEmiNumber(Integer emiNumber){
        Integer emiAmount = getEMIAmount();
        Double totalAmount = getTotalAmount();
        Double totalLumpSum = this.lumpSumData.fetchCumulativeSumUptoEmiNumber(emiNumber);
        Double approximation = emiAmount*emiNumber + totalLumpSum;
        if(approximation > totalAmount){
            return totalAmount;
        }else{
            return approximation;
        }
    }

    /**
     * Evaulates the EMI payments remaining after the specified EMI's are paid.
     * ExtraPayment of lumpSum amount, reduces the number of EMI to be paid, as the EMI amount is constant
     * @param emiNumber
     * @return
     */
    public Integer eMIPaymentsRemaining(Integer emiNumber){
        Double totalPaid = totalAmountPaidUptoEmiNumber(emiNumber);
        if(totalPaid == getTotalAmount()){
            return 0;
        }else{
            double emiRemaining = (getTotalAmount() - totalPaid)/getEMIAmount();
            emiRemaining = Math.ceil(emiRemaining);
            return (int) emiRemaining;
        }
    }


}
