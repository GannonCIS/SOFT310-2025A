import org.junit.jupiter.api.*;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

public class InAccNoControlFlowTest {

    private static final String TEST_CREDENTIALS = "test_credentials.txt";

    @BeforeEach
    void setup() throws Exception {
        // Test credential file with two accounts
        Files.writeString(Paths.get(TEST_CREDENTIALS),
                "1001 pass123\n" +
                        "1002 hello123\n");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_CREDENTIALS));
    }

    // CONTROL FLOW CASE 1:
    // Account exists + correct password
    @Test
    void testLoginAuth_Valid() throws Exception {
        Login login = new Login();

        boolean result = login.loginAuthTest(TEST_CREDENTIALS, "1001", "pass123");
        assertTrue(result);
    }

    // CONTROL FLOW CASE 2:
    // Account exists + wrong password
    @Test
    void testLoginAuth_WrongPassword() throws Exception{
        Login login = new Login();

        boolean result = login.loginAuthTest(TEST_CREDENTIALS, "1001", "wrong");
        assertFalse(result);
    }

    // CONTROL FLOW CASE 3:
    // Account does not exist
    @Test
    void testLoginAuth_AccountNotFound() throws Exception {
        Login login = new Login();

        boolean result = login.loginAuthTest(TEST_CREDENTIALS, "9999", "nopass");
        assertFalse(result);
    }
}
