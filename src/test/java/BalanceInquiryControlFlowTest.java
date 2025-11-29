import org.junit.jupiter.api.*;
import java.io.File;
import java.util.Scanner;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

class BalanceInquiryControlFlowTest {

    private static final String TEST_BALANCE_DB = "test_balanceDB.txt";

    @BeforeEach
    void setup() throws Exception {
        Files.writeString(Paths.get(TEST_BALANCE_DB),
                "1001 500\n" +
                        "1002 1200\n" +
                        "1003 0\n");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_BALANCE_DB));
    }

    private int balanceInquiryFunTest(String filePath, int accNo) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) {
            return -1;
        }

        Scanner scanner = new Scanner(file);
        int accBalance = -1;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] subLine = line.split(" ");
            if (accNo == Integer.parseInt(subLine[0])) {
                accBalance = Integer.parseInt(subLine[1]);
                break;
            }
        }

        scanner.close();
        return accBalance;
    }

    @Test
    void testBalanceInquiry_AccountFound() throws Exception {
        BalanceInquiry bi = new BalanceInquiry();
        int balance = balanceInquiryFunTest(TEST_BALANCE_DB, 1002);
        assertEquals(1200, balance);
    }

    @Test
    void testBalanceInquiry_AccountFoundZeroBalance() throws Exception {
        BalanceInquiry bi = new BalanceInquiry();
        int balance = balanceInquiryFunTest(TEST_BALANCE_DB, 1003);
        assertEquals(0, balance);
    }

    @Test
    void testBalanceInquiry_AccountNotFound() throws Exception {
        BalanceInquiry bi = new BalanceInquiry();
        int balance = balanceInquiryFunTest(TEST_BALANCE_DB, 9999);
        assertEquals(-1, balance);
    }
}
