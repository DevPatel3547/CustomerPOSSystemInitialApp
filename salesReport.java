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


class salesReport extends JFrame {
    private Database database;
    
    salesReport(Set<String> selectedDates) {
        setTitle("Sales Report");
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
        JPanel salesReport_panel = new JPanel();
        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        salesReport_panel.setLayout(gbl);


        JButton back_button = new JButton("Back Button");//    BACK BUTTON
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 5, 30);
        gbl.setConstraints(back_button, constraints);

        JButton to_sales_together_button = new JButton("Sales Together");// SALES TOGETHER
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 30, 5, 0);
        gbl.setConstraints(to_sales_together_button, constraints);
        
        JLabel current_invetory_label = new JLabel("Current Sales Report"); // SALES REPORT
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
        
        JLabel item_quantity_label = new JLabel("Total Sales of item");// TYPE OF ITEM
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 2;
        gbl.setConstraints(item_quantity_label, constraints);

        DefaultListModel<String> listModel = new DefaultListModel<>();
        JList<String> salesReportList = new JList<>(listModel);
        try {
            String date1 = selectedDates.iterator().next();
            String date2 = selectedDates.toArray()[1].toString();
            String sql = "SELECT flavor, SUM(totalprice) AS \"Total Sales\" FROM orderhistory WHERE date BETWEEN '" + date1 + "' AND '" + date2 + "' GROUP BY flavor ORDER BY \"Total Sales\" DESC;";
            ResultSet resultSet = database.getData(sql);
        
            while (resultSet.next()) {
                String flavor = resultSet.getString("flavor");
                double totalSales = resultSet.getDouble("Total Sales");
        
                // Format the flavor and total sales and add it to the list model
                String formattedEntry = String.format("%-70s %.2f", flavor, totalSales);
                listModel.addElement(formattedEntry);
            }

            resultSet.close();
           
        } catch (SQLException e) {
            System.out.println("Error: Could not load sales report");
            e.printStackTrace();
            System.exit(0);
        }

        // Create a custom cell renderer for right-aligning the quantities
        salesReportList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component component = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                setHorizontalAlignment(SwingConstants.RIGHT);
                return component;
            }
        });

        JScrollPane scrollPane = new JScrollPane(salesReportList);
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

        
        
        salesReport_panel.add(back_button);
        //salesReport_panel.add(to_sales_together_button);
        salesReport_panel.add(current_invetory_label);
        salesReport_panel.add(item_name_label);
        salesReport_panel.add(item_quantity_label);
        salesReport_panel.add(buy_more_supplies_button);
        salesReport_panel.add(scrollPane);


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

        to_sales_together_button.addActionListener(
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

        add(salesReport_panel);
    }

    private void closeDatabase() {
        database.closeJDBC();
    }
}