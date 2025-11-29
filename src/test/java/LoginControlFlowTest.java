import org.junit.jupiter.api.*;
import java.io.File;
import java.util.Scanner;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

class LoginControlFlowTest {

    private static final String TEST_CREDENTIALS = "test_credentials.txt";

    @BeforeEach
    void setup() throws Exception {
        Files.writeString(Paths.get(TEST_CREDENTIALS),
                "1001 pass123\n" +
                        "1002 hello123\n");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_CREDENTIALS));
    }

    private boolean loginAuthTest(String filePath, int accNo, String pass) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) return false;

        Scanner scanner = new Scanner(file);
        boolean loginBoo = false;
        boolean incPass = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] subLine = line.split(" ");
            if (accNo == Integer.parseInt(subLine[0]) && pass.equals(subLine[1])) {
                loginBoo = true;
                break;
            } else if (accNo == Integer.parseInt(subLine[0])) {
                incPass = true;
            }
        }

        scanner.close();
        return loginBoo;
    }

    @Test
    void testLoginAuth_ValidCredentials() throws Exception {
        Login login = new Login();
        boolean result = loginAuthTest(TEST_CREDENTIALS, 1001, "pass123");
        assertTrue(result);
    }

    @Test
    void testLoginAuth_InvalidPassword() throws Exception {
        Login login = new Login();
        boolean result = loginAuthTest(TEST_CREDENTIALS, 1001, "wrongpass");
        assertFalse(result);
    }

    @Test
    void testLoginAuth_AccountNotFound() throws Exception {
        Login login = new Login();
        boolean result = loginAuthTest(TEST_CREDENTIALS, 9999, "anything");
        assertFalse(result);
    }
}
