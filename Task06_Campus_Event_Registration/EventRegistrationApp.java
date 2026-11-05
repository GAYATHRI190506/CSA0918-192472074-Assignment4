import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class EventRegistrationApp extends JFrame {

    private final JTextField studentId = new JTextField(10);
    private final JTextField studentName = new JTextField(12);
    private final JTextField email = new JTextField(15);

    private final JComboBox<String> event =
            new JComboBox<>(new String[]{
                    "Cultural Fest",
                    "Coding Contest",
                    "Sports Meet"
            });

    private final DefaultTableModel model =
            new DefaultTableModel(
                    new String[]{"ID", "Student", "Email", "Event"}, 0);

    private final JTable table = new JTable(model);
    private final JLabel status = new JLabel("Ready");

    public EventRegistrationApp() {

        super("Campus Event Registration");

        // File menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(e -> dispose());

        menu.add(exit);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Form
        JPanel form = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JButton register = new JButton("Register");
        JButton refresh = new JButton("Refresh");

        form.add(new JLabel("Student ID:"));
        form.add(studentId);

        form.add(new JLabel("Name:"));
        form.add(studentName);

        form.add(new JLabel("Email:"));
        form.add(email);

        form.add(new JLabel("Event:"));
        form.add(event);

        form.add(register);
        form.add(refresh);

        register.addActionListener(e -> registerStudent());
        refresh.addActionListener(e -> loadAll());

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        setSize(1050, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Load existing registrations
        loadAll();
    }

    private void registerStudent() {

        String id = studentId.getText().trim();
        String name = studentName.getText().trim();
        String emailValue = email.getText().trim();
        String selectedEvent = (String) event.getSelectedItem();

        // Validation
        if (id.isEmpty() || name.isEmpty() || emailValue.isEmpty()) {
            status.setText(
                    "Invalid input: Student ID, Name and Email are required."
            );
            JOptionPane.showMessageDialog(
                    this,
                    "Student ID, Name and Email are required.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Basic email validation
        if (!emailValue.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            status.setText("Invalid email address.");
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid email address.",
                    "Invalid Email",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        registerStudentInDatabase(id, name, emailValue, selectedEvent);
    }

    private void registerStudentInDatabase(
            String id,
            String name,
            String emailValue,
            String selectedEvent) {

        new SwingWorker<Boolean, Void>() {

            String message = "";

            @Override
            protected Boolean doInBackground() {

                String sql =
                        "INSERT INTO registrations " +
                        "(student_id, student_name, email, event_name) " +
                        "VALUES (?, ?, ?, ?)";

                try (
                        Connection c = DBConnection.getConnection();
                        PreparedStatement ps = c.prepareStatement(sql)
                ) {

                    ps.setString(1, id);
                    ps.setString(2, name);
                    ps.setString(3, emailValue);
                    ps.setString(4, selectedEvent);

                    ps.executeUpdate();

                    message = "Registration successful.";
                    return true;

                } catch (SQLIntegrityConstraintViolationException ex) {

                    message =
                            "Duplicate registration: this student is already registered for the event.";
                    return false;

                } catch (SQLException ex) {

                    message = "Database error: " + ex.getMessage();
                    return false;
                }
            }

            @Override
            protected void done() {

                status.setText(message);

                if (message.equals("Registration successful.")) {

                    JOptionPane.showMessageDialog(
                            EventRegistrationApp.this,
                            message,
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    studentId.setText("");
                    studentName.setText("");
                    email.setText("");

                } else {

                    JOptionPane.showMessageDialog(
                            EventRegistrationApp.this,
                            message,
                            "Registration Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                }

                loadAll();
            }

        }.execute();
    }

    private void loadAll() {

        new SwingWorker<Vector<Vector<Object>>, Void>() {

            String errorMessage = null;

            @Override
            protected Vector<Vector<Object>> doInBackground() {

                Vector<Vector<Object>> rows = new Vector<>();

                String sql =
                        "SELECT student_id, student_name, email, event_name " +
                        "FROM registrations ORDER BY student_id";

                try (
                        Connection c = DBConnection.getConnection();
                        Statement st = c.createStatement();
                        ResultSet rs = st.executeQuery(sql)
                ) {

                    while (rs.next()) {

                        Vector<Object> row = new Vector<>();

                        row.add(rs.getString("student_id"));
                        row.add(rs.getString("student_name"));
                        row.add(rs.getString("email"));
                        row.add(rs.getString("event_name"));

                        rows.add(row);
                    }

                } catch (SQLException ex) {

                    errorMessage = "Load error: " + ex.getMessage();
                }

                return rows;
            }

            @Override
            protected void done() {

                try {

                    model.setRowCount(0);

                    for (Vector<Object> row : get()) {
                        model.addRow(row);
                    }

                    if (errorMessage != null) {
                        status.setText(errorMessage);
                    } else {
                        status.setText(
                                "Registrations loaded successfully."
                        );
                    }

                } catch (Exception ex) {

                    status.setText(
                            "Unable to display registrations."
                    );
                }
            }

        }.execute();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(
                () -> new EventRegistrationApp().setVisible(true)
        );
    }
}