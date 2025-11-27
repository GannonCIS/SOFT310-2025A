import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

public class MainTest {
 @Test
    public void Intro_JustBelowMin() throws IOException {
            System.setIn(new ByteArrayInputStream("0\n".getBytes()));
            try {
                Main.main(new String[0]);
            } catch (NoSuchElementException e) {
            }
    }

    @Test
    public void Intro_Min() throws IOException {
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));
        try {
            Main.main(new String[0]);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void Intro_Max() throws IOException {
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));
        try {
            Main.main(new String[0]);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void Intro_JustAboveMax() throws IOException {
        System.setIn(new ByteArrayInputStream("3\n".getBytes()));
        try {
            Main.main(new String[0]);
        } catch (NoSuchElementException e) {
        }
    }




}
