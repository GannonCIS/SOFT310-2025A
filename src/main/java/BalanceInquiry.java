import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class BalanceInquiry {
    void balanceInquiryFun(int accNo) throws IOException {
        int accBalance = balanceInquiryFunTest("db/balanceDB.txt", accNo);
        if (accBalance == -1) {
            System.out.println("We're having some issues, Try Again!");
            System.exit(0);
        } else {
            System.out.println("┌───────────────────────────────┐");
            System.out.println("  Your current balance is $"+ accBalance +"   ");
            System.out.println("└───────────────────────────────┘");
            System.out.println("Press Enter key to continue...");
            Scanner scanner1 = new Scanner(System.in);
            scanner1.nextLine();
            Main.menu(accNo);
        }
    }

    // Test helper (reads from a specified file and returns balance or -1)
    public int balanceInquiryFunTest(String balFilePath, int accNo) throws IOException {
        File file = new File(balFilePath);
        if (!file.exists()) return -1;
        Scanner scanner = new Scanner(file);
        int accBalance = -1;
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;
            String[] subLine = line.split(" ");
            if (accNo == Integer.parseInt(subLine[0])) {
                accBalance = Integer.parseInt(subLine[1]);
                break;
            }
        }
        scanner.close();
        return accBalance;
    }
}
