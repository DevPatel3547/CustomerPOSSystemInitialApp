import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;   
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Set;


class excess extends JFrame {
    private Database database;
    
    excess(Set<String> selectedDates) {
        setTitle("Excess Report");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        database = new Database();
        database.openJDBC();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeDatabase();
                System.exit(0);
            }
        });

        // Create a panel and set its layout to GridBagLayout
        JPanel excess_panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        excess_panel.setLayout(gbl);


        JButton back_button = new JButton("Back Button");//    BACK BUTTON
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 5, 30);
        gbl.setConstraints(back_button, constraints);

        
        JLabel current_invetory_label = new JLabel("Current Excess Report"); // SALES REPORT
        constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        gbl.setConstraints(current_invetory_label, constraints);
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        current_invetory_label.setFont(labelFont);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        current_invetory_label.setBorder(border);

        JLabel item_name_label = new JLabel("Menu Item"); // MENU ITEM
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gbl.setConstraints(item_name_label, constraints);
        

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> excessList = new JList<>(listModel);
        try {
            // Use the provided single date as the timestamp
            String date1 = selectedDates.iterator().next();
            String sql = "SELECT i.name AS \"Menu Item\" " +
                "FROM inventory i " +
                "LEFT JOIN (" +
                "    SELECT unnest(string_to_array(toppings, ', ')) AS sold_item, " +
                "           COUNT(*) AS sold_count " +
                "    FROM orderhistory " +
                "    WHERE CAST(date AS timestamp) BETWEEN '" + date1 + "' AND NOW() " +
                "    GROUP BY sold_item" +
                ") sd ON i.name = sd.sold_item " +
                "WHERE (i.quantity - COALESCE(sd.sold_count, 0)) < 0.1 * i.quantity;";
            ResultSet resultSet = database.getData(sql);

            while (resultSet.next()) {
                String itemName = resultSet.getString("Menu Item");
                
                // Add only the item name to the list model
                listModel.addElement(itemName);
            }

            resultSet.close();
        
        } catch (SQLException e) {
            System.out.println("Error: Could not load excess report");
            e.printStackTrace();
            System.exit(0);
        }







        // Create a custom cell renderer for right-aligning the quantities
        excessList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(SwingConstants.RIGHT);
                return component;
            }
        });

        JScrollPane scrollPane = new JScrollPane(excessList);
        scrollPane.setPreferredSize(new Dimension(400, 125));

        constraints = new GridBagConstraints();
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 3;
        gbl.setConstraints(scrollPane, constraints);

        JButton buy_more_supplies_button = new JButton("Buy More Supplies");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(buy_more_supplies_button, constraints);

        
        
        excess_panel.add(back_button);
        //excess_panel.add(to_sales_together_button);
        excess_panel.add(current_invetory_label);
        excess_panel.add(item_name_label);
        excess_panel.add(buy_more_supplies_button);
        excess_panel.add(scrollPane);


        back_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new MostPopularTrends().setVisible(true);
                }
            }
        );


        buy_more_supplies_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new BuyMoreSupplies().setVisible(true);
                }
            }
        );

        add(excess_panel);
    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}