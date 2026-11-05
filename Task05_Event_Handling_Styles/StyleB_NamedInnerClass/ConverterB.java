import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterB extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target =
            new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");

    public ConverterB() {
        super("Currency Converter - Named Inner Class");
        setLayout(new FlowLayout());

        JButton button = new JButton("Convert");

        ConvertHandler handler = new ConvertHandler();

        // Same named listener handles both Button click and Enter key
        button.addActionListener(handler);
        amount.addActionListener(handler);

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

            double converted = value * (c.equals("USD") ? 0.012 : 0.011);

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

    // Named Inner Class
    private class ConvertHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            convert();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new ConverterB().setVisible(true)
        );
    }
}