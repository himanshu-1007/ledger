package interfaces;

import entities.Borrower;

public interface BorrowerAccessInterface {
    void addBorrower(Borrower borrower);

    Borrower getBorrower(String name);

}
