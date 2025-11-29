import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class DeletionControlFlowTest {

    private static final String TEST_CREDENTIALS = "test_credentials.txt";

    @BeforeEach
    void setup() throws Exception {
        Files.writeString(Paths.get(TEST_CREDENTIALS),
                "1001 pass1\n" +
                        "1002 pass2\n" +
                        "1003 pass3\n");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(Paths.get(TEST_CREDENTIALS));
    }

    private void accCloseFunTest(int accNo, String fileName) throws Exception {
        Deletion del = new Deletion();
        del.delLine(accNo, fileName);
    }

    @Test
    void testAccCloseFun_RemovesCorrectAccount() throws Exception {
        int accToDelete = 1002;

        accCloseFunTest(accToDelete, TEST_CREDENTIALS);

        String content = Files.readString(Paths.get(TEST_CREDENTIALS));

        assertFalse(content.contains("1002"));
        assertTrue(content.contains("1001"));
        assertTrue(content.contains("1003"));
    }

    @Test
    void testAccCloseFun_NoCrashIfAccNotFound() throws Exception {
        int accToDelete = 9999;

        assertDoesNotThrow(() ->
                accCloseFunTest(accToDelete, TEST_CREDENTIALS)
        );

        String content = Files.readString(Paths.get(TEST_CREDENTIALS));

        assertTrue(content.contains("1001"));
        assertTrue(content.contains("1002"));
        assertTrue(content.contains("1003"));
    }
}
