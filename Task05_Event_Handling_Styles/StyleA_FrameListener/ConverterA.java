import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ConverterA extends JFrame implements ActionListener {

    private final JTextField amount = new JTextField(10);
    private final JComboBox<String> target =
            new JComboBox<>(new String[]{"USD", "EUR"});
    private final JLabel result = new JLabel(" ");

    public ConverterA() {
        super("Currency Converter - Frame Listener");

        setLayout(new FlowLayout());

        JButton button = new JButton("Convert");

        // Convert button
        button.addActionListener(this);

        // Press Enter in amount field
        amount.addActionListener(this);

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

    private static double rate(String c) {
        return c.equals("USD") ? 0.012 : 0.011;
    }

    private void convert() {
        try {
            double value = Double.parseDouble(amount.getText().trim());

            String currency = (String) target.getSelectedItem();
            double converted = value * rate(currency);

            result.setText(String.format("%.2f %s", converted, currency));

            JOptionPane.showMessageDialog(
                    this,
                    String.format("%.2f INR = %.2f %s",
                            value, converted, currency),
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

    @Override
    public void actionPerformed(ActionEvent e) {
        convert();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                () -> new ConverterA().setVisible(true)
        );
    }
}