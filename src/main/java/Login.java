import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Login {

    // TEST VERSION
    public boolean loginAuthTest(String credFile, String acc, String pass) throws Exception {
        File file = new File(credFile);
        if (!file.exists()) return false;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().trim().split(" ");
            if (split.length < 2) continue;
            if (split[0].equals(acc) && split[1].equals(pass)) {
                scanner.close();
                return true;     // success
            }
        }
        scanner.close();
        return false;            // fail
    }


    // NORMAL VERSION
    void loginFun() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your Account Number: ");
        int accNo = scanner.nextInt();

        System.out.print("Enter your Password: ");
        String pass = scanner.next();

        loginAuth(accNo, pass);
    }

    void loginAuth(int accNo, String pass) throws IOException {
        File file = new File("db/credentials.txt");
        Scanner scanner = new Scanner(file);

        boolean correctAcc = false;

        while (scanner.hasNextLine()) {
            String[] split = scanner.nextLine().split(" ");
            if (split.length < 2) continue;
            int fileAcc = Integer.parseInt(split[0]);

            if (fileAcc == accNo) {
                correctAcc = true;
                if (split[1].equals(pass)) {
                    System.out.println("Login Successful!!\n");
                    scanner.close();
                    Main.menu(accNo);
                    return;
                } else {
                    scanner.close();
                    System.out.println("Incorrect Password, try again.");
                    loginFun();
                    return;
                }
            }
        }
        scanner.close();

        if (!correctAcc) {
            System.out.println("Account not found.");
            loginFun();
        }
    }
}
