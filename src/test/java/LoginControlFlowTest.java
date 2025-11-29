import org.junit.jupiter.api.*;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginControlFlowTest {

    private static final String TEST_CREDENTIALS = "test_credentials.txt";

    @BeforeEach
    void setup() throws Exception {
        // Create a test credential file
        Files.writeString(Paths.get(TEST_CREDENTIALS),
                "1001 pass123\n" +
                        "1002 hello123\n");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_CREDENTIALS));
    }

    @Test
    void testLoginAuth_ValidCredentials() {
        Login login = new Login();

        assertDoesNotThrow(() ->
                login.loginAuth(1001, "pass123")
        );
    }

    @Test
    void testLoginAuth_InvalidPassword() {
        Login login = new Login();

        assertDoesNotThrow(() ->
                login.loginAuth(1001, "wrongpass")
        );
    }

    @Test
    void testLoginAuth_AccountNotFound() {
        Login login = new Login();

        assertDoesNotThrow(() ->
                login.loginAuth(9999, "anything")
        );
    }
}
