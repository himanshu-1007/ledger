package dataStores;

import entities.Bank;
import interfaces.BankAccessInterface;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory store for Bank,
 * Currently name is the unique constraint
 */
@Data
public class InMemoryBankStore implements BankAccessInterface {
    private final Map<String, Bank> borrowerMap = new HashMap<>();

    /**
     *
     * @param bank
     */
    public void addBank(Bank bank){
        borrowerMap.put(bank.getName(),bank);
    }

    /**
     *
     * @param name
     * @return
     */
    public Bank getBank(String name){
        return borrowerMap.get(name);
    }


}


