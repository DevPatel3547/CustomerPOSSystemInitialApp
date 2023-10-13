import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
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
    private JPanel cardPanel;
    private JPanel buyMoreMenu;
    private JScrollPane scrollPane;
    private JPanel itemsPanel;
    private Database database;
    
    public BuyMoreSupplies() {
        database = new Database();
        database = new Database();
        database.openJDBC();
        currentCartList = new ArrayList<String>();
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
        //JPanel cartPanel = new JPanel();

        // --------- Beginning of the Items Panel ---------
        JLabel buy_more_supplies_label = new JLabel("Add items you wish to purchase to your cart");
        buyMoreMenu.setLayout(new BorderLayout());
        buyMoreMenu.add(buy_more_supplies_label, BorderLayout.NORTH);

        JButton back_to_inventory = new JButton("Back to Inventory");
        buyMoreMenu.add(back_to_inventory, BorderLayout.WEST);

        JButton cart = new JButton("Cart");
        buyMoreMenu.add(cart, BorderLayout.SOUTH);

        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(itemsPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

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
            createItemsList(item_names.get(i), item_prices.get(i), i);
        }

        buyMoreMenu.add(scrollPane, BorderLayout.CENTER);
        cardPanel.add(buyMoreMenu, "1");

        add(cardPanel);
    }

    private void createItemsList(String item_name, double item_price, int index) {
        JPanel itemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel name = new JLabel(item_name);

        JLabel price = new JLabel("" + item_price);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentCartList.add(item_name);
            }
        });

        itemPanel.add(name);
        itemPanel.add(price);
        itemPanel.add(addButton);

        itemsPanel.add(itemPanel);

    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}