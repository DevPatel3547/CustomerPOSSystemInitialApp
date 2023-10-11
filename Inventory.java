import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


class Inventory extends JFrame {
    
    Inventory() {
        setTitle("Inventory");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create a panel and set its layout to GridBagLayout
        JPanel inventory_panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        inventory_panel.setLayout(gbl);

        JButton back_to_login = new JButton("Back to Login");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        gbl.setConstraints(back_to_login, constraints);

        JButton to_order_history_button = new JButton("Order History");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        gbl.setConstraints(to_order_history_button, constraints);
        
        JLabel current_invetory_label = new JLabel("Current Inventory");
        constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        gbl.setConstraints(current_invetory_label, constraints);
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        current_invetory_label.setFont(labelFont);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        current_invetory_label.setBorder(border);

        JLabel item_name_label = new JLabel("Name");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gbl.setConstraints(item_name_label, constraints);
        
        JLabel item_quantity_label = new JLabel("Quantity Available");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        gbl.setConstraints(item_quantity_label, constraints);

        JPanel inventory_box = new JPanel();
        inventory_box.setBorder(new LineBorder(Color.BLACK, 4));
        Dimension boxSize = new Dimension(400, 125);
        inventory_box.setPreferredSize(boxSize);
        constraints = new GridBagConstraints();
        constraints.gridwidth = 4; 
        constraints.gridx = 0;
        constraints.gridy = 3;
        gbl.setConstraints(inventory_box, constraints);   

        JButton buy_more_suppplies_button = new JButton("Buy More Supplies");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        gbl.setConstraints(buy_more_suppplies_button, constraints);
        
        inventory_panel.add(back_to_login);
        inventory_panel.add(to_order_history_button);
        inventory_panel.add(current_invetory_label);
        inventory_panel.add(item_name_label);
        inventory_panel.add(item_quantity_label);
        inventory_panel.add(inventory_box);
        inventory_panel.add(buy_more_suppplies_button);

        back_to_login.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Login().setVisible(true);
                }
            }
        );

        to_order_history_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new OrderHistory().setVisible(true);
                }
            }
        );

        buy_more_suppplies_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new BuyMoreSupplies().setVisible(true);
                }
            }
        );



        add(inventory_panel);
    }
}