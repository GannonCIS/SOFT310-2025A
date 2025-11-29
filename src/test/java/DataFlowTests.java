import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runner.*;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RunWith(JUnit4.class)
public class DataFlowTests {
    // Tests for the tRemarks variable in the transaction class
    @Test
    public void TesttRemarks_T1_Transaction() {
        InputStream originalIn = System.in;
        try {
            String simulatedInput = "1" + System.lineSeparator() + "30" + System.lineSeparator() + "Test" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Transaction tr = new Transaction();
            tr.transactionFun(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TesttRemarks_T2_Transaction(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Transaction tr = new Transaction();
            tr.allTransaction(1, 1, 30, "Test");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TesttRemarks_T3_Transaction(){
        try {
            Transaction tr = new Transaction();
            tr.writeTransaction(1, 1, 30, "Test");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TesttRemarks_T4_Transaction(){
        try {
            Transaction tr = new Transaction();
            tr.debitWrite(1, 1, 30, "Test");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TesttRemarks_T5_Transaction(){
        try {
            Transaction tr = new Transaction();
            tr.creditWrite(1, 1, 30, "Test");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Tests for the file variable accross the differant classes
    @Test
    public void TestFile_T1_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestFile_T1_BankStatment(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BankStatement bs = new BankStatement();
            bs.bankStatementFun(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestFile_T1_BalanceInquiry(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();
            bi.balanceInquiryFun(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestFile_T1_Login(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Login lo = new Login();
            lo.loginAuth(1, "1");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    public void TestFile_T1_Creation(){
        try {
            Creation cr = new Creation();
            int num = cr.accNoCreation();
            int expected = 5;
            Assert.assertEquals(num, expected);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestFile_T1_Deletion(){
        try {
            Deletion dl = new Deletion();
            dl.delLine(7, "db/userDB.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestFile_T1_Transaction(){
        try {
            Transaction tr = new Transaction();
            boolean state = tr.rAccCheck(1);
            boolean expected = true;
            Assert.assertEquals(expected, state);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestFile_T2_Transaction(){
        try {
            Transaction tr = new Transaction();
            boolean state = tr.sAccBalCheck(1, 10);
            boolean expected = true;
            Assert.assertEquals(expected, state);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestFile_T3_Transaction(){
        try {
            Transaction tr = new Transaction();
            tr.transaction(1, 2, 10);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // Tests for the fullnameArr variable across the Creation class
    @Test
    public void TestfullnameArr_T1_Creation() throws IOException {
        InputStream originalIn = System.in;
        try {
            String simulatedInput = "Test First" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Creation cr = new Creation();
            String[] result = cr.getUserInfoFromUser();
            String[] expected = new String[9];
            expected[0] = "Test";
            expected[1] = "First";
            expected[2] = "1";
            expected[3] = "1";
            expected[4] = "1";
            expected[5] = "1";
            expected[6] = "1";
            expected[7] = "1";
            expected[8] = "1";
            expected[9] = "1";
            Assert.assertEquals(expected[0], result[0]);
            Assert.assertEquals(expected[1], result[1]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestfullnameArr_T2_Creation() throws IOException {
        InputStream originalIn = System.in;
        try {
            String simulatedInput = "Test Second" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Creation cr = new Creation();
            String[] result = cr.getUserInfoFromUser();
            String[] expected = new String[9];
            expected[0] = "Test";
            expected[1] = "Second";
            expected[2] = "1";
            expected[3] = "1";
            expected[4] = "1";
            expected[5] = "1";
            expected[6] = "1";
            expected[7] = "1";
            expected[8] = "1";
            expected[9] = "1";
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestfullnameArr_T3_Creation() throws IOException {
        InputStream originalIn = System.in;
        try {
            String simulatedInput = "Test Third" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Creation cr = new Creation();
            String[] result = cr.getUserInfoFromUser();
            String[] expected = new String[9];
            expected[0] = "Test";
            expected[1] = "Third";
            expected[2] = "1";
            expected[3] = "1";
            expected[4] = "1";
            expected[5] = "1";
            expected[6] = "1";
            expected[7] = "1";
            expected[8] = "1";
            expected[9] = "1";
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestfullnameArr_T4_Creation() throws IOException {
        InputStream originalIn = System.in;
        try {
            String simulatedInput = "TestFirst" + System.lineSeparator() + "Test Forth" + System.lineSeparator() + "1" +
                    System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" +
                    System.lineSeparator() + "1" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Creation cr = new Creation();
            String[] result = cr.getUserInfoFromUser();
            String[] expected = new String[9];
            expected[0] = "Test";
            expected[1] = "Forth";
            expected[2] = "1";
            expected[3] = "1";
            expected[4] = "1";
            expected[5] = "1";
            expected[6] = "1";
            expected[7] = "1";
            expected[8] = "1";
            expected[9] = "1";
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestfullnameArr_T5_Creation() throws IOException {
        InputStream originalIn = System.in;
        try {
            String simulatedInput = "TestFirst" + System.lineSeparator() + "Test Fifth" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Creation cr = new Creation();
            String[] result = cr.getUserInfoFromUser();
            String[] expected = new String[9];
            expected[0] = "Test";
            expected[1] = "Fifth";
            expected[2] = "1";
            expected[3] = "1";
            expected[4] = "1";
            expected[5] = "1";
            expected[6] = "1";
            expected[7] = "1";
            expected[8] = "1";
            expected[9] = "1";
            Assert.assertEquals(expected, result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // Tests for the accBalance variable across the BalanceInquiry class
    @Test
    public void TestaccBalance_T1_BalanceInquiry(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();
            bi.balanceInquiryFun(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestaccBalance_T2_BalanceInquiry(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();
            bi.balanceInquiryFun(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestaccBalance_T3_BalanceInquiry(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();
            bi.balanceInquiryFun(3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // Tests for the detail variable across the AccountDetails class
    @Test
    public void Testdetail_T1_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void Testdetail_T2_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void Testdetail_T3_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void Testdetail_T4_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void Testdetail_T5_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void Testdetail_T6_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void Testdetail_T7_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void Testdetail_T8_AccountDetails(){
        InputStream originalIn = System.in;
        try {
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            ad.accountDetailsFun(2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
