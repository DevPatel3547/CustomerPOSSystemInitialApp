import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.*;
import java.util.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import javax.naming.spi.DirStateFactory.Result;

class DrinkMenu extends JFrame {
    private int curCard = 1;
    private CardLayout cardLayout;
    private ArrayList<Drink> currentOrder;
    private JPanel cardPanel;
    private JPanel flavorMenu;
    private JScrollPane scrollPane;
    private JPanel flavorsPanel;
    private Database database;
    private String currDrinkName;
    
    public DrinkMenu() {
        database = new Database();
        database.openJDBC();
        currentOrder = new ArrayList<Drink>();
        setTitle("Drink Menu");
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


        flavorMenu = new JPanel();
        JPanel customize = new JPanel();
        JPanel cartPanel = new JPanel();

        //--------Flavor Menu Here---------------

        JLabel flavorTitle = new JLabel("Choose a Drink");
        flavorMenu.setLayout(new BorderLayout());
        flavorMenu.add(flavorTitle, BorderLayout.NORTH);

        JButton login = new JButton("Log Out");
        flavorMenu.add(login, BorderLayout.WEST);

        JButton cart = new JButton("Cart");
        flavorMenu.add(cart, BorderLayout.SOUTH);

        flavorsPanel = new JPanel();
        flavorsPanel.setLayout(new BoxLayout(flavorsPanel, BoxLayout.Y_AXIS));

        scrollPane = new JScrollPane(flavorsPanel);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);


        ArrayList<String> flavors = new ArrayList<String>();
        ArrayList<Double> prices = new ArrayList<Double>();
        try {
            ResultSet query = database.getData("SELECT \"nameofitem\" AS Drink, \"cost\" AS Price, \"numbersoldtoday\" AS SoldDuringDay FROM menu WHERE Type = 'Drink';");

            while (query.next()) {
                flavors.add(query.getString("drink"));
                prices.add(query.getDouble("price"));
                // System.out.println("Drink: " + query.getString("drink"));
                // System.out.println("Price: " + query.getString("price"));
                // System.out.println("Number Sold: " + query.getString("soldduringday"));
            }
        } catch (Exception e) {
            System.out.println("ERROR:");
            e.printStackTrace();
            System.exit(0);
        }

        for (int i = 0; i < flavors.size(); ++i) {
            createFlavor(flavors.get(i), prices.get(i), i);
        }

        flavorMenu.add(scrollPane, BorderLayout.CENTER);

        cardPanel.add(flavorMenu, "1");

        //--------Drink Customization Here---------------

        // customize.setLayout(new BoxLayout(customize, BoxLayout.Y_AXIS));
        GridBagLayout gb2 = new GridBagLayout();
        customize.setLayout(gb2);

        JLabel customizeTitle = new JLabel("Customize Drink");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        gb2.setConstraints(customizeTitle, constraints);
        customize.add(customizeTitle);

        JButton save = new JButton("Save");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        gb2.setConstraints(save, constraints);
        customize.add(save);

        JLabel customizeDividerLeft = new JLabel("----------");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 1;
        gb2.setConstraints(customizeDividerLeft, constraints);
        customize.add(customizeDividerLeft);
        JLabel customizeDividerMid = new JLabel("----------");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 1;
        gb2.setConstraints(customizeDividerMid, constraints);
        customize.add(customizeDividerMid);
        JLabel customizeDividerRight = new JLabel("----------");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 1;
        gb2.setConstraints(customizeDividerRight, constraints);
        customize.add(customizeDividerRight);



        Vector<String> sizeOptions = new Vector<String>();
        Vector<String> milkOptions = new Vector<String>();
        String iceOptions[] = {"No Ice", "Less Ice", "Regular Ice", "Extra Ice"};
        String sugarOptions[] = {"No Sugar", "25% Sugar", "50% Sugar", "75% Sugar", "100% Sugar"};
        String bobaOptions[] = {"No Boba", "Less Boba", "Default Amount of Boba", "Extra Boba (+$0.60)", "Extra Extra Boba (+$1.20)"};
        Vector<String> toppingOptions = new Vector<String>();

