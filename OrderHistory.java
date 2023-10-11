import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class OrderHistory extends JFrame {
    
    OrderHistory() {
        setTitle("Order History");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and customize the manager panel UI here
        JPanel order_history_panel = new JPanel();
        JLabel label = new JLabel("Welcome to the Order History (in progress)");
        order_history_panel.add(label);

        add(order_history_panel);
    }
}