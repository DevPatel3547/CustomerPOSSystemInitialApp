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


        JLabel current_inventory_label = new JLabel("Current Inventory");
        constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        gbl.setConstraints(current_inventory_label, constraints);
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        current_inventory_label.setFont(labelFont);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        current_inventory_label.setBorder(border);


        JLabel item_name_label = new JLabel("Name");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        gbl.setConstraints(item_name_label, constraints);


        JLabel item_quantity_label = new JLabel("Quantity");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 2;
        gbl.setConstraints(item_quantity_label, constraints);


        JLabel restock_label = new JLabel("Restock");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gbl.setConstraints(restock_label, constraints);


        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> inventoryList = new JList<>(listModel);


        try {
            String sql = "SELECT name, quantity, min FROM inventory;";
            ResultSet resultSet = database.getData(sql);


            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                int min = resultSet.getInt("min");


                // Determine if restocking is needed based on the "min" value
                boolean needRestock = quantity < min;


                // Format the name, quantity, and needRestock and add it to the list model
                String formattedEntry = String.format("%-50s   %10s   %50s", needRestock, quantity, name);
                listModel.addElement(formattedEntry);
            }

 
            resultSet.close();
            //database.closeJDBC();
        } catch (SQLException e) {
            System.out.println("Error: Could not load inventory");
            e.printStackTrace();
            System.exit(0);
        }



        JScrollPane scrollPane = new JScrollPane(inventoryList);
        scrollPane.setPreferredSize(new Dimension(450, 150));


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
        inventory_panel.add(current_inventory_label);
        inventory_panel.add(item_name_label);
        inventory_panel.add(item_quantity_label);
        inventory_panel.add(restock_label);
        inventory_panel.add(buy_more_supplies_button);
        inventory_panel.add(scrollPane);
        inventory_panel.add(edit_menu_button);


        logout_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.closeJDBC();
                dispose();
                new Login().setVisible(true);
            }
        });


        to_order_history_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.closeJDBC();
                dispose();
                new OrderHistory().setVisible(true);
            }
        });


        buy_more_supplies_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.closeJDBC();
                dispose();
                new BuyMoreSupplies().setVisible(true);
            }
        });


        edit_menu_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.closeJDBC();
                dispose();
                new EditMenu().setVisible(true);
            }
        });


        add(inventory_panel);
    }


    private void closeDatabase() {
        database.closeJDBC();
    }
}



