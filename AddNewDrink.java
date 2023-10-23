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

public class AddNewDrink extends JFrame {
    private Database database; 

    AddNewDrink() {
        setTitle("Add New Drink");
        setSize(1500, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //setResizable(false);

        database = new Database();
        database.openJDBC();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                database.closeJDBC();
                System.exit(0);
            }
        });

        Vector<String> type_options = new Vector<String>();
        try {
            ResultSet query = database.getData("SELECT name FROM inventory WHERE category = 'Type';");
            while (query.next()) {
                type_options.add(query.getString("name"));
            }
        } catch (Exception t) {
            System.out.println("Could not load type options");
        }

        Vector<String> fruit_options = new Vector<String>();
        try {
            ResultSet query = database.getData("SELECT name FROM inventory WHERE category = 'Fruit';");
            while (query.next()) {
                fruit_options.add(query.getString("name"));
            }
        } catch (Exception t) {
            System.out.println("Could not load fruit options");
        }

        Vector<String> recipe_options = new Vector<String>();
        try {
            ResultSet query = database.getData("SELECT name FROM inventory WHERE category = 'Recipe';");
            while (query.next()) {
                recipe_options.add(query.getString("name"));
            }
        } catch (Exception t) {
            System.out.println("Could not load recipe options");
        }

        Vector<String> milk_options = new Vector<String>();
        try {
            ResultSet query = database.getData("SELECT name FROM inventory WHERE category = 'Milk';");
            while (query.next()) {
                milk_options.add(query.getString("name"));
            }
        } catch (Exception t) {
            System.out.println("Could not load milk options");
        }
        
        Vector<String> add_ons = new Vector<String>();
        try {
            ResultSet query = database.getData("SELECT name FROM inventory WHERE category = 'Add-Ons';");
            while (query.next()) {
                add_ons.add(query.getString("name"));
            }
        } catch (Exception t) {
            System.out.println("Could not load add-ons");
        }
        
        JPanel add_drink_panel = new JPanel();
        add_drink_panel.setLayout(new BoxLayout(add_drink_panel, BoxLayout.Y_AXIS));
        JScrollPane add_dialog_scroller = new JScrollPane();
        add_dialog_scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JButton back_to_edit = new JButton("Back");
        add_drink_panel.add(back_to_edit);

        add_drink_panel.add(new JLabel("Enter the name of the new drink (as it should appear on the menu)"));
        JTextField new_item_name = new JTextField();
        add_drink_panel.add(new_item_name);

        add_drink_panel.add(new JLabel("Enter the price for the new drink: "));
        JTextField new_item_price = new JTextField();
        add_drink_panel.add(new_item_price);

        // The panel that holds all type options
        add_drink_panel.add(new JLabel("Choose the type(s) of the drink:"));
        JPanel type_panel = new JPanel();
        type_panel.setLayout(new BoxLayout(type_panel, BoxLayout.Y_AXIS));
        JScrollPane typeScrollPane = new JScrollPane();
        typeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Vector<JCheckBox> typeCheckBoxes = new Vector<JCheckBox>();

        for (String x : type_options) {
            JCheckBox checkbox = new JCheckBox(x);
            typeCheckBoxes.add(checkbox);
            type_panel.add(checkbox);
        }

        typeScrollPane.setViewportView(type_panel);
        add_drink_panel.add(typeScrollPane);

        // The panel that holds all fruit options
        add_drink_panel.add(new JLabel("Choose fruit(s):"));
        JPanel fruit_panel = new JPanel();
        fruit_panel.setLayout(new BoxLayout(fruit_panel, BoxLayout.Y_AXIS));
        JScrollPane fruitScrollPane = new JScrollPane();
        fruitScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Vector<JCheckBox> fruitCheckboxes = new Vector<JCheckBox>();

        for (String x : fruit_options) {
            JCheckBox checkbox = new JCheckBox(x);
            fruitCheckboxes.add(checkbox);
            fruit_panel.add(checkbox);
        }

        fruitScrollPane.setViewportView(fruit_panel);
        add_drink_panel.add(fruitScrollPane);

        // The panel that holds all recipe options
        add_drink_panel.add(new JLabel("Choose other ingredient(s):"));
        JPanel recipe_panel = new JPanel();
        recipe_panel.setLayout(new BoxLayout(recipe_panel, BoxLayout.Y_AXIS));
        JScrollPane recipeScrollPane = new JScrollPane();
        recipeScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Vector<JCheckBox> recipeCheckBoxes = new Vector<JCheckBox>();

        for (String x : recipe_options) {
            JCheckBox checkbox = new JCheckBox(x);
            recipeCheckBoxes.add(checkbox);
            recipe_panel.add(checkbox);
        }

        recipeScrollPane.setViewportView(recipe_panel);
        add_drink_panel.add(recipeScrollPane);

        // The panel that holds all milk options
        add_drink_panel.add(new JLabel("Choose the type of milk(s):"));
        JPanel milk_panel = new JPanel();
        milk_panel.setLayout(new BoxLayout(milk_panel, BoxLayout.Y_AXIS));
        JScrollPane milkScrollPane = new JScrollPane();
        milkScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Vector<JCheckBox> milkCheckBoxes = new Vector<JCheckBox>();

        for (String x : milk_options) {
            JCheckBox checkbox = new JCheckBox(x);
            milkCheckBoxes.add(checkbox);
            milk_panel.add(checkbox);
        }

        milkScrollPane.setViewportView(milk_panel);
        add_drink_panel.add(milkScrollPane);

        // The panel that holds all add-on options
        add_drink_panel.add(new JLabel("Choose Add-On(s):"));
        JPanel add_ons_panel = new JPanel();
        add_ons_panel.setLayout(new BoxLayout(add_ons_panel, BoxLayout.Y_AXIS));
        JScrollPane addOnsScrollPane = new JScrollPane();
        addOnsScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        Vector<JCheckBox> addOnsCheckBoxes = new Vector<JCheckBox>();

        for (String x : add_ons) {
            JCheckBox checkbox = new JCheckBox(x);
            addOnsCheckBoxes.add(checkbox);
            add_ons_panel.add(checkbox);
        }

        addOnsScrollPane.setViewportView(add_ons_panel);
        add_drink_panel.add(addOnsScrollPane);
        JButton create_button = new JButton("Create");
        add_drink_panel.add(create_button);

        add_dialog_scroller.setViewportView(add_drink_panel);

        back_to_edit.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    database.closeJDBC();
                    dispose();
                    new EditMenu().setVisible(true);
                }
            }
        );

        create_button.addActionListener(
            new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String ingredientString = "";
                    System.out.println("The name of the drink you are creating is: " + new_item_name.getText());
                    for(JCheckBox cb : typeCheckBoxes) {
                        if(cb.isSelected()) {
                            ingredientString += cb.getText() + ", ";
                        }   
                    }
                    for(JCheckBox cb : milkCheckBoxes) {
                        if(cb.isSelected()) {
                            ingredientString += cb.getText() + ", ";
                        }   
                    }
                    for(JCheckBox cb : fruitCheckboxes) {
                        if(cb.isSelected()) {
                            ingredientString += cb.getText() + ", ";
                        }   
                    }
                    for(JCheckBox cb : recipeCheckBoxes) {
                        if(cb.isSelected()) {
                            ingredientString += cb.getText() + ", ";
                        }   
                    }
                    for(JCheckBox cb : addOnsCheckBoxes) {
                        if(cb.isSelected()) {
                            ingredientString += cb.getText() + ", ";
                        }   
                    }
                    ingredientString = ingredientString.substring(0, ingredientString.length() - 2);

                    try {
                        String sql = "INSERT INTO menu (type, name_of_item, cost_of_item, numbers_sold_during_day, ingredients)";
                        sql += " VALUES (\'Drink\', \'" + new_item_name.getText() + "\', \'" + new_item_price.getText() + "\', 0, \'" + ingredientString + "\');";
                        database.addData(sql);
                    } catch (Exception s) {
                        System.out.println("Could not create new drink");
                    }
                }
            }
        );

        add(add_drink_panel);
    }
}
