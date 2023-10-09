import java.awt.*;  
import javax.swing.*;  

public class Login extends JFrame {
    private JTextField user;
    private JPasswordField pass;
    Login() {
        setTitle("The Alley Login Pannel"); 

        // Exits the panel if the user clicks the "X" button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);

        // Centers the panel on the user's screen
        setLocationRelativeTo(null);

        // Creates a new JPanel that is formatted using GridLayout
        // There are 3 rows and 3 columns
        JPanel p = new JPanel(new GridLayout(3, 3));

        // Create fields in the panel that will store the username and password temporarily
        JLabel user_temp = new JLabel("Username:");
        JLabel pass_temp = new JLabel("Password");

        // Declare the elements that will format the entered username and password
        user = new JTextField();
        pass = new JPasswordField(); // Makes the password appear as asterisks when entered

        // Create the button that will be clicked after entering the username and password
        // Will be used to invoke the ActionListener
        JButton login_button = new JButton("Login");

    }

    public static void main(String args[]) {
        new Login();
    }

}