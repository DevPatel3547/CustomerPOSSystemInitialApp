import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;


class EditMenu extends JFrame {
    private Database database;
    private JScrollPane scrollPane;
    private GridBagLayout gbl;
    private GridBagConstraints constraints;
    private JPanel edit_menu_panel;
    
    EditMenu() {
        setTitle("Edit Menu");
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
        edit_menu_panel = new JPanel();
        gbl = new GridBagLayout();
        edit_menu_panel.setLayout(gbl);

        JButton back_to_inventory_button = new JButton("Back to Inventory");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 5, 30);
        gbl.setConstraints(back_to_inventory_button, constraints);

        JButton logout_button = new JButton("Logout");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 30, 5, 0);
        gbl.setConstraints(logout_button, constraints);
        
        JLabel edit_menu_label = new JLabel("Edit Menu");
        constraints = new GridBagConstraints();
        constraints.gridwidth = 2;
        constraints.gridx = 1;
        constraints.gridy = 1;
        gbl.setConstraints(edit_menu_label, constraints);
        Font labelFont = new Font("Arial", Font.BOLD, 20);
        edit_menu_label.setFont(labelFont);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4);
        edit_menu_label.setBorder(border);

        JLabel item_name_label = new JLabel("Menu Item");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gbl.setConstraints(item_name_label, constraints);
        
        JLabel item_price_label = new JLabel("Price");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        gbl.setConstraints(item_price_label, constraints);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> inventoryList = new JList<>(listModel);

        try {
            String sql = "SELECT name_of_item, cost_of_item FROM menu;";
            ResultSet resultSet = database.getData(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name_of_item");
                double quantity = resultSet.getDouble("cost_of_item");

                // Format the name and quantity and add it to the list model
                String formattedEntry = String.format("%-35s %10s ----$%.2f----", name, "", quantity);
                listModel.addElement(formattedEntry);
            }

            resultSet.close();
        } catch (SQLException e) {
            System.out.println("Error: Could not load menu");
            e.printStackTrace();
            System.exit(0);
        }

        scrollPane = new JScrollPane(inventoryList);
        scrollPane.setPreferredSize(new Dimension(400, 125));

        constraints = new GridBagConstraints();
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 3;
        gbl.setConstraints(scrollPane, constraints);

        JButton add_new_item_button = new JButton("Add New Item");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(add_new_item_button, constraints);

        JButton edit_item_button = new JButton("Edit Selected Item");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(edit_item_button, constraints);

        JButton remove_selected_button = new JButton("Delete Selected Item");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(remove_selected_button, constraints);
        
        edit_menu_panel.add(back_to_inventory_button);
        edit_menu_panel.add(logout_button);
        edit_menu_panel.add(edit_menu_label);
        edit_menu_panel.add(item_name_label);
        edit_menu_panel.add(item_price_label);
        edit_menu_panel.add(edit_item_button);
        edit_menu_panel.add(scrollPane);
        edit_menu_panel.add(add_new_item_button);
        edit_menu_panel.add(remove_selected_button);

        back_to_inventory_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new Inventory().setVisible(true);
                }
            }
        );

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

        edit_item_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    double temp_price = 0;

                    String temp_name = inventoryList.getSelectedValue();
                    temp_name = temp_name.substring(0, temp_name.indexOf("  "));
                    JPanel edit_dialog = new JPanel(new GridLayout(0, 1));
                    
                    edit_dialog.add(new JLabel("What would you like to change the price of " + temp_name + " to?"));
                    try {
                        ResultSet query = database.getData("SELECT cost_of_item FROM menu WHERE name_of_item = \'" + temp_name + "\';");
                            if(query.next()) {
                                temp_price = query.getDouble("cost_of_item");
                            }
                    } catch (Exception c) {
                        c.printStackTrace();
                        System.out.println("Error: Could not find menu item price.");
                    }
                    JTextField new_price_field = new JTextField((String.format("%.2f", temp_price)) , 5);

                    edit_dialog.add(new_price_field);

                    int result = JOptionPane.showConfirmDialog(edit_menu_panel, edit_dialog, "Confirm", JOptionPane.OK_CANCEL_OPTION);

                    String temp_type = "";
                    if(result == JOptionPane.OK_OPTION) {
                        try {
                            ResultSet query = database.getData("SELECT type FROM menu WHERE name_of_item = \'" + temp_name + "\'");
                                if(query.next()) {
                                    temp_type = query.getString("type");
                                }
                        } catch (Exception c) {
                            c.printStackTrace();
                            System.out.println("Error: Could not find menu item type.");
                        }

                        try {
                            String sql = "UPDATE menu SET cost_of_item = " + new_price_field.getText() + " WHERE name_of_item = \'" + temp_name + "\';";
                            database.addData(sql);
                        } catch (Exception d) {
                            d.printStackTrace();
                            System.out.println("Could not update price of " + temp_name + " in menu");
                        }

                        if(temp_type.equals("Topping")) {
                            try {
                                String sql = "UPDATE inventory SET price = " + new_price_field.getText() + " WHERE name = \'" + temp_name + "\';";
                                database.addData(sql);
                            } catch (Exception d) {
                                d.printStackTrace();
                                System.out.println("Could not update price of " + temp_name + " in inventory");
                            }
                        }
                    }
                }
            }
        );

        add_new_item_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel add_choice_dialog = new JPanel(new GridLayout(0, 1));
                    add_choice_dialog.add(new JLabel("Would you like to add a new Drink or Topping?"));
                    JButton new_drink_button = new JButton("New Drink");
                    JButton new_topping_button = new JButton("New Topping");

                    new_drink_button.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // //add_choice_dialog.setVisible(false);
                                // JPanel add_dialog = new JPanel(new GridLayout(0, 1));

                                // add_dialog.add(new JLabel("Enter the name of the new item (as it should appear on the menu)"));
                                // JTextField new_item_name = new JTextField();
                                // add_dialog.add(new_item_name);

                                // add_dialog.add(new JLabel("Enter the price of the new item (no $)"));
                                // JTextField new_item_price = new JTextField();
                                // add_dialog.add(new_item_price);

                                // int result = JOptionPane.showConfirmDialog(edit_menu_panel, add_dialog, "Create New Drink", JOptionPane.OK_CANCEL_OPTION);

                                // if(result == JOptionPane.OK_OPTION) {
                                //     try {
                                //         String sql = "INSERT INTO menu (type, name_of_item, cost_of_item, numbersoldtoday)";
                                //         sql += " VALUES (\'Drink\', \'" + new_item_name.getText() + "\', \'" + new_item_price.getText() + "\', 0);";
                                //         database.addData(sql);
                                //     } catch (Exception d) {
                                //         d.printStackTrace();
                                //         System.out.println("Could not add drink");
                                //     }
                                // }
                                dispose();
                                new AddNewDrink().setVisible(true);
                            }
                        }
                    );

                    new_topping_button.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JPanel add_dialog = new JPanel(new GridLayout(0, 1));

                                add_dialog.add(new JLabel("Enter the name of the new item (as it should appear on the menu)"));
                                JTextField new_item_name = new JTextField();
                                add_dialog.add(new_item_name);

                                add_dialog.add(new JLabel("Enter the category of the new item\n(Type, Fruits, Milk, Recipe, Add-Ons)"));
                                JTextField new_item_category = new JTextField();
                                add_dialog.add(new_item_category);

                                add_dialog.add(new JLabel("Enter the price of the new item (no $)"));
                                JTextField new_item_price = new JTextField();
                                add_dialog.add(new_item_price);

                                int result = JOptionPane.showConfirmDialog(edit_menu_panel, add_dialog, "Create New Topping", JOptionPane.OK_CANCEL_OPTION);

                                if(result == JOptionPane.OK_OPTION) {
                                    try {
                                        String sql = "INSERT INTO inventory (name, category, quantity, price, min)";
                                        sql += " VALUES (\'" + new_item_name.getText() + "\', \'" + new_item_category.getText() + "\', 0, \'" + new_item_price.getText() + "\', 10);";
                                        database.addData(sql);
                                    } catch (Exception d) {
                                        d.printStackTrace();
                                        System.out.println("Could not add topping");
                                    }

                                    if(new_item_category.getText().equals("Add-Ons")) {
                                        try {
                                            String sql = "INSERT INTO menu (type, name_of_item, cost_of_item, numbers_sold_during_day, ingredients)";
                                            sql += " VALUES (\'Topping\', \'" + new_item_name.getText() + "\', \'" + new_item_price.getText() + "\', 0, 'N/A');";
                                            database.addData(sql);
                                        } catch (Exception d) {
                                            d.printStackTrace();
                                            System.out.println("Could not add topping");
                                        }
                                    }
                                }
                            }
                        }
                    );

                    add_choice_dialog.add(new_drink_button);
                    add_choice_dialog.add(new_topping_button);

                    JOptionPane.showConfirmDialog(edit_menu_panel, add_choice_dialog, "Drink or Topping?", JOptionPane.DEFAULT_OPTION);            
                }
            }
        );

        remove_selected_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String temp_name = inventoryList.getSelectedValue();
                    temp_name = temp_name.substring(0, temp_name.indexOf("  "));
                    JPanel delete_dialog = new JPanel(new GridLayout(0, 1));
                    
                    delete_dialog.add(new JLabel("Are you sure you want to remove"));
                    delete_dialog.add(new JLabel("\"" + temp_name + "\""));
                    delete_dialog.add(new JLabel("from the menu?"));
                    
                    int result = JOptionPane.showConfirmDialog(edit_menu_panel, delete_dialog, "Delete Item", JOptionPane.OK_CANCEL_OPTION);

                    String temp_type = "";
                    if(result == JOptionPane.OK_OPTION) {
                        try {
                            ResultSet query = database.getData("SELECT type FROM menu WHERE name_of_item = \'" + temp_name + "\'");
                                if(query.next()) {
                                    temp_type = query.getString("type");
                                }
                        } catch (Exception c) {
                            c.printStackTrace();
                            System.out.println("Error: Could not find menu item type.");
                        }

                        if(temp_type.equals("Drink")) {
                            try {
                                String sql = "DELETE FROM menu WHERE name_of_item = \'" + temp_name + "\';";
                                database.addData(sql);
                            } catch (Exception d) {
                                d.printStackTrace();
                                System.out.println("Error: Could not delete \"" + temp_name + "\" from menu.");
                            }
                        } else if((temp_type.equals("Topping")) && !(temp_name.equals("Lychee Bits") || temp_name.equals("Strawberry"))) {
                            try {
                                String sql = "DELETE FROM menu WHERE name_of_item = \'" + temp_name + "\';";
                                database.addData(sql);
                            } catch (Exception d) {
                                d.printStackTrace();
                                System.out.println("Error: Could not delete \"" + temp_name + "\" from menu.");
                            }

                            try {
                                String sql = "DELETE FROM inventory WHERE name = \'" + temp_name + "\';";
                                database.addData(sql);
                            } catch (Exception d) {
                                d.printStackTrace();
                                System.out.println("Error: Could not delete \"" + temp_name + "\" from inventory.");
                            }
                        }
                    }
                }
            }
        );

        add(edit_menu_panel);
    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}