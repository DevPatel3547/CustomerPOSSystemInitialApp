import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.*;

class DrinkMenu extends JFrame {
    private int curCard = 1;
    private CardLayout cardLayout;
    
    public DrinkMenu() {
        setTitle("Drink Menu");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);


        JPanel flavorMenu = new JPanel();
        JPanel customize = new JPanel();
        JPanel cartPanel = new JPanel();

        //--------Flavor Menu Here---------------

        GridBagLayout gb1 = new GridBagLayout();
        GridBagConstraints constraints;
        flavorMenu.setLayout(gb1);
        flavorMenu.setSize(400, 200);

        JLabel flavorTitle = new JLabel("Choose a Drink");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 0;
        gb1.setConstraints(flavorTitle, constraints);
        flavorMenu.add(flavorTitle);

        JButton login = new JButton("Log Out");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        gb1.setConstraints(login, constraints);
        flavorMenu.add(login);

        JButton cart = new JButton("Cart");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 0;
        gb1.setConstraints(cart, constraints);
        flavorMenu.add(cart);

        // JLabel divider = new JLabel("----------------------");
        // constraints = new GridBagConstraints();
        // constraints.gridy = 1;
        // gb1.setConstraints(divider, constraints);
        // flavorMenu.add(divider);

        //maybe look at anonymous buttons and labels so that we can use a for loop to create these
        JLabel drinkName = new JLabel("Drink Name");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gb1.setConstraints(drinkName, constraints);

        JLabel price = new JLabel("Price");
        constraints = new GridBagConstraints();
        constraints.gridx = 1;
        constraints.gridy = 2;
        gb1.setConstraints(price, constraints);

        JButton add = new JButton("Add");
        constraints = new GridBagConstraints();
        constraints.gridx = 2;
        constraints.gridy = 2;
        gb1.setConstraints(add, constraints);
        
        flavorMenu.add(drinkName);
        flavorMenu.add(price);
        flavorMenu.add(add);

        cardPanel.add(flavorMenu, "1");


        //---------------------------------------

        //--------Drink Customization Here---------------

        // GridBagLayout gb2 = new GridBagLayout();
        // customize.setLayout(gb2);
        // customize.setSize(400, 200);

        // //maybe look at anonymous buttons and labels so that we can use a for loop to create these
        // JLabel flavorTitle = new JLabel("Drink Name");
        // constraints = new GridBagConstraints();
        // constraints.gridx = 0;
        // constraints.gridy = 0;
        // gb1.setConstraints(flavorTitle, constraints);

        // JLabel price = new JLabel("Price");
        // constraints = new GridBagConstraints();
        // constraints.gridx = 1;
        // constraints.gridy = 0;
        // gb1.setConstraints(price, constraints);

        // JButton add = new JButton("Add");
        // constraints = new GridBagConstraints();
        // constraints.gridx = 2;
        // constraints.gridy = 0;
        // gb1.setConstraints(add, constraints);
        
        // flavorMenu.add(flavorTitle);
        // flavorMenu.add(price);
        // flavorMenu.add(add);

        cardPanel.add(flavorMenu, "1");



        //---------------------------------------

        login.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    new Login().setVisible(true);
                }
            }
        );

        add(cardPanel);
    }
}