import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class DateSelectionFrame extends JFrame {
    private JTextField startDateField, endDateField;
    
    public DateSelectionFrame() {
        setTitle("Select Date Range");
        setSize(400, 150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        JLabel startLabel = new JLabel("Start Date:");
        startDateField = new JTextField(10);
        JLabel endLabel = new JLabel("End Date:");
        endDateField = new JTextField(10);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String startDate = startDateField.getText();
                String endDate = endDateField.getText();
                new PopularCombination(startDate, endDate).setVisible(true);
                dispose();
            }
        });

        add(startLabel);
        add(startDateField);
        add(endLabel);
        add(endDateField);
        add(submitButton);
    }
}
