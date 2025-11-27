

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class BankStatementTest {

    @Test
    public void testBankStatement_FileNotFound_CallsExitTwice() throws Exception {
        int accNo = 100;
        BankStatement bs = Mockito.spy(new BankStatement());
        Mockito.doNothing().when(bs).exit(accNo);
        bs.bankStatementFun(accNo);
        Mockito.verify(bs, Mockito.times(2)).exit(accNo);
    }


    @Test
    public void testBankStatement_FileIsEmpty_CallsExitOnce() throws Exception {
        int accNo = 200;
        File dir = new File("db/Bank Statement");


        File file = new File(dir, "acc_" + accNo + ".txt");
       if (file.exists()) {
            file.delete();
       }
       file.createNewFile();

        BankStatement bs = Mockito.spy(new BankStatement());
        Mockito.doNothing().when(bs).exit(accNo);

        bs.bankStatementFun(accNo);

        Mockito.verify(bs, Mockito.times(1)).exit(accNo);
    }

    @Test
    public void testBankStatement_FileIsNotEmpty_CallsExitOnce() throws Exception {
    int accNo = 300;
        File file = new File("db/Bank Statement/acc_" + accNo + ".txt");


        file.createNewFile();

        BankStatement bs = Mockito.spy(new BankStatement());
        Mockito.doNothing().when(bs).exit(accNo);

        bs.bankStatementFun(accNo);

        Mockito.verify(bs, Mockito.times(1)).exit(accNo);
    }
}
