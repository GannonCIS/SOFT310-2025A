import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreationTest {

    @Test
    public void createAccFunTest_Works() throws IOException {
        int accNo = 100;
        String[] accLineInfo = new String[9];

        accLineInfo[0] = "Nikk";
        accLineInfo[1] = "Adams";
        accLineInfo[2] = "07-15-2005";
        accLineInfo[3] = "July";
        accLineInfo[4] = "180 North";
        accLineInfo[5] = "1-2-3";
        accLineInfo[6] = "adams375@gannon.edu";
        accLineInfo[7] = "100";
        accLineInfo[8] = "password";

        System.setIn(new ByteArrayInputStream("\n".getBytes()));

        Creation creation = Mockito.spy(new Creation());

        Mockito.doReturn(accNo).when(creation).accNoCreation();
        Mockito.doReturn(accLineInfo).when(creation).getUserInfoFromUser();

        try {
            creation.createAccFun();
        } catch (NoSuchElementException e) {
        }

        Mockito.verify(creation, Mockito.times(1)).accNoCreation();
        Mockito.verify(creation, Mockito.times(1)).getUserInfoFromUser();
    }

    @Test
    public void accNoCreation_EmptyFile_Returns1() throws IOException {
        File file = new File("db/credentials.txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write("");
        writer.close();

        Creation creation = new Creation();
        int accNo = creation.accNoCreation();

        assertEquals(1, accNo);
    }

    @Test
    public void accNoCreation_NonEmptyFile_ReturnsNextNumber() throws IOException {
        File file = new File("db/credentials.txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write("10 pass1\n");
        writer.write("20 pass2\n");
        writer.write("100 pass3\n");
        writer.close();

        Creation creation = new Creation();
        int accNo = creation.accNoCreation();

        assertEquals(101, accNo);
    }

    @Test
    public void credWrite_AppendsCorrectLine() throws IOException {
        File file = new File("db/credentials.txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write("1 oldpass\n");
        writer.close();

        int accNo = 200;
        String[] accLineInfo = new String[9];
        accLineInfo[8] = "newpass";

        Creation creation = new Creation();
        creation.credWrite(accNo, accLineInfo);

        Scanner scanner = new Scanner(file);
        String last = "";
        while (scanner.hasNextLine()) {
            last = scanner.nextLine();
        }
        scanner.close();

        assertEquals(accNo + " " + accLineInfo[8], last.trim());
    }
}
