import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class Inventory extends JFrame {
    
    Inventory() {
        setTitle("Inventory");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create and customize the manager panel UI here
        JPanel inventory_panel = new JPanel();
        JLabel label = new JLabel("Welcome to the Inventory (in progress)");
        inventory_panel.add(label);

        add(inventory_panel);
    }
}