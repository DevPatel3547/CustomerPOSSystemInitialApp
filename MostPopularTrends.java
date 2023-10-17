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
import java.util.HashSet;

class MostPopularTrends extends JFrame {
    private Database database;
    private Set<String> selectedDates;
    private JTextField dateInputField;
   
    MostPopularTrends() {
        setTitle("Most Popular Trends");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        database = new Database();
        database.openJDBC();

        // Create and customize the manager panel UI here
        JPanel popular_trends_panel = new JPanel();
        

        GridBagLayout gbl = new GridBagLayout();
        GridBagConstraints constraints;
        popular_trends_panel.setLayout(gbl); // Set the layout to the panel

        JLabel label = new JLabel("Select two dates");
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 2;
        gbl.setConstraints(label, constraints);

        JButton back_button = new JButton("Back Button");//    BACK BUTTON
        constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 5, 30);
        gbl.setConstraints(back_button, constraints);

        JButton to_sales_report_button = new JButton("Enter Sales Report");
        constraints = new GridBagConstraints();
        constraints.gridx = 3;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 30, 5, 0);
        gbl.setConstraints(to_sales_report_button, constraints);


        DefaultListModel<String> listModel = new DefaultListModel<>();// for the scroll data
        JList<String> dateList = new JList<>(listModel);
        Set<String> uniqueDates = new HashSet<>(); // Set to store unique dates

        try {
            String sql = "SELECT date FROM orderhistory;";
            ResultSet resultSet = database.getData(sql);

            while (resultSet.next()) {
                String date = resultSet.getString("date");

                // Format the name and quantity and add it to the list model
                if (!uniqueDates.contains(date)) {
                    // Add the date to the set and list model
                    uniqueDates.add(date);
                    String formattedEntry = String.format("%s", date);
                    listModel.addElement(formattedEntry);
                }
            }

            resultSet.close();
            //database.closeJDBC();
        } catch (SQLException e) {
            System.out.println("Error: Could not load orderhistory");
            e.printStackTrace();
            System.exit(0);
        }
        dateList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());
                JLabel label = new JLabel(value.toString());
            
                panel.add(label, BorderLayout.CENTER);
                return panel;
            }
        });



        JScrollPane scrollDate2 = new JScrollPane(dateList);
        scrollDate2.setPreferredSize(new Dimension(200, 125));

        constraints = new GridBagConstraints();
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 5;
        gbl.setConstraints(scrollDate2, constraints);

        // Add the "Add" button
        JButton addButton = new JButton("Add");
        constraints = new GridBagConstraints();
        constraints.gridx = 4;
        constraints.gridy = 5;
        gbl.setConstraints(addButton, constraints);
        //
        
        dateInputField = new JTextField(15);
        constraints = new GridBagConstraints();
        constraints.gridwidth = 4;
        constraints.gridx = 0;
        constraints.gridy = 6;
        gbl.setConstraints(dateInputField, constraints);

        popular_trends_panel.add(label);
        popular_trends_panel.add(to_sales_report_button);
        popular_trends_panel.add(back_button);
        popular_trends_panel.add(scrollDate2);
        popular_trends_panel.add(addButton);
        popular_trends_panel.add(dateInputField);

        to_sales_report_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedDates.size() == 2) {
                    String date1 = selectedDates.iterator().next();
                    String date2 = selectedDates.toArray()[1].toString();
                    boolean date1Exists = uniqueDates.contains(date1);
                    boolean date2Exists = uniqueDates.contains(date2);
        
                    if (!date1Exists || !date2Exists) {
                        
                        JOptionPane.showMessageDialog(MostPopularTrends.this, "Please select valid dates from the list.");
                        selectedDates.clear();
                    } else if (date1.equals(date2)) {
                       
                        selectedDates.clear();
                        dateInputField.setText(""); // Clear the input field
                        JOptionPane.showMessageDialog(MostPopularTrends.this, "Please select two different dates before entering the sales report.");
                    } else {
                        
                        dispose();
                        new salesReport(selectedDates).setVisible(true); 
                    }
                } else {
                    JOptionPane.showMessageDialog(MostPopularTrends.this, "Please select a valid input for two dates.");
                    selectedDates.clear();
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAddButton();
            }
        });

        selectedDates = new HashSet();

        back_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                database.closeJDBC();
                dispose();
                new OrderHistory().setVisible(true);
            }
        });

        add(popular_trends_panel);
    }

    private void handleAddButton() {
        String selectedDate = dateInputField.getText();
        if (!selectedDate.isEmpty()) {
            selectedDates.add(selectedDate);
            dateInputField.setText("");
        }
    }
    private void addDateToList(String date) {
        if (!selectedDates.contains(date)) {
            selectedDates.add(date);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MostPopularTrends().setVisible(true);
        });
    }
}