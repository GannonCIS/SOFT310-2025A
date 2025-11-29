import org.junit.jupiter.api.*;
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

    @Test
    void testBalanceInquiry_AccountFound() throws Exception {
        BalanceInquiry bi = new BalanceInquiry();

        int balance = bi.balanceInquiryFunTest(TEST_BALANCE_DB, 1002);

        assertEquals(1200, balance);
    }

    @Test
    void testBalanceInquiry_AccountFoundZeroBalance() throws Exception {
        BalanceInquiry bi = new BalanceInquiry();

        int balance = bi.balanceInquiryFunTest(TEST_BALANCE_DB, 1003);

        assertEquals(0, balance);
    }

    @Test
    void testBalanceInquiry_AccountNotFound() throws Exception {
        BalanceInquiry bi = new BalanceInquiry();

        int balance = bi.balanceInquiryFunTest(TEST_BALANCE_DB, 9999);

        assertEquals(-1, balance);
    }
}
