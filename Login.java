import java.awt.*;  
import javax.swing.*; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login extends JFrame {
    private JTextField username_field;
    private JPasswordField password_field;
    
    Login() {
        setTitle("The Alley Login Panel"); 

        // Terminates the code if the user clicks the "X" button
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        // Centers the panel on the user's screen
        setLocationRelativeTo(null);
        setResizable(false);

        // Creates a new JPanel that is formatted using GridLayout
        // There are 3 rows and 3 columns
        JPanel login_panel = new JPanel(new GridLayout(3, 2, 20, 20));
        login_panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create labels in the login panel to indicate where the username and password
        // will be entered
        JLabel user_label = new JLabel("Username:");
        JLabel pass_label = new JLabel("Password");

        // Create the fields where the username and password will actually be entered
        username_field = new JTextField();
        password_field = new JPasswordField(); // Makes the password appear as asterisks when entered

        // Create the button that will be clicked after entering the username and password
        // Will be used to invoke the ActionListener
        JButton login_button = new JButton("Login");
        
        // center the login button relative to the x axis
        // login_button.setAlignmentX(Component.CENTER_ALIGNMENT); 

        // Add each component declared perviously in the code
        login_panel.add(user_label);
        login_panel.add(username_field);
        login_panel.add(pass_label);
        login_panel.add(password_field);
        // adds some white space and right align the login button
        login_panel.add(new JLabel()); 
        login_panel.add(login_button);

        // ActionListener will check the username and password entered and then:
        // 1) If employee information is entered correctly, show first panel on Server Side
        // 2) If manager information is entered correctly, show first panel on Manager Side
        // 3) If the username or password are wrong, display error message and allow user to re-enter the information
        login_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String user_temp = username_field.getText();
                    
                    // needed to use .getPassword() 
                    // password is stored as a JPasswordField not JTextField
                    String password_temp = new String(password_field.getPassword());

                    // check if the entered information is valid
                    if(check_user(user_temp, password_temp)) { // if the information is valid
                        dispose(); // close the Login Panel

                        if(user_temp.equals("manager")) {
                            openManagerPanel();
                        } else {
                            openEmployeePanel();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Please re-enter your username or password!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                    }
                }        
            }
        );

        add(login_panel);
    }

    // function checks the username and password entered in the login panel
    // the two arguments are checked against a list of usernames and passwords
    private boolean check_user(String user, String pass) {
        String[] users = {"manager", "employeeA", "employeeB", "employeeC"};
        String[] passwords = {"manager123", "passwordA", "passwordB", "passwordC"};

        for(int i = 0; i < users.length; ++i) {
            if(users[i].equals(user)) {
                if(passwords[i].equals(pass)) {
                    return true;
                }
            }
        }

        return false;
    }

    private void openManagerPanel() {
        JOptionPane.showMessageDialog(null, "Inventory Stuff", "Inventory", JOptionPane.PLAIN_MESSAGE);
    }

    private void openEmployeePanel() {
        JOptionPane.showMessageDialog(null, "List of Drinks", "Drinks", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String args[]) {
        new Login().setVisible(true);
    }
}