import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

class SuppliesCart extends JFrame {
    
    SuppliesCart() {
        setTitle("Supplies Cart");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel supplies_cart_panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        supplies_cart_panel.setLayout(gbl);

        JButton back_to_buy_more_supplies_button = new JButton("Back");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0, 0, 10, 0);
        constraints.fill = GridBagConstraints.HORIZONTAL;
        gbl.setConstraints(back_to_buy_more_supplies_button, constraints);

        JLabel cart_label = new JLabel("Cart");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 3;
        constraints.insets = new Insets(0, 0, 10, 0);
        constraints.anchor = GridBagConstraints.CENTER;
        Font labelFont = new Font("Arial", Font.BOLD, 16);
        cart_label.setFont(labelFont);
        gbl.setConstraints(cart_label, constraints);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
        cart_label.setBorder(border);

        JLabel item_name_label = new JLabel("Name");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(item_name_label, constraints);

        JLabel item_price_label = new JLabel("Price");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(item_price_label, constraints);

        JLabel item_quantity_label = new JLabel("Quantity");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 2;
        gbl.setConstraints(item_quantity_label, constraints);

        JPanel name_box = new JPanel();
        name_box.setBorder(new LineBorder(Color.BLACK, 3));
        Dimension boxSize = new Dimension(75, 150);
        name_box.setPreferredSize(boxSize);
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        gbl.setConstraints(name_box, constraints);

        JPanel price_box = new JPanel();
        price_box.setBorder(new LineBorder(Color.BLACK, 3));
        price_box.setPreferredSize(boxSize);
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(price_box, constraints);

        JPanel quantity_box = new JPanel();
        quantity_box.setBorder(new LineBorder(Color.BLACK, 3));
        quantity_box.setPreferredSize(boxSize);
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(quantity_box, constraints);

        JButton supplies_checkout_button = new JButton("Checkout");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        gbl.setConstraints(supplies_checkout_button, constraints);

        supplies_cart_panel.add(back_to_buy_more_supplies_button);
        supplies_cart_panel.add(cart_label);
        supplies_cart_panel.add(item_name_label);
        supplies_cart_panel.add(item_price_label);
        supplies_cart_panel.add(item_quantity_label);
        supplies_cart_panel.add(name_box);
        supplies_cart_panel.add(price_box);
        supplies_cart_panel.add(quantity_box);
        supplies_cart_panel.add(supplies_checkout_button);

        back_to_buy_more_supplies_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new BuyMoreSupplies().setVisible(true);
                }
            }
        );

        supplies_checkout_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "You bought supplies!", "Purchase Successful!", JOptionPane.PLAIN_MESSAGE);
                }
            }
        );

        add(supplies_cart_panel);
    }
}
