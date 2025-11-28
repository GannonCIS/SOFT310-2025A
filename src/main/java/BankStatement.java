import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class BankStatement {
    void bankStatementFun(int accNo) throws IOException {
        File file = new File("db/Bank Statement/acc_"+accNo+".txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
            System.out.println("\n");
            System.out.println("                           | Bank Statement |");
            System.out.println("---------------------------------------------------------------------------");
            System.out.printf("%-21s | %-6s | %-6s | %-7s | %-10s | %-8s%n", "Description", "Type", "Amount", "Remarks", "Date", "Time");
            System.out.println("---------------------------------------------------------------------------");
            while (scanner.hasNextLine()) {
                String trWLine = scanner.nextLine();
                if (trWLine.trim().isEmpty()) continue;
                String[] trLine = trWLine.split(" ");
                String description = (trLine.length>0?trLine[0]:"") + " " + (trLine.length>1?trLine[1]:"") + " " + (trLine.length>2?trLine[2]:"");
                String type = trLine.length>3?trLine[3]:"";
                String amount = (trLine.length>4?"$"+trLine[4]:"");
                String remarks = trLine.length>5?trLine[5]:"";
                String date = trLine.length>6?trLine[6]:"";
                String time = trLine.length>7?trLine[7]:"";
                System.out.printf("%-21s | %-6s | %-6s | %-7s | %-10s | %-8s%n", description, type, amount, remarks, date, time);
            }
            System.out.println("---------------------------------------------------------------------------");
        } catch (FileNotFoundException e) {
            System.out.println("No Transaction found!");
            exit(accNo);
        } finally {
            if (scanner != null) scanner.close();
        }
        exit(accNo);
    }

    void exit(int accNo) throws IOException {
        System.out.println("\n" + "Press Enter key to continue...");
        Scanner scanner1 = new Scanner(System.in);
        scanner1.nextLine();
        Main.menu(accNo);
    }

    // Test helper: returns true if statement file exists and has content
    public boolean bankStatementFunTest(String stmtFilePath) {
        File file = new File(stmtFilePath);
        return file.exists() && file.length() > 0;
    }
}
