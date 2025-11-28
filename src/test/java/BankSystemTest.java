// BankSystemTest.java -- JUnit 5
import org.junit.jupiter.api.*;
import java.nio.file.*;
import java.io.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankSystemTest {

    private static final String MOCK_DIR = "mockdb";
    private static final String CREDENTIALS = MOCK_DIR + "/credentials.txt";
    private static final String USERDB = MOCK_DIR + "/userDB.txt";
    private static final String BALANCEDB = MOCK_DIR + "/balanceDB.txt";
    private static final String STMT_DIR = MOCK_DIR + "/Bank Statement/";

    @BeforeAll
    static void beforeAll() throws Exception {
        Files.createDirectories(Paths.get(MOCK_DIR));
        Files.createDirectories(Paths.get(STMT_DIR));
        Files.writeString(Paths.get(CREDENTIALS), "");
        Files.writeString(Paths.get(USERDB), "");
        Files.writeString(Paths.get(BALANCEDB), "");
    }

    @Test
    @Order(1)
    void testCreateAccount() throws Exception {
        Creation creation = new Creation();
        String[] info = new String[]{
                "John", "Doe", "1990-01-01", "M", "123 Street", "555-0000", "j@example.com", "CITIZEN123", "pass123"
        };

        int accNo = creation.createAccFunTest(CREDENTIALS, USERDB, BALANCEDB, info);
        assertTrue(accNo >= 1, "Account number should be >= 1");

        String credContent = Files.readString(Paths.get(CREDENTIALS));
        assertTrue(credContent.contains(String.valueOf(accNo)), "Credentials should contain new accNo");

        String userContent = Files.readString(Paths.get(USERDB));
        assertTrue(userContent.contains(String.valueOf(accNo)), "UserDB should contain new accNo");

        String balContent = Files.readString(Paths.get(BALANCEDB));
        assertTrue(balContent.contains(String.valueOf(accNo)), "BalanceDB should contain new accNo");
    }

    @Test
    @Order(2)
    void testLoginSuccess() throws Exception {
        Login login = new Login();
        String firstLine = Files.readAllLines(Paths.get(CREDENTIALS))
                .stream().filter(l -> !l.isBlank())
                .findFirst().orElseThrow(() -> new IllegalStateException("No account in credentials file"));
        String[] parts = firstLine.split(" ");
        String acc = parts[0];
        String pass = parts[1];

        boolean ok = login.loginAuthTest(CREDENTIALS, acc, pass);
        assertTrue(ok, "Login should succeed with right credentials");
    }

    @Test
    @Order(3)
    void testBalanceInquiry() throws Exception {
        BalanceInquiry b = new BalanceInquiry();
        String firstLine = Files.readAllLines(Paths.get(CREDENTIALS))
                .stream().filter(l -> !l.isBlank())
                .findFirst().orElseThrow(() -> new IllegalStateException("No account in credentials file"));
        int accNo = Integer.parseInt(firstLine.split(" ")[0]);

        int bal = b.balanceInquiryFunTest(BALANCEDB, accNo);
        assertTrue(bal >= 0, "Balance should be >= 0");
    }

    @Test
    @Order(4)
    void testFundTransfer() throws Exception {
        // Ensure there is at least one account
        if (Files.readAllLines(Paths.get(CREDENTIALS))
                .stream().filter(l -> !l.isBlank()).count() == 0) {
            Creation creation = new Creation();
            String[] info = new String[]{
                    "John", "Doe", "1990-01-01", "M", "123 Street", "555-0000", "j@example.com", "CITIZEN123", "pass123"
            };
            creation.createAccFunTest(CREDENTIALS, USERDB, BALANCEDB, info);
        }

        // Get sender
        String firstLine = Files.readAllLines(Paths.get(CREDENTIALS))
                .stream().filter(l -> !l.isBlank())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No account in credentials file"));
        int sender = Integer.parseInt(firstLine.split(" ")[0]);
        int receiver = sender + 1;

        // Append receiver account
        Files.writeString(Paths.get(CREDENTIALS), "\n" + receiver + " passR", StandardOpenOption.APPEND);
        Files.writeString(Paths.get(USERDB), "\n" + receiver + " Receiver Test 1990-01-01 M Addr 555-1111 r@example.com IDR", StandardOpenOption.APPEND);
        Files.writeString(Paths.get(BALANCEDB), "\n" + receiver + " 50", StandardOpenOption.APPEND);

        // Overwrite balances
        String senderLines = sender + " 200\n" + receiver + " 50\n";
        Files.writeString(Paths.get(BALANCEDB), senderLines);

        Transaction t = new Transaction();
        boolean ok = t.transactionFunTest(BALANCEDB, STMT_DIR, sender, receiver, 100);
        assertTrue(ok, "Transaction should succeed");

        String balContent = Files.readString(Paths.get(BALANCEDB));
        assertTrue(balContent.contains(sender + " 100"), "Sender should have 100 left");
        assertTrue(balContent.contains(receiver + " 150"), "Receiver should have 150");

        assertTrue(Files.exists(Paths.get(STMT_DIR + "acc_" + sender + ".txt")));
        assertTrue(Files.exists(Paths.get(STMT_DIR + "acc_" + receiver + ".txt")));
    }

    @Test
    @Order(5)
    void testAccountDeletion() throws Exception {
        String firstLine = Files.readAllLines(Paths.get(CREDENTIALS))
                .stream().filter(l -> !l.isBlank())
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("No account in credentials file"));
        int acc1 = Integer.parseInt(firstLine.split(" ")[0]);
        int toDelete = acc1 + 1; // receiver

        Deletion del = new Deletion();
        del.accCloseFunTest(toDelete, CREDENTIALS);
        del.delLineTest(USERDB, toDelete);
        del.delLineTest(BALANCEDB, toDelete);

        assertFalse(Files.readAllLines(Paths.get(CREDENTIALS))
                        .stream().anyMatch(line -> line.startsWith(toDelete + " ")),
                "Credential should not contain deleted acc");

        assertFalse(Files.readAllLines(Paths.get(USERDB))
                        .stream().anyMatch(line -> line.startsWith(toDelete + " ")),
                "UserDB should not contain deleted acc");

        assertFalse(Files.readAllLines(Paths.get(BALANCEDB))
                        .stream().anyMatch(line -> line.startsWith(toDelete + " ")),
                "BalanceDB should not contain deleted acc");
    }
} // end BankSystemTest class
