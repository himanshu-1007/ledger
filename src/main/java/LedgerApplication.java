import dtos.BalanceInputDto;
import dtos.BalanceOutputDto;
import dtos.LoanInputDto;
import dtos.PaymentInputDto;
import exception.PathNotFoundException;
import init.Initialization;
import interfaces.LedgerCommandInterface;

import java.io.*;

public class LedgerApplication {

    public static void main(String[] args) throws IOException {
        //Get file path
        String filePath = getAndValidatePath(args);
        // Initializing the application
        Initialization.init();
        //Creating ledger command interface
        LedgerCommandInterface ledgerCommandInterface = Initialization.getLedgerService();
        //Processing file
        readAndProcessFile(ledgerCommandInterface, filePath);

    }

    private static void readAndProcessFile(
            LedgerCommandInterface ledgerCommandInterface,
            String fileName
    ) throws IOException {
        File file = new File(fileName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            String[] splitString = st.split("\\s+");
            try {
                if (splitString[0].equals("LOAN")) {
                    LoanInputDto inputDto = new LoanInputDto(splitString[1],
                            splitString[2],
                            Double.parseDouble(splitString[3]),
                            Integer.parseInt(splitString[4]),
                            Double.parseDouble(splitString[5]));
                    ledgerCommandInterface.loan(inputDto);


                } else if (splitString[0].equals("BALANCE")) {
                    BalanceInputDto inputDto = new BalanceInputDto(splitString[1],
                            splitString[2],
                            Integer.parseInt(splitString[3]));
                    BalanceOutputDto outputDto = ledgerCommandInterface.balance(inputDto);
                    System.out.println(outputDto);


                } else if (splitString[0].equals("PAYMENT")) {
                    PaymentInputDto inputDto = new PaymentInputDto(splitString[1],
                            splitString[2],
                            Double.parseDouble(splitString[3]),
                            Integer.parseInt(splitString[4]));
                    ledgerCommandInterface.pay(inputDto);

                } else {
                    System.out.println("Unknown Command");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());

            }
        }
    }

    private static String getAndValidatePath(String args[]) {
        String filePath;
        try {
            filePath = args[0];
        } catch (Exception e) {
            throw new PathNotFoundException("File Path is missing");
        }

        if (filePath.isEmpty() || !new File(filePath).exists()) {
            throw new PathNotFoundException("File path is invalid or missing");
        }
        return filePath;
    }
}
