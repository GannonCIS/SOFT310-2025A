import java.io.IOException;

public class TestLogin{
    public static void main(String[] args) throws IOException {
        Login login = new Login();


        System.out.println("=== TEST 1: correct acc/pass ===");
        int accNo1 = 1;          // must match a line in db/credentials.txt
        String pass1 = "pass";   // must match that same line's password
        login.loginAuth(accNo1, pass1);


    }
}
