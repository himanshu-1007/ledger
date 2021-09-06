package managers;

import entities.Borrower;
import interfaces.BorrowerAccessInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BorrowerManager {

    private BorrowerAccessInterface borrowerAccessInterface;


    public Borrower getBorrower(String borrowerName){
        return borrowerAccessInterface.getBorrower(borrowerName);
    }

    /**
     * private utility function, assumes that that borrower is not present,
     * If exposed to public, will need a check for existence
     * @param borrowerName
     * @return
     */
    private Borrower addBorrower(String borrowerName){
        Borrower borrower = new Borrower(borrowerName);
        borrowerAccessInterface.addBorrower(borrower);
        return borrower;
    }

    public Borrower getOrAddBorrower(String borrowerName){
        Borrower borrower = getBorrower(borrowerName);
        if(borrower == null){
            borrower = addBorrower(borrowerName);
        }
        return borrower;
    }
}
