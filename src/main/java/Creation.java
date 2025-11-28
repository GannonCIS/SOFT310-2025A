import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Creation {

    // Original user-facing method (uses db/)
    public void createAccFun() throws IOException {
        int accNo = accNoCreation("db/credentials.txt");
        String[] accLineInfo = getUserInfoFromUser();
        credWrite("db/credentials.txt", accNo, accLineInfo);
        balWrite("db/balanceDB.txt", accNo);
        userWrite("db/userDB.txt", accNo, accLineInfo);
        System.out.println("\nAccount created successfully!\n");
        System.out.println("Your account number is: " + accNo);
        System.out.println("Your password is: " + accLineInfo[8] + "\n");
        Main.menu(accNo);
    }

    // Test helper - minimal changes: accept file paths and userInfo array, returns accNo
    public int createAccFunTest(String credPath, String userPath, String balPath, String[] accLineInfo) throws Exception {
        int accNo = accNoCreation(credPath);
        credWrite(credPath, accNo, accLineInfo);
        balWrite(balPath, accNo);
        userWrite(userPath, accNo, accLineInfo);
        return accNo;
    }

    String[] getUserInfoFromUser () throws IOException {
        String[] accLineInfo = new String[9];
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter your Name: ");
        String fullName = scanner.nextLine();
        String[] fullNameArr = fullName.split(" ");
        if (fullNameArr.length == 2) {
            accLineInfo[0] = fullNameArr[0];
            accLineInfo[1] = fullNameArr[1];
        } else {
            System.out.println("Please provide both first name and last name.");
            return getUserInfoFromUser();
        }

        System.out.println("Enter your Date of Birth (YYYY-MM-DD): ");
        accLineInfo[2] = scanner.nextLine();
        System.out.println("Enter your Gender: ");
        accLineInfo[3] = scanner.nextLine();
        System.out.println("Enter your Address: ");
        accLineInfo[4] = scanner.nextLine();
        System.out.println("Enter your Phone Number: ");
        accLineInfo[5] = scanner.nextLine();
        System.out.println("Enter your Email: ");
        accLineInfo[6] = scanner.nextLine();
        System.out.println("Enter your Citizenship Number: ");
        accLineInfo[7] = scanner.nextLine();
        System.out.println("Create a Password for your Account: ");
        accLineInfo[8] = scanner.nextLine();
        return accLineInfo;
    }

    int accNoCreation (String credentialsPath) throws IOException {
        String lastLine = "";
        int accNo;
        File file = new File(credentialsPath);
        // create file if it doesn't exist so tests don't crash on read
        file.getParentFile().mkdirs();
        if (!file.exists()) file.createNewFile();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.trim().isEmpty()) lastLine = line;
        }
        scanner.close();

        if (Objects.equals(lastLine, "") || lastLine.trim().isEmpty()) {
            accNo = 1;
        } else {
            String[] subLine = lastLine.split(" ");
            accNo = Integer.parseInt(subLine[0]);
            accNo++;
        }
        return accNo;
    }

    void credWrite ( String credentialsPath, int accNo, String[] accLineInfo) throws IOException {
        File file = new File(credentialsPath);
        file.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(file, true);
        if (file.length() == 0) writer.write(accNo + " " + accLineInfo[8]);
        else writer.write("\n" + accNo + " " + accLineInfo[8]);
        writer.close();
    }

    void balWrite ( String balPath, int accNo) throws IOException {
        int initialBal = 69;
        File file = new File(balPath);
        file.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(file, true);
        if (file.length() == 0) writer.write(accNo + " " + initialBal);
        else writer.write("\n" + accNo + " " + initialBal);
        writer.close();
    }

    void userWrite ( String userPath, int accNo, String[] accLineInfo) throws IOException {
        File file = new File(userPath);
        file.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(file, true);
        if (file.length() == 0) writer.write(accNo + " ");
        else writer.write("\n" + accNo + " ");
        for (int i = 0; i < 8; i++) {
            writer.write(accLineInfo[i] + " ");
        }
        writer.close();
    }
}
