import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Transaction {
    void transactionFun(int accNo) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Receiver's Account Number: ");
        int rAccNo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Amount: ");
        int tAmount = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Remarks: ");
        String tRemarks = scanner.nextLine();
        System.out.println("\n");
        allTransaction(accNo, rAccNo, tAmount, tRemarks);
    }

    void allTransaction(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        if (rAccCheck("db/balanceDB.txt", rAccNo)) {
            if (sAccBalCheck("db/balanceDB.txt", accNo, tAmount)) {
                transaction("db/balanceDB.txt", accNo, rAccNo, tAmount);
                writeTransaction(accNo, rAccNo, tAmount, tRemarks);
                System.out.println("Transaction Successful!");
                System.out.println("Press any key to continue...");
                Scanner tscanner = new Scanner(System.in);
                tscanner.nextLine();
                Main.menu(accNo);
            } else {
                System.out.println("Insufficient Balance!");
            }
        } else {
            System.out.println("Incorrect Account Number!");
        }
    }

    // Test-friendly overloaded versions (use file path params)
    public boolean transactionFunTest(String balFilePath, String stmtDir, int accNo, int rAccNo, int tAmount) throws IOException {
        if (!rAccCheck(balFilePath, rAccNo)) return false;
        if (!sAccBalCheck(balFilePath, accNo, tAmount)) return false;
        transaction(balFilePath, accNo, rAccNo, tAmount);
        debitWrite(accNo, rAccNo, tAmount, "test", stmtDir);
        creditWrite(accNo, rAccNo, tAmount, "test", stmtDir);
        return true;
    }

    boolean rAccCheck(String balFilePath, int rAccNo) throws FileNotFoundException {
        File file = new File(balFilePath);
        if (!file.exists()) return false;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] subLine = line.split(" ");
            int a = Integer.parseInt(subLine[0]);
            if (rAccNo == a) {
                scanner.close();
                return true;
            }
        }
        scanner.close();
        return false;
    }

    boolean rAccCheck(int rAccNo) throws FileNotFoundException {
        return rAccCheck("db/balanceDB.txt", rAccNo);
    }

    boolean sAccBalCheck(String balFilePath, int accNo, int tAmount) throws FileNotFoundException {
        File file = new File(balFilePath);
        if (!file.exists()) return false;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] subLine = line.split(" ");
            int a = Integer.parseInt(subLine[0]);
            int b = Integer.parseInt(subLine[1]);
            if (accNo == a) {
                scanner.close();
                return tAmount <= b;
            }
        }
        scanner.close();
        return false;
    }

    boolean sAccBalCheck(int accNo, int tAmount) throws FileNotFoundException {
        return sAccBalCheck("db/balanceDB.txt", accNo, tAmount);
    }

    void transaction(String balFilePath, int accNo, int rAccNo, int tAmount) throws IOException {
        File file = new File(balFilePath);
        if (!file.exists()) return;
        Scanner scanner = new Scanner(file);
        String newInfo = "";
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] subLine = line.split(" ");
            int a = Integer.parseInt(subLine[0]);
            int b = Integer.parseInt(subLine[1]);
            if (accNo == a) {
                b = b - tAmount;
            } else if (rAccNo == a) {
                b = b + tAmount;
            }
            String newLine = a + " " + b;
            newInfo += newLine + "\n";
        }
        scanner.close();

        Writer writer = new FileWriter(balFilePath);
        writer.write(newInfo);
        writer.close();
    }

    void writeTransaction(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        debitWrite(accNo, rAccNo, tAmount, tRemarks);
        creditWrite(accNo, rAccNo, tAmount, tRemarks);
    }

    void debitWrite(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        debitWrite(accNo, rAccNo, tAmount, tRemarks, "db/Bank Statement/");
    }

    void creditWrite(int accNo, int rAccNo, int tAmount, String tRemarks) throws IOException {
        creditWrite(accNo, rAccNo, tAmount, tRemarks, "db/Bank Statement/");
    }

    void debitWrite(int accNo, int rAccNo, int tAmount, String tRemarks, String stmtDir) throws IOException {
        String description = ("Transfer to " + rAccNo);
        String type = "Debit";
        String date = java.time.LocalDate.now().toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = formatter.format(now);
        File dir = new File(stmtDir);
        dir.mkdirs();
        Writer writer = new FileWriter(stmtDir + "acc_"+accNo+".txt", true);
        writer.write(description + " " + type + " " + tAmount + " " + tRemarks + " " + date + " " + time + "\n");
        writer.close();
    }

    void creditWrite(int accNo, int rAccNo, int tAmount, String tRemarks, String stmtDir) throws IOException {
        String description = ("Transfer from " + accNo);
        String type = "Credit";
        String date = java.time.LocalDate.now().toString();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String time = formatter.format(now);
        File dir = new File(stmtDir);
        dir.mkdirs();
        Writer writer = new FileWriter(stmtDir + "acc_"+rAccNo+".txt",true);
        writer.write(description + " " + type + " " + tAmount + " " + tRemarks + " " + date + " " + time + "\n");
        writer.close();
    }
}
