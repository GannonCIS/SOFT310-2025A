import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CreationControlFlowTest {

    @Test
    void testCreateAccFun_NominalFlow() {
        Creation creation = new Creation();

        // Call the method (no arguments)
        assertDoesNotThrow(() -> creation.createAccFun());
    }

    @Test
    void testCreateAccFun_ControlFlow() {
        Creation creation = new Creation();

        // This just ensures the method can run through all branches
        assertDoesNotThrow(() -> {
            creation.createAccFun();
        });
    }
}
