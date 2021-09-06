package managers;

import entities.Bank;
import interfaces.BankAccessInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BankManager {

    private BankAccessInterface bankStore;

    public Bank getBank(String bankName) {
        return bankStore.getBank(bankName);
    }

    /**
     * internal function, hence not checking for bank existence
     * Idf exposed to public then will need a check to make sure, bank doesn't exists already
     * @param bankName
     * @return
     */
    private Bank addBank(String bankName){
        Bank bank = new Bank(bankName);
        bankStore.addBank(bank);
        return bank;
    }

    public Bank getOrAddBank(String bankName){
        Bank bank = getBank(bankName);
        if(bank == null){
            bank = addBank(bankName);
        }
        return bank;
    }
}
