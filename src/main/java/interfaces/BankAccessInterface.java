package interfaces;

import entities.Bank;

public interface BankAccessInterface {
    void addBank(Bank bank);

    Bank getBank(String name);
}
