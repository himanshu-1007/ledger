package init;

import dataStores.InMemoryBankStore;
import dataStores.InMemoryBorrowerStore;
import dataStores.InMemoryLoanStore;
import interfaces.LedgerCommandInterface;
import managers.BankManager;
import managers.BorrowerManager;
import managers.LoanManager;
import services.LedgerService;


/**
 * Idea is to add sigletons to the application
 */
public class Initialization {
    private static LedgerCommandInterface ledgerCommandInterface;

    public static void init() {
        ledgerCommandInterface = new LedgerService(
                new LoanManager(
                        new InMemoryLoanStore()
                ), new BankManager(
                new InMemoryBankStore()),
                new BorrowerManager(
                        new InMemoryBorrowerStore()
                ));
    }

    public static LedgerCommandInterface getLedgerService() {
        return ledgerCommandInterface;
    }

}
