import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class DeletionControlFlowTest {

    private static final String TEST_CREDENTIALS = "test_credentials.txt";

    @BeforeEach
    void setup() throws Exception {
        // Create a test credentials file with sample accounts
        Files.writeString(Paths.get(TEST_CREDENTIALS),
                "1001 pass1\n" +
                        "1002 pass2\n" +
                        "1003 pass3\n");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_CREDENTIALS));
    }

    @Test
    void testAccCloseFun_RemovesCorrectAccount() throws Exception {
        Deletion del = new Deletion();

        int accToDelete = 1002;

        del.accCloseFunTest(accToDelete, TEST_CREDENTIALS);

        String content = Files.readString(Paths.get(TEST_CREDENTIALS));

        assertFalse(content.contains("1002"), "File should not contain deleted account");
        assertTrue(content.contains("1001"), "Other accounts must remain");
        assertTrue(content.contains("1003"), "Other accounts must remain");
    }

    @Test
    void testAccCloseFun_NoCrashIfAccNotFound() throws Exception {
        Deletion del = new Deletion();

        // Account that does NOT exist
        int accToDelete = 9999;

        // Should not throw an error
        assertDoesNotThrow(() ->
                del.accCloseFunTest(accToDelete, TEST_CREDENTIALS)
        );

        // File should remain unchanged
        String content = Files.readString(Paths.get(TEST_CREDENTIALS));

        assertTrue(content.contains("1001"));
        assertTrue(content.contains("1002"));
        assertTrue(content.contains("1003"));
    }
}
