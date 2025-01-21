package BankFunctions;

import java.util.ArrayList;
import java.util.List;

public class TransactionLog {
    List<String> logs = new ArrayList<>();
    
    public void printTransactionLogs() {

        for (String log : logs) {
            System.out.println(log);
        }
    }

    public void addLog(String transactionDetails) {
        logs.add(transactionDetails);
    }

}
