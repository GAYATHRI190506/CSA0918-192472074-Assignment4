import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterC extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target =
            new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");

    public ConverterC() {
        super("Currency Converter - Anonymous Inner Class");
        setLayout(new FlowLayout());

        JButton button = new JButton("Convert");

        // Anonymous Inner Class
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        };

        // Same listener handles button click and Enter key
        button.addActionListener(listener);
        amount.addActionListener(listener);

        add(new JLabel("INR"));
        add(amount);
        add(target);
        add(button);
        add(result);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(amount.getText().trim());
            String c = (String) target.getSelectedItem();

            double converted =
                    value * (c.equals("USD") ? 0.012 : 0.011);

            String message = String.format(
                    "%.2f INR = %.2f %s",
                    value, converted, c
            );

            result.setText(message);

            JOptionPane.showMessageDialog(
                    this,
                    message,
                    "Conversion Result",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException ex) {

            result.setText("Invalid amount");

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid amount",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new ConverterC().setVisible(true)
        );
    }
}