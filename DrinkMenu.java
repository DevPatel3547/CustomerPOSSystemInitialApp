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

        customize.setLayout(new BoxLayout(customize, BoxLayout.Y_AXIS));

        JPanel customizeTitlePanel = new JPanel();
        customizeTitlePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 200, 0));

        JLabel customizeTitle = new JLabel("Customize Drink");
        customizeTitlePanel.add(customizeTitle);
        JButton save = new JButton("Save");
        customizeTitlePanel.add(save);

        customize.add(customizeTitlePanel);

        String arr[] = {"Drink Options Here", "Drink Option 1", "Drink Option 2", "Drink Option 3"};
        JComboBox testOptions = new JComboBox(arr);
        customize.add(testOptions);

        cardPanel.add(customize, "2");


        //---------------------------------------

        //-------------Cart Here-----------------

        GridBagLayout gb3 = new GridBagLayout();
        cartPanel.setLayout(gb3);
        cartPanel.setSize(400, 200);

        JButton delete = new JButton("X");
        GridBagConstraints constraints = new GridBagConstraints();
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

        JLabel price = new JLabel("" + cost);

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curCard = 2;
                cardLayout.show(cardPanel, "" + curCard);
            }
        });

        flavorPanel.add(name);
        flavorPanel.add(price);
        flavorPanel.add(addButton);

        flavorsPanel.add(flavorPanel);

    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}