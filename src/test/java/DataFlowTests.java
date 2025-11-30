import org.junit.Assert;


import org.junit.runner.RunWith;
import org.junit.Test;
import org.junit.runners.JUnit4;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

@RunWith(JUnit4.class)
public class DataFlowTests {
    // Tests for the tRemarks variable in the transaction class

    @Test
    public void TesttRemarks_T1_Transaction() throws IOException {
        String simulatedInput =
                "1" + System.lineSeparator() +
                        "1" + System.lineSeparator() +
                        "30" + System.lineSeparator() +
                        "Test" + System.lineSeparator() +
                        "6" + System.lineSeparator();

        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        Transaction tr = new Transaction();

        try {
            tr.transactionFun(1);

            Assert.fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }


    @Test
    public void TesttRemarks_T2_Transaction() throws IOException {
        String simulatedInput =
                "6" + System.lineSeparator();

        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        Transaction tr = new Transaction();

        try {
            tr.allTransaction(1, 1, 30, "Test");

            Assert.fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }

    @Test
    public void TesttRemarks_T3_Transaction() throws IOException {
        Transaction tr = new Transaction();
        try {
            tr.writeTransaction(1, 1, 30, "Test");
            Assert.fail("Expected FileNotFoundException");
        } catch (FileNotFoundException e) {
        }
    }

    @Test
    public void TesttRemarks_T4_Transaction() throws IOException {
        Transaction tr = new Transaction();
        try {
            tr.debitWrite(1, 1, 30, "Test");
            Assert.fail("Expected FileNotFoundException");
        } catch (FileNotFoundException e) {
        }
    }

    @Test
    public void TesttRemarks_T5_Transaction() throws IOException {
        Transaction tr = new Transaction();
        try {
            tr.creditWrite(1, 1, 30, "Test");
            Assert.fail("Expected FileNotFoundException");
        } catch (FileNotFoundException e) {
        }
    }



    // Tests for the file variable accross the differant classes
    @Test
    public void TestFile_T1_AccountDetails() throws IOException {
        String simulatedInput =
                System.lineSeparator() + "6" + System.lineSeparator();

        ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
        System.setIn(in);

        AccountDetails ad = new AccountDetails();

        try {
            ad.accountDetailsFun(1);
            Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {

        }
    }
    @Test
    public void TestFile_T1_BankStatment(){

            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BankStatement bs = new BankStatement();
            try{
            bs.bankStatementFun(1);
            Assert.fail("Expected NoSuchElementException");}
         catch (NoSuchElementException | IOException e) {

        }
    }
    @Test
    public void TestFile_T1_BalanceInquiry() throws IOException{
            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();
            try{
            bi.balanceInquiryFun(1);
           Assert.fail("Expected NoSuchElementException");}
            catch (NoSuchElementException e) {

        }
    }
    @Test
    public void TestFile_T1_Login() throws IOException{
            String simulatedInput = "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Login lo = new Login();
            try{
            lo.loginAuth(1, "1");
        Assert.fail("Expected NoSuchElementException");}
            catch (NoSuchElementException e) {

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
    public void TestFile_T1_Deletion() throws IOException{
        try {
            Deletion dl = new Deletion();
            dl.delLine(7, "db/userDB.txt");
        } catch (NumberFormatException e) {
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
    public void TestFile_T3_Transaction() throws IOException{
        try {
            Transaction tr = new Transaction();
            tr.transaction(1, 2, 10);
        } catch (NumberFormatException e) {

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
            Assert.assertEquals(expected[0], result[0]);
            Assert.assertEquals(expected[1], result[1]);
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
            Assert.assertEquals(expected[0], result[0]);
            Assert.assertEquals(expected[1], result[1]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    public void TestfullnameArr_T4_Creation() throws IOException {


            String simulatedInput = "TestFirst" + System.lineSeparator() + "Test Forth" + System.lineSeparator() + "1" +
                    System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" +
                    System.lineSeparator() + "1" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Creation cr = new Creation();
            try{
             cr.getUserInfoFromUser();
             Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {

        }
    }
    @Test
    public void TestfullnameArr_T5_Creation() throws IOException {
        InputStream originalIn = System.in;

            String simulatedInput = "TestFirst" + System.lineSeparator() + "Test Fifth" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator() + "1" + System.lineSeparator() +
                    "1" + System.lineSeparator() + "1" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            Creation cr = new Creation();
        try{ cr.getUserInfoFromUser();
          Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
        }
    }

    // Tests for the accBalance variable across the BalanceInquiry class
    @Test
    public void TestaccBalance_T1_BalanceInquiry() throws IOException{

            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();
            try{
            bi.balanceInquiryFun(1);
        Assert.fail("Expected NoSuchElementException");
            } catch (NoSuchElementException e) {
        }
    }
    @Test
    public void TestaccBalance_T2_BalanceInquiry() throws IOException{


            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();
            try{
            bi.balanceInquiryFun(2);
        Assert.fail("Expected NumberFormatException");
            } catch (NumberFormatException e) {
        }
    }
    @Test
    public void TestaccBalance_T3_BalanceInquiry() throws IOException{

            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            BalanceInquiry bi = new BalanceInquiry();

            try{
                bi.balanceInquiryFun(3);
                Assert.fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    // Tests for the detail variable across the AccountDetails class
    @Test
    public void Testdetail_T1_AccountDetails() throws IOException{


            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
        try {
            ad.accountDetailsFun(1);
            Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {
        }
    }
    @Test
    public void Testdetail_T2_AccountDetails() throws IOException{

            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
            try{
            ad.accountDetailsFun(2);
            Assert.fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    @Test
    public void Testdetail_T3_AccountDetails() throws IOException{

            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
        try {
            ad.accountDetailsFun(3);
            Assert.fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {

        }
    }
    @Test
    public void Testdetail_T4_AccountDetails() throws IOException{


            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
        AccountDetails ad = new AccountDetails();
        try {

            ad.accountDetailsFun(1);
            Assert.fail("Expected NoSuchElementException");

        } catch (NoSuchElementException e) {

        }
    }
    @Test
    public void Testdetail_T5_AccountDetails() throws IOException{


            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
        try {
            ad.accountDetailsFun(2);
            Assert.fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
    @Test
    public void Testdetail_T6_AccountDetails() throws IOException{


            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
        try {
            ad.accountDetailsFun(3);
        Assert.fail("Expected NumberFormatException");} catch (NumberFormatException e) {

        }
    }
    @Test
    public void Testdetail_T7_AccountDetails() throws IOException{


            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
        try {
            ad.accountDetailsFun(1);
            Assert.fail("Expected NoSuchElementException");
        } catch (NoSuchElementException e) {

        }
    }
    @Test
    public void Testdetail_T8_AccountDetails() throws IOException{

            String simulatedInput = System.lineSeparator() + "6" + System.lineSeparator();
            ByteArrayInputStream in = new ByteArrayInputStream(simulatedInput.getBytes());
            System.setIn(in);
            AccountDetails ad = new AccountDetails();
        try {
            ad.accountDetailsFun(2);
            Assert.fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
    }
}
