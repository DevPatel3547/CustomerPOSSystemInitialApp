import javax.swing.*;
import java.awt.*;
import java.sql.*;

class PopularCombination extends JFrame {
    private Database database;
    private JTable table;

    public PopularCombination() {
        setTitle("Popular Combinations");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        database = new Database();
        database.openJDBC();

        String[] columnNames = {"Flavor", "Topping", "Frequency"};
        Object[][] data = fetchDataFromDatabase();
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

    private Object[][] fetchDataFromDatabase() {
        try {
            String sql = "WITH ToppingsExploded AS (\r\n" + //
                    "    -- Exploding the toppings into individual rows\r\n" + //
                    "    SELECT date, flavor, UNNEST(STRING_TO_ARRAY(toppings, ', ')) AS topping\r\n" + //
                    "    FROM orderhistory\r\n" + //
                    "    WHERE date BETWEEN '2022-10-05' AND '2022-10-06'  -- Change dates as needed\r\n" + //
                    ")\r\n" + //
                    "\r\n" + //
                    "SELECT\r\n" + //
                    "    flavor,\r\n" + //
                    "    topping,\r\n" + //
                    "    COUNT(*) AS frequency\r\n" + //
                    "FROM ToppingsExploded\r\n" + //
                    "WHERE topping <> ''  -- filtering out empty toppings\r\n" + //
                    "GROUP BY flavor, topping\r\n" + //
                    "ORDER BY frequency DESC;\r\n" + //
                    "";;  // Your SQL query from earlier goes here
            ResultSet resultSet = database.getData(sql);

            // Convert ResultSet to Object[][]
            // For simplicity, I'm assuming a fixed size of results.
            // You might want to use an ArrayList if you're uncertain of the number of rows and then convert to an array.
            Object[][] data = new Object[10][3]; // adjust the size accordingly

            int index = 0;
            while (resultSet.next() && index < 10) {
                data[index][0] = resultSet.getString("flavor");
                data[index][1] = resultSet.getString("topping");
                data[index][2] = resultSet.getInt("frequency");
                index++;
            }

            return data;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Object[][]{}; // return empty array if there's an issue
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PopularCombination().setVisible(true);
        });
    }
}
