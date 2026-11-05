import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class EventRegistrationApp extends JFrame {
    private final JTextField studentId = new JTextField(12);
    private final JTextField studentName = new JTextField(15);
    private final JComboBox<String> event = new JComboBox<>(new String[]{"Cultural Fest", "Coding Contest", "Sports Meet"});
    private final DefaultTableModel model = new DefaultTableModel(
            new String[]{"ID", "Student", "Event"}, 0);
    private final JTable table = new JTable(model);
    private final JLabel status = new JLabel("Ready");

    public EventRegistrationApp() {
        super("Campus Event Registration");

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());
        menu.add(exit); menuBar.add(menu); setJMenuBar(menuBar);

        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton register = new JButton("Register");
        JButton refresh = new JButton("Refresh");
        form.add(new JLabel("Student ID:")); form.add(studentId);
        form.add(new JLabel("Name:")); form.add(studentName);
        form.add(new JLabel("Event:")); form.add(event);
        form.add(register); form.add(refresh);

        register.addActionListener(e -> registerStudent());
        refresh.addActionListener(e -> loadAll());

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        setSize(850, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void registerStudent() {
        String id = studentId.getText().trim();
        String name = studentName.getText().trim();
        String selectedEvent = (String) event.getSelectedItem();

        if (id.isEmpty() || name.isEmpty()) {
            status.setText("Invalid input: Student ID and Name are required.");
            return;
        }

        new SwingWorker<Boolean, Void>() {
            String message = "";
            @Override protected Boolean doInBackground() {
                String sql = "INSERT INTO registrations(student_id, student_name, event_name) VALUES (?, ?, ?)";
                try (Connection c = DBConnection.getConnection();
                     PreparedStatement ps = c.prepareStatement(sql)) {
                    ps.setString(1, id);
                    ps.setString(2, name);
                    ps.setString(3, selectedEvent);
                    ps.executeUpdate();
                    message = "Registration successful.";
                    return true;
                } catch (SQLIntegrityConstraintViolationException ex) {
                    message = "Duplicate registration: this student is already registered for the event.";
                    return false;
                } catch (SQLException ex) {
                    message = "Database error: " + ex.getMessage();
                    return false;
                }
            }
            @Override protected void done() {
                try {
                    get();
                } catch (Exception ignored) {}
                status.setText(message);
                loadAll();
            }
        }.execute();
    }

    private void loadAll() {
        new SwingWorker<Vector<Vector<Object>>, Void>() {
            @Override protected Vector<Vector<Object>> doInBackground() {
                Vector<Vector<Object>> rows = new Vector<>();
                String sql = "SELECT student_id, student_name, event_name FROM registrations ORDER BY student_id";
                try (Connection c = DBConnection.getConnection();
                     Statement st = c.createStatement();
                     ResultSet rs = st.executeQuery(sql)) {
                    while (rs.next()) {
                        Vector<Object> row = new Vector<>();
                        row.add(rs.getString(1));
                        row.add(rs.getString(2));
                        row.add(rs.getString(3));
                        rows.add(row);
                    }
                } catch (SQLException ex) {
                    status.setText("Load error: " + ex.getMessage());
                }
                return rows;
            }
            @Override protected void done() {
                try {
                    model.setRowCount(0);
                    for (Vector<Object> row : get()) model.addRow(row);
                } catch (Exception ex) {
                    status.setText("Unable to display registrations.");
                }
            }
        }.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EventRegistrationApp().setVisible(true));
    }
}
