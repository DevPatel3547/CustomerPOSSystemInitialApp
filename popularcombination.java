import javax.swing.*;
import java.awt.*;
import java.sql.*;

class PopularCombination extends JFrame {
    private Database database;
    private JTable table;

    public PopularCombination(String startDate, String endDate) {
        setTitle("Popular Combinations");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        database = new Database();
        database.openJDBC();

        String[] columnNames = {"Flavor 1 & Topping 1", "Flavor 2 & Topping 2", "Quantity"};
        Object[][] data = fetchDataFromDatabase(startDate, endDate);
        table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        JButton back_button = new JButton("Back");
        back_button.addActionListener(
            e -> {
                database.closeJDBC();
                dispose();
                new OrderHistory().setVisible(true); // assuming OrderHistory is another JFrame or JDialog
            }
        );

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(back_button, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private Object[][] fetchDataFromDatabase(String startDate, String endDate) {
        try {
            String sql = 
                "WITH ToppingsExploded AS (" +
                "    -- Exploding the toppings into individual rows" +
                "    SELECT date, flavor, UNNEST(STRING_TO_ARRAY(toppings, ', ')) AS topping" +
                "    FROM orderhistory" +
                "    WHERE date BETWEEN '" + startDate + "' AND '" + endDate + "'" +
                ")" +
                "SELECT" +
                "    flavor," +
                "    topping," +
                "    COUNT(*) AS frequency" +
                "FROM ToppingsExploded" +
                "WHERE topping <> ''  -- filtering out empty toppings" +
                "GROUP BY flavor, topping" +
                "ORDER BY frequency DESC LIMIT 2;";



            ResultSet resultSet = database.getData(sql);

            if (resultSet == null) {
                return new Object[][]{}; // Return empty array if there's an issue
            }

            Object[][] data = new Object[10][3]; // Adjusted size for 3 columns

            int index = 0;
            while (resultSet.next() && index < 10) {
                data[index][0] = resultSet.getString("flavor1") + " & " + resultSet.getString("topping1");
                data[index][1] = resultSet.getString("flavor2") + " & " + resultSet.getString("topping2");
                data[index][2] = resultSet.getInt("quantity"); 
                index++;
            }

            return data;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Object[][]{}; // Return empty array if there's an issue
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                new PopularCombination("2022-10-05", "2022-10-06").setVisible(true);  // Example dates
            });
    }
}