        try {
            ResultSet query2 = database.getData("SELECT * FROM inventory WHERE category = 'Cup';");
            while (query2.next()) {
                sizeOptions.add(query2.getString("name"));
                // System.out.println(query2.getString("name"));
            }
            query2 = database.getData("SELECT * FROM inventory WHERE category = 'Milk';");
            while (query2.next()) {
                milkOptions.add(query2.getString("name"));
                // System.out.println(query2.getString("name"));
            }
            query2 = database.getData("SELECT * FROM inventory WHERE category = 'Add-Ons';");
            while (query2.next()) {
                toppingOptions.add(query2.getString("name"));
                // System.out.println(query2.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }


        JComboBox sizeDropDown = new JComboBox(sizeOptions);
        JComboBox milkDropDown = new JComboBox(milkOptions);
        JComboBox iceDropDown = new JComboBox(iceOptions);
        JComboBox sugarDropDown = new JComboBox(sugarOptions);
        JComboBox bobaDropDown = new JComboBox(bobaOptions);
        JComboBox toppingsDropDown = new JComboBox(toppingOptions);

        // JLabel sizeDivider = new JLabel("-----------------------------------------------------");
        // customize.add(sizeDivider);
        JLabel sizeTitle = new JLabel("Choose Size:");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gb2.setConstraints(sizeTitle, constraints);
        customize.add(sizeTitle);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        gb2.setConstraints(sizeDropDown, constraints);
        customize.add(sizeDropDown);
        

        JLabel milkTitle = new JLabel("Choose Milk:");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 3;
        gb2.setConstraints(milkTitle, constraints);
        customize.add(milkTitle);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 3;
        gb2.setConstraints(milkDropDown, constraints);
        customize.add(milkDropDown);

        JLabel iceTitle = new JLabel("Choose Ice Level:");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 4;
        gb2.setConstraints(iceTitle, constraints);
        customize.add(iceTitle);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 4;
        gb2.setConstraints(iceDropDown, constraints);
        customize.add(iceDropDown);

        JLabel sugarTitle = new JLabel("Choose Sugar Level:");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 5;
        gb2.setConstraints(sugarTitle, constraints);
        customize.add(sugarTitle);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 5;
        gb2.setConstraints(sugarDropDown, constraints);
        customize.add(sugarDropDown);

        JLabel bobaTitle = new JLabel("Choose Boba Level:");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 6;
        gb2.setConstraints(bobaTitle, constraints);
        customize.add(bobaTitle);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 6;
        gb2.setConstraints(bobaDropDown, constraints);
        customize.add(bobaDropDown);

        JLabel toppingsTitle = new JLabel("Choose Toppings:");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 7;
        gb2.setConstraints(toppingsTitle, constraints);
        customize.add(toppingsTitle);

        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 7;
        gb2.setConstraints(toppingsDropDown, constraints);
        customize.add(toppingsDropDown);

        cardPanel.add(customize, "2");


        //---------------------------------------

        //-------------Cart Here-----------------

        GridBagLayout gb3 = new GridBagLayout();
        cartPanel.setLayout(gb3);
        cartPanel.setSize(400, 200);

        JButton delete = new JButton("X");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        gb3.setConstraints(delete, constraints);
        cartPanel.add(delete);

        JLabel orderName = new JLabel("Order-Name ");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        gb3.setConstraints(orderName, constraints);
        cartPanel.add(orderName);

        JLabel orderPrice = new JLabel(" Order-Price");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        gb3.setConstraints(orderPrice, constraints);
        cartPanel.add(orderPrice);




        cardPanel.add(cartPanel, "3");


        //---------------------------------------

        login.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new Login().setVisible(true);
                }
            }
        );

        cart.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    curCard = 3;
                    cardLayout.show(cardPanel, "" + curCard);
                }
            }
        );

        save.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    curCard = 1;
                    cardLayout.show(cardPanel, "" + curCard);
                }
            }
        );

        add(cardPanel);
    }

    private void createFlavor(String flavor, double cost, int index) {
        JPanel flavorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel name = new JLabel(flavor);

        JLabel price = new JLabel("$" + cost);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curCard = 2;
                currDrinkName = flavor;
                cardLayout.show(cardPanel, "" + curCard);
            }
        });

        JLabel divider1 = new JLabel("---");
        JLabel divider2 = new JLabel("---");

        flavorPanel.add(name);
        flavorPanel.add(divider1);
        flavorPanel.add(price);
        flavorPanel.add(divider2);
        flavorPanel.add(addButton);

        flavorsPanel.add(flavorPanel);

    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}