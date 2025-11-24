import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.NoSuchElementException;
import static org.junit.Assert.fail;
import java.io.FileWriter;


@RunWith(MockitoJUnitRunner.class)
public class DeletionTest {


    /*
    Testing for accCloseFun(int accNo, String fileName) of DELETION Class
    1st BVA Test Written
    Explanation - Range is very limited, min = 1 and max = 2
             Robust Normal BVA: min-1,min,min+1,max,max-1,max+1,nominal
              Since min = 1 and max = 2, min +1 = max and max-1 = min in this example
              Instead of 7 test cases needed for full testing, only 4 are necessary
              min+1 and max-1 are redundant and nominal is too due to the nature of the method
     */

    @Test
    public void testAccCloseFun_UserEntersNumberThatIsNot1_MinMinus1() throws Exception {
        int accNo = 000;
        String fileName = "zero.txt";

        System.setIn(new ByteArrayInputStream("0\n".getBytes()));

        Deletion deletion = Mockito.spy(new Deletion());

        try {
            deletion.accCloseFun(accNo, fileName);
        } catch (NoSuchElementException e) {

        }
        Mockito.verify(deletion, Mockito.times(2)).accCloseFun(accNo, fileName);

    }


    @Test
    public void testAccCloseFun_UserWantsToDeleteAcc_Types1_Min() throws Exception {
        int accNo = 100;
        String fileName = "onehundred.txt";
        System.setIn(new ByteArrayInputStream("1\n".getBytes()));

        Deletion deletion = Mockito.spy(new Deletion());
        Mockito.doNothing().when(deletion).delLine(accNo, fileName);
        deletion.accCloseFun(accNo, fileName);
        Mockito.verify(deletion, Mockito.times(1)).delLine(accNo, fileName);
    }

    @Test
    public void testAccCloseFun_UserDoesntWantToDeleteAcc_Types2_Max() throws Exception {
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
    public void testAccCloseFun_UserEntersNumberThatIsNot1_MaxPlus1() throws Exception {
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






/*
Testing for void delLine(int accNo, String fileName) in DELETION class
 */

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





