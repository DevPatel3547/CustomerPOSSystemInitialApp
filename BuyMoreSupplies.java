import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.naming.spi.DirStateFactory.Result;

class BuyMoreSupplies extends JFrame {
    //private int curCard = 1;
    private CardLayout cardLayout;
    private ArrayList<String> currentCartList;
    private ArrayList<JTextField> currentCartQuantityRequested;
    private JPanel cardPanel;
    private JPanel buyMoreMenu;
    private JPanel cartPanel;
    private JScrollPane itemScrollPane;
    private JScrollPane cartScrollPane;
    private JPanel itemsPanel;
    private JPanel cart_list_panel;
    private Database database;
    
    public BuyMoreSupplies() {
        database = new Database();
        database.openJDBC();
        currentCartList = new ArrayList<String>();
        currentCartQuantityRequested = new ArrayList<JTextField>();
        setTitle("Buy More Supplies");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeDatabase();
                System.exit(0);
            }
        });

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        buyMoreMenu = new JPanel();
        cartPanel = new JPanel();

        // --------- Beginning of the Items Panel --------- // Card 1
        buyMoreMenu.setLayout(new BorderLayout());
        JLabel buy_more_supplies_label = new JLabel("Add items you wish to purchase to your cart");
        buyMoreMenu.add(buy_more_supplies_label, BorderLayout.NORTH);

        JButton back_to_inventory = new JButton("Back to Inventory");
        buyMoreMenu.add(back_to_inventory, BorderLayout.WEST);

        JButton cart_button = new JButton("Cart");
        buyMoreMenu.add(cart_button, BorderLayout.SOUTH);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        itemScrollPane = new JScrollPane(itemsPanel);
        itemScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        ArrayList<String> item_names = new ArrayList<String>();
        ArrayList<Double> item_prices = new ArrayList<Double>();
        try {
            String sql = "SELECT name, price FROM inventory;";
            ResultSet query = database.getData(sql);

            while (query.next()) {
                item_names.add(query.getString("name"));
                item_prices.add(query.getDouble("price"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not load supply purchase menu");
            e.printStackTrace();
            System.exit(0);
        }

        for (int i = 0; i < item_names.size(); ++i) {
            createItemsList(item_names.get(i), item_prices.get(i));
        }

        buyMoreMenu.add(itemScrollPane, BorderLayout.CENTER);
        cardPanel.add(buyMoreMenu, "1");

        // --------- Beginning of the Items Panel --------- // Card 2

        JLabel cartTitle = new JLabel("Your Cart: Type the Quantities you wish to buy for each supply in the field");
        cartPanel.setLayout(new BorderLayout());
        cartPanel.add(cartTitle, BorderLayout.NORTH);

        JButton back_to_menu = new JButton("Back");
        cartPanel.add(back_to_menu, BorderLayout.WEST);

        JButton checkout = new JButton("Checkout");
        cartPanel.add(checkout, BorderLayout.SOUTH);

        cart_list_panel = new JPanel();
        cart_list_panel.setLayout(new BoxLayout(cart_list_panel, BoxLayout.Y_AXIS));

        ArrayList<String> tempList = new ArrayList<String>();
        tempList = currentCartList;

        for (int i = 0; i < currentCartList.size(); ++i) {
            JPanel cartItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel cartItemName = new JLabel(currentCartList.get(i));
            
            double cartItemPrice = 0;

            try {
                ResultSet query = database.getData("SELECT price FROM inventory WHERE name = \'" + currentCartList.get(i) + "\';");

                if(query.next()) {
                    cartItemPrice = query.getDouble("price");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Could not item price");
                e.printStackTrace();
                System.exit(0);
            }

            JLabel cartItemPrice_label = new JLabel(String.format("---- $%.2f ----", cartItemPrice));

            JButton removeItem = new JButton("X");
            final int itemIndex = i;
            removeItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentCartList.remove(itemIndex);
                    currentCartQuantityRequested.clear();
                    updateItemsPanel();
                    updateCartPanel();             
                }
            });

            JTextField quantity_wanted = new JTextField(4);
            quantity_wanted.setText("1");
            quantity_wanted.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (quantity_wanted.getText().isEmpty()) {
                        quantity_wanted.setText("1");
                    }
                }
            });
            currentCartQuantityRequested.add(quantity_wanted);

            currentCartQuantityRequested.add(quantity_wanted);

            cartItem.add(cartItemName);
            cartItem.add(cartItemPrice_label);
            cartItem.add(removeItem);
            cartItem.add(quantity_wanted, FlowLayout.RIGHT);

            cart_list_panel.add(cartItem);
        }

        cartPanel.add(cart_list_panel, BorderLayout.CENTER);
        cardPanel.add(cartPanel, "2");

        // --------- Button Functionality ----------------- //

        back_to_inventory.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new Inventory().setVisible(true);
                }
            }
        );

        back_to_menu.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    cardLayout.show(cardPanel, "1");
                }
            }
        );

        cart_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(currentCartList.size() == 0) {
                        JOptionPane.showMessageDialog(null, "You have no supplies in your shopping cart.");
                    } else {
                        updateCartPanel();
                        cardLayout.show(cardPanel, "2");
                    }
                }
            }
        );

        checkout.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(currentCartList.size() == 0) {
                        JOptionPane.showMessageDialog(null, "You have no supplies in your shopping cart.");
                    } else {
                        String toPrint = "Order Rundown:\n-------------\nName: Quantity\n";
                        double totalPrice = 0;
                        for(int i = 0; i < currentCartList.size(); ++i) {
                            toPrint += currentCartList.get(i) + ": " + currentCartQuantityRequested.get(i).getText() + "\n";
                            try {
                                ResultSet query = database.getData("SELECT price FROM inventory WHERE name = \'" + currentCartList.get(i) + "\';");
                                if(query.next()) {
                                    totalPrice += query.getDouble("price") * Integer.parseInt(currentCartQuantityRequested.get(i).getText());
                                }
                            } catch (Exception c) {
                                System.out.println("ERROR: Could load not item price");
                                c.printStackTrace();
                                System.exit(0);
                            }
                        }
                        toPrint += String.format("-------------\nOrder Total: $%.2f", totalPrice);
                        JOptionPane.showMessageDialog(null, toPrint);
                        for(int i = 0; i < currentCartList.size(); ++i) {
                            try {
                                String sql = "UPDATE inventory SET Quantity = Quantity + " + currentCartQuantityRequested.get(i).getText() + " WHERE Name = \'" + currentCartList.get(i) + "\';";
                                database.addData(sql);
                            } catch (Exception d) {
                                d.printStackTrace();
                                System.out.println("Could not purchase: " + currentCartList.get(i));
                            }
                        }
                    }
                }
            }
        );

        add(cardPanel);
    }

    private void createItemsList(String item_name, double item_price) {
        JPanel itemPanel = new JPanel(new BorderLayout());

        JLabel name = new JLabel(item_name);

        JLabel price = new JLabel(String.format("---- $%5.2f ----", item_price), SwingConstants.CENTER);

        JButton addButton = new JButton("Add");

        if(currentCartList.contains(item_name)) {
            addButton.setEnabled(false);
        } else {
            addButton.setEnabled(true);
        }

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currentCartList.contains(item_name)) {
                    addButton.setEnabled(false);
                } else {
                    currentCartList.add(item_name);
                    addButton.setEnabled(false);
                }  
            }
        });

        itemPanel.add(name, BorderLayout.WEST);
        itemPanel.add(price, BorderLayout.CENTER);
        itemPanel.add(addButton, BorderLayout.EAST);

        itemsPanel.add(itemPanel);

    }

    private void updateCartPanel() {
        cart_list_panel.removeAll();
        currentCartQuantityRequested.clear(); 
        for (int i = 0; i < currentCartList.size(); ++i) {
            JPanel cartItem = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel cartItemName = new JLabel(currentCartList.get(i));

            double cartItemPrice = 0;

            try {
                ResultSet query = database.getData("SELECT price FROM inventory WHERE name = \'" + currentCartList.get(i) + "\';");
                if(query.next()) {
                    cartItemPrice = query.getDouble("price");
                }
            } catch (Exception e) {
                System.out.println("ERROR: Could not load item price");
                e.printStackTrace();
                System.exit(0);
            }

            JLabel cartItemPrice_label = new JLabel(String.format("---- $%.2f ----", cartItemPrice));

            JButton removeItem = new JButton("X");
            final int itemIndex = i;
            removeItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    currentCartList.remove(itemIndex);
                    currentCartQuantityRequested.clear();
                    updateItemsPanel();
                    updateCartPanel(); 
                }
            });

            JTextField quantity_wanted = new JTextField(4);
            quantity_wanted.setText("1");
            quantity_wanted.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    if (quantity_wanted.getText().isEmpty()) {
                        quantity_wanted.setText("1");
                    }
                }
            });
            currentCartQuantityRequested.add(quantity_wanted);

            cartItem.add(cartItemName);
            cartItem.add(cartItemPrice_label);
            cartItem.add(removeItem);
            cartItem.add(quantity_wanted, FlowLayout.RIGHT);

            cart_list_panel.add(cartItem);
        }
        cart_list_panel.revalidate();
        cart_list_panel.repaint();
    }

    private void updateItemsPanel() {
        itemsPanel.removeAll();
        ArrayList<String> item_names = new ArrayList<String>();
        ArrayList<Double> item_prices = new ArrayList<Double>();
        try {
            String sql = "SELECT name, price FROM inventory;";
            ResultSet query = database.getData(sql);

            while (query.next()) {
                item_names.add(query.getString("name"));
                item_prices.add(query.getDouble("price"));
            }
        } catch (Exception e) {
            System.out.println("ERROR: Could not load supply purchase menu");
            e.printStackTrace();
            System.exit(0);
        }

        for (int i = 0; i < item_names.size(); ++i) {
            createItemsList(item_names.get(i), item_prices.get(i));
        }

        buyMoreMenu.revalidate();
        buyMoreMenu.repaint();
    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}