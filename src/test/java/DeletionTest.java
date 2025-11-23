import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.NoSuchElementException;
import static org.junit.Assert.fail;
import java.io.FileWriter;
import java.util.Scanner;

@RunWith(MockitoJUnitRunner.class)
public class DeletionTest {

    @Test
    public void testAccCloseFun_UserWantsToDeleteAcc_Types1() throws Exception {
        int accNo = 100;
        String fileName = "onehundred.txt";
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        Deletion deletion = Mockito.spy(new Deletion());
        Mockito.doNothing().when(deletion).delLine(accNo, fileName);
        deletion.accCloseFun(accNo, fileName);
        Mockito.verify(deletion, Mockito.times(1)).delLine(accNo, fileName);
    }

    @Test
    public void testAccCloseFun_UserDoesntWantToDeleteAcc_Types2() throws Exception {
        int accNo = 200;
        String fileName = "twohundred.txt";
        System.setIn(new ByteArrayInputStream("2\n".getBytes()));

        Deletion deletion = Mockito.spy(new Deletion());

        try {
            deletion.accCloseFun(accNo, fileName);
            fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {

        }

        Mockito.verify(deletion, Mockito.never()).delLine(accNo, fileName);
    }

    @Test
    public void testAccCloseFun_UserEntersNumberThatIsNot1_TypesAnyInt() throws Exception {
        int accNo = 300;
        String fileName = "threehundred.txt";

        System.setIn(new ByteArrayInputStream("3\n".getBytes()));

        Deletion deletion = Mockito.spy(new Deletion());

        try {
            deletion.accCloseFun(accNo, fileName);
        } catch (NoSuchElementException e) {

        }
        Mockito.verify(deletion, Mockito.times(2)).accCloseFun(accNo, fileName);

    }


@Test
    public void testDelLine_FileExists() throws Exception {
    File file = new File("fourhundred.txt");

    FileWriter writer = new FileWriter(file);
    writer.write("100 Nate 500.00\n");
    writer.write("200 Tim 500.00\n");
    writer.write("300 Nikk 500.00\n");
    writer.write("400 John 500.00\n"); //Targeted Line
    writer.close();

    Deletion deletion = Mockito.spy(new Deletion());
    deletion.delLine(400, "fourhundred.txt");

    Mockito.verify(deletion, Mockito.times(1))
            .delLine(400, "fourhundred.txt");
}
}





