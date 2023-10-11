import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


class BuyMoreSupplies extends JFrame {
    
    BuyMoreSupplies() {
        setTitle("Buy More Supplies");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Create a panel and set its layout to GridBagLayout
        JPanel buy_more_supplies_panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        buy_more_supplies_panel.setLayout(gbl);

        JButton back_to_inventory = new JButton("Back to Inventory");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        gbl.setConstraints(back_to_inventory, constraints);

        JButton to_supplies_cart_button = new JButton("Go to Cart");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        gbl.setConstraints(to_supplies_cart_button, constraints);

        JTextField search_bar = new JTextField(20);
        search_bar.setText("Search Bar"); 
        constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        gbl.setConstraints(search_bar, constraints);
        search_bar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (search_bar.getText().equals("Search Bar")) {
                    search_bar.setText(""); // Clear the text when clicked on
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (search_bar.getText().isEmpty()) {
                    search_bar.setText("Search Bar"); // Restore the default text if empty
                }
            }
        });

        JLabel item_name_label = new JLabel("Name");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gbl.setConstraints(item_name_label, constraints);
        
        JLabel item_price_label = new JLabel("Price ($)");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        gbl.setConstraints(item_price_label, constraints);

        JPanel shopping_box = new JPanel();
        shopping_box.setBorder(new LineBorder(Color.BLACK, 4));
        Dimension boxSize = new Dimension(400, 125);
        shopping_box.setPreferredSize(boxSize);
        constraints = new GridBagConstraints();
        constraints.gridwidth = 4; 
        constraints.gridx = 0;
        constraints.gridy = 3;
        gbl.setConstraints(shopping_box, constraints);   
        
        buy_more_supplies_panel.add(back_to_inventory);
        buy_more_supplies_panel.add(to_supplies_cart_button);
        buy_more_supplies_panel.add(search_bar);
        buy_more_supplies_panel.add(item_name_label);
        buy_more_supplies_panel.add(item_price_label);
        buy_more_supplies_panel.add(shopping_box);

        back_to_inventory.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Inventory().setVisible(true);
                }
            }
        );

        to_supplies_cart_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new SuppliesCart().setVisible(true);
                }
            }
        );

        add(buy_more_supplies_panel);
    }
}