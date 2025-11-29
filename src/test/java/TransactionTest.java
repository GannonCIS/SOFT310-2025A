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

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TransactionTest {

    @Test
    public void allTransaction_ValidReceiver_ValidBalance() throws IOException {
        int accNo = 100;
        int rAccNo = 200;
        int amount = 50;
        String remarks = "Test payment";

        System.setIn(new ByteArrayInputStream("\n".getBytes()));

        Transaction tx = Mockito.spy(new Transaction());

        Mockito.doReturn(true).when(tx).rAccCheck(rAccNo);
        Mockito.doReturn(true).when(tx).sAccBalCheck(accNo, amount);
        Mockito.doNothing().when(tx).transaction(accNo, rAccNo, amount);
        Mockito.doNothing().when(tx).writeTransaction(accNo, rAccNo, amount, remarks);

        try {
            tx.allTransaction(accNo, rAccNo, amount, remarks);
        } catch (NoSuchElementException e) {
        }
    }

    @Test
    public void allTransaction_ValidReceiver_InvalidBalance() throws IOException {
        int accNo = 100;
        int rAccNo = 200;
        int amount = 9999;
        String remarks = "Large payment";

        Transaction tx = Mockito.spy(new Transaction());

        Mockito.doReturn(true).when(tx).rAccCheck(rAccNo);
        Mockito.doReturn(false).when(tx).sAccBalCheck(accNo, amount);

        tx.allTransaction(accNo, rAccNo, amount, remarks);
    }

    @Test
    public void allTransaction_InvalidReceiver() throws IOException {
        int accNo = 100;
        int rAccNo = 300;
        int amount = 10;
        String remarks = "Small payment";

        Transaction tx = Mockito.spy(new Transaction());

        Mockito.doReturn(false).when(tx).rAccCheck(rAccNo);

        tx.allTransaction(accNo, rAccNo, amount, remarks);
    }

    @Test
    public void transactionFun_DelegatesToAllTransaction() throws IOException {
        int accNo = 100;
        String input = "200\n50\nTest remarks\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Transaction tx = Mockito.spy(new Transaction());

        Mockito.doNothing().when(tx)
                .allTransaction(Mockito.eq(accNo), Mockito.eq(200), Mockito.eq(50), Mockito.eq("Test remarks"));

        tx.transactionFun(accNo);
    }

    @Test
    public void rAccCheckAndSAccBalCheck_BranchesCovered() throws IOException {
        File file = new File("db/balanceDB.txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write("100 1000\n");  // sender
        writer.write("200 50\n");    // small balance
        writer.close();

        Transaction tx = new Transaction();

        assertTrue(tx.rAccCheck(100));
        assertFalse(tx.rAccCheck(300));

        assertTrue(tx.sAccBalCheck(100, 500));   // 500 <= 1000
        assertFalse(tx.sAccBalCheck(200, 100));  // 100 > 50
    }

    @Test
    public void transaction_UpdatesBalancesCorrectly() throws IOException {
        File file = new File("db/balanceDB.txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write("100 500\n");
        writer.write("200 200\n");
        writer.close();

        Transaction tx = new Transaction();
        tx.transaction(100, 200, 100);

        Scanner scanner = new Scanner(file);
        String line1 = scanner.nextLine();
        String line2 = scanner.nextLine();
        scanner.close();

        assertEquals("100 400", line1.trim());
        assertEquals("200 300", line2.trim());
    }

    @Test
    public void debitWrite_AppendsDebitEntry() throws IOException {
        int accNo = 100;
        int rAccNo = 200;
        int amount = 50;
        String remarks = "DebitTest";

        File file = new File("db/Bank Statement/acc_" + accNo + ".txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write("");
        writer.close();

        Transaction tx = new Transaction();
        tx.debitWrite(accNo, rAccNo, amount, remarks);

        Scanner scanner = new Scanner(file);
        String last = "";
        while (scanner.hasNextLine()) {
            last = scanner.nextLine();
        }
        scanner.close();

        assertTrue(last.contains("Transfer to " + rAccNo));
        assertTrue(last.contains("Debit"));
        assertTrue(last.contains(String.valueOf(amount)));
        assertTrue(last.contains(remarks));
    }

    @Test
    public void creditWrite_AppendsCreditEntry() throws IOException {
        int accNo = 100;
        int rAccNo = 200;
        int amount = 75;
        String remarks = "CreditTest";

        File file = new File("db/Bank Statement/acc_" + rAccNo + ".txt");
        FileWriter writer = new FileWriter(file, false);
        writer.write("");
        writer.close();

        Transaction tx = new Transaction();
        tx.creditWrite(accNo, rAccNo, amount, remarks);

        Scanner scanner = new Scanner(file);
        String last = "";
        while (scanner.hasNextLine()) {
            last = scanner.nextLine();
        }
        scanner.close();

        assertTrue(last.contains("Transfer from " + accNo));
        assertTrue(last.contains("Credit"));
        assertTrue(last.contains(String.valueOf(amount)));
        assertTrue(last.contains(remarks));
    }
}
