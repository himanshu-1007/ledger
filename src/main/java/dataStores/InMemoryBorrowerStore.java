package dataStores;

import entities.Borrower;
import entities.LoanData;
import entities.LoanKey;
import interfaces.BorrowerAccessInterface;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory store for storing all the borrower
 * Currently, name is the unique constraint
 */
public class InMemoryBorrowerStore implements BorrowerAccessInterface {
    private final Map<String, Borrower> borrowerMap = new HashMap<>();

    /**
     *
     * @param borrower
     */
    public void addBorrower(Borrower borrower) {
        borrowerMap.put(borrower.getName(), borrower);
    }

    /**
     *
     * @param name
     * @return
     */
    public Borrower getBorrower(String name) {
        return borrowerMap.get(name);
    }
}
