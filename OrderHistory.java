import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class OrderHistory extends JFrame {

    OrderHistory() {
        setTitle("Order History");
        setSize(700, 200);
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

        JButton popular_combinations_button = new JButton("Popular Combinations");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        gbl.setConstraints(popular_combinations_button, constraints);

        JPanel orders_box = new JPanel();
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
        order_history_panel.add(orders_box);
        order_history_panel.add(popular_combinations_button);

        popular_combinations_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new DateSelectionFrame().setVisible(true);
                }
            }
        );

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
