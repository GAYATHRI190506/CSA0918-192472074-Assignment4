import javax.swing.*;
import java.awt.*;

public class LibrarySearch extends JFrame {
    private final JTextField searchField = new JTextField(20);
    private final JComboBox<String> category =
            new JComboBox<>(new String[]{"Title", "Author", "Subject"});
    private final JTextArea results = new JTextArea();
    private final JLabel status = new JLabel("Ready");

    public LibrarySearch() {
        super("Campus Library Search");

        JPanel searchRow = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton searchButton = new JButton("Search");
        searchRow.add(new JLabel("Search:"));
        searchRow.add(searchField);
        searchRow.add(category);
        searchRow.add(searchButton);

        JPanel bottom = new JPanel(new BorderLayout());
        JPanel buttonRow = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonRow.add(new JButton("Prev"));
        buttonRow.add(new JButton("Next"));
        buttonRow.add(new JButton("Reserve"));
        bottom.add(buttonRow, BorderLayout.CENTER);
        bottom.add(status, BorderLayout.SOUTH);

        results.setEditable(false);
        results.setText("Search results appear here.");

        add(searchRow, BorderLayout.NORTH);
        add(new JScrollPane(results), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        setSize(700, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySearch().setVisible(true));
    }
}
