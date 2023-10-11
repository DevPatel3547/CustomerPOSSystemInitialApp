import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;

class OrderHistory extends JFrame {

    OrderHistory() {
        setTitle("Order History");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel order_history_panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        order_history_panel.setLayout(gbl);

        JButton back_to_inventory_button = new JButton("Back");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        gbl.setConstraints(back_to_inventory_button, constraints);

        JButton to_most_popular_trends_button = new JButton("Most Popular Trends");
        constraints = new GridBagConstraints();
        constraints.gridx = 4;
        constraints.gridy = 0;
        gbl.setConstraints(to_most_popular_trends_button, constraints);

        JLabel date_label = new JLabel("Date");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        gbl.setConstraints(date_label, constraints);

        JLabel past_orders_labels = new JLabel("All Past Orders");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.insets = new Insets(0, 125, 0, 0);
        gbl.setConstraints(past_orders_labels, constraints);

        JLabel price_label = new JLabel("Price");
        constraints = new GridBagConstraints();
        constraints.gridx = 4;
        constraints.gridy = 3;
        gbl.setConstraints(price_label, constraints);

        JPanel orders_box = new JPanel();
        orders_box.setBorder(new LineBorder(Color.BLACK, 4));
        Dimension boxSize = new Dimension(500, 225);
        orders_box.setPreferredSize(boxSize);
        constraints = new GridBagConstraints();
        constraints.gridwidth = 5;
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridheight = 2;
        gbl.setConstraints(orders_box, constraints);

        order_history_panel.add(back_to_inventory_button);
        order_history_panel.add(to_most_popular_trends_button);
        order_history_panel.add(date_label);
        order_history_panel.add(past_orders_labels);
        order_history_panel.add(price_label);
        order_history_panel.add(orders_box);

        back_to_inventory_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Inventory().setVisible(true);
                }
            }
        );

        to_most_popular_trends_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new MostPopularTrends().setVisible(true);
                }
            }
        );

        add(order_history_panel);
    }
}
