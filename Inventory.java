import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;


class Inventory extends JFrame {
    private Database database;
    
    Inventory() {
        setTitle("Inventory");
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
        JPanel inventory_panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        inventory_panel.setLayout(gbl);

        JButton logout_button = new JButton("Logout");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 5, 30);
        gbl.setConstraints(logout_button, constraints);

        JButton to_order_history_button = new JButton("Order History");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 30, 5, 0);
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

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> inventoryList = new JList<>(listModel);

        try {
            String sql = "SELECT name, quantity FROM inventory;";
            ResultSet resultSet = database.getData(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");

                // Format the name and quantity and add it to the list model
                String formattedEntry = String.format("%-70s %5d", name, quantity);
                listModel.addElement(formattedEntry);
            }

            resultSet.close();
            //database.closeJDBC();
        } catch (SQLException e) {
            System.out.println("Error: Could not load inventory");
            e.printStackTrace();
            System.exit(0);
        }

        // Create a custom cell renderer for right-aligning the quantities
        inventoryList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(SwingConstants.RIGHT);
                return component;
            }
        });

        JScrollPane scrollPane = new JScrollPane(inventoryList);
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

        JButton edit_menu_button = new JButton("Edit Menu");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(edit_menu_button, constraints);
        
        inventory_panel.add(logout_button);
        inventory_panel.add(to_order_history_button);
        inventory_panel.add(current_invetory_label);
        inventory_panel.add(item_name_label);
        inventory_panel.add(item_quantity_label);
        inventory_panel.add(buy_more_supplies_button);
        inventory_panel.add(scrollPane);
        inventory_panel.add(edit_menu_button);

        logout_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new Login().setVisible(true);
                }
            }
        );

        to_order_history_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new OrderHistory().setVisible(true);
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

        edit_menu_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new EditMenu().setVisible(true);
                }
            }
        );

        add(inventory_panel);
    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}