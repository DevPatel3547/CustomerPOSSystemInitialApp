import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

class MostPopularTrends extends JFrame {
    
    MostPopularTrends() {
        setTitle("Most Popular Trends");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create and customize the manager panel UI here
        JPanel popular_trends_panel = new JPanel();
        JLabel label = new JLabel("Welcome to the Most Popular Trends Panel (in progress)");
        popular_trends_panel.add(label);

        add(popular_trends_panel);
    }
}