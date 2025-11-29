import org.junit.jupiter.api.*;
import java.io.File;
import java.util.Scanner;
import java.nio.file.*;
import static org.junit.jupiter.api.Assertions.*;

public class InAccNoControlFlowTest {

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

    private boolean loginAuthTest(String filePath, String acc, String pass) throws Exception {
        File file = new File(filePath);
        if (!file.exists()) return false;

        Scanner scanner = new Scanner(file);
        boolean loginBoo = false;
        boolean incPass = false;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] subLine = line.split(" ");
            if (acc.equals(subLine[0]) && pass.equals(subLine[1])) {
                loginBoo = true;
                break;
            } else if (acc.equals(subLine[0])) {
                incPass = true;
            }
        }

        scanner.close();
        return loginBoo;
    }

    @Test
    void testLoginAuth_Valid() throws Exception {
        boolean result = loginAuthTest(TEST_CREDENTIALS, "1001", "pass123");
        assertTrue(result);
    }

    @Test
    void testLoginAuth_WrongPassword() throws Exception {
        boolean result = loginAuthTest(TEST_CREDENTIALS, "1001", "wrong");
        assertFalse(result);
    }

    @Test
    void testLoginAuth_AccountNotFound() throws Exception {
        boolean result = loginAuthTest(TEST_CREDENTIALS, "9999", "nopass");
        assertFalse(result);
    }
}
