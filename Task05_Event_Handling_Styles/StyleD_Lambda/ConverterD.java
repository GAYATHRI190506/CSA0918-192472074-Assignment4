import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterD extends JFrame {
    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target = new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");

    public ConverterD() {
        super("Currency Converter - Lambda");
        setLayout(new FlowLayout());
        JButton convertButton = new JButton("Convert");
        ActionListener listener = e -> convert();
        convertButton.addActionListener(listener);
        amount.addActionListener(listener);
        add(new JLabel("INR")); add(amount); add(target); add(convertButton); add(result);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack(); setLocationRelativeTo(null);
    }

    private void convert() {
        try {
            double value = Double.parseDouble(amount.getText().trim());
            String c = (String) target.getSelectedItem();
            result.setText(String.format("%.2f %s", value * (c.equals("USD") ? 0.012 : 0.011), c));
        } catch (NumberFormatException ex) {
            result.setText("Invalid amount");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConverterD().setVisible(true));
    }
}
