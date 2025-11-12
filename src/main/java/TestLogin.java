import java.io.IOException;

public class TestLogin{
    public static void main(String[] args) throws IOException {
        Login login = new Login();


        System.out.println("=== TEST 1: correct acc/pass ===");
        int accNo1 = 1;
        String pass1 = "pass";
        login.loginAuth(accNo1, pass1);


    }
}
