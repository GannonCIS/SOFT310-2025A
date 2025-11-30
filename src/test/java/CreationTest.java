import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;



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






}
