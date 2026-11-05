# Task 2 – Hospital Outpatient Appointment Desk

## 2.1 Event Table

| Event Source | Event Object | Listener | Action |
|---|---|---|---|
| Department ComboBox | ItemEvent | ItemListener | Refresh doctor list |
| Doctor ComboBox | ItemEvent | ItemListener | Update selected doctor |
| Time Slot ComboBox | ItemEvent | ItemListener | Update selected slot |
| Book Button | ActionEvent | ActionListener | Validate and book |
| Close Window | WindowEvent | WindowListener/WindowAdapter | Confirm close |
| Patient ID field | ActionEvent | ActionListener | Optional validation/booking trigger |

## 2.2 Component Hierarchy

JFrame
- JPanel (form)
  - JLabel + JTextField (Patient ID)
  - JLabel + JComboBox (Department)
  - JLabel + JComboBox (Doctor)
  - JLabel + JComboBox (Time Slot)
  - JButton (Book)
- JLabel (Status)

## 2.3 Pseudocode

### Refresh Doctor List
START
READ selected department
GET doctors from Map<Department, List<Doctor>>
CLEAR doctor ComboBox
FOR each doctor in selected list
    ADD doctor to ComboBox
END FOR
END

### Validate and Book
START
READ patient ID, department, doctor and time slot
IF any required field is empty
    SHOW error
    STOP
END IF
VALIDATE patient ID
IF invalid
    SHOW error
    STOP
END IF
BOOK appointment
IF booking succeeds
    SHOW confirmation
ELSE
    SHOW error
END IF
END

## 2.4 Event Trace
Open application → constructor initializes components → frame becomes visible → user changes department → ItemEvent → ItemListener refreshes doctors → user selects slot → user clicks Book → ActionEvent → validation → booking → confirmation → user attempts close → WindowEvent → windowClosing → confirmation dialog → dispose.
## 2.5 Alternative Data Storage Comparison

### Map-Based Approach

A Map can store departments as keys and lists of doctors as values. This approach is simple and fast for a small prototype because data can be accessed directly in memory.

Advantages:
- Simple to implement.
- Fast lookup for small datasets.
- No database connection is required.
- Suitable for demonstrating event-driven GUI concepts.

Limitations:
- Data is lost when the application closes.
- It does not support persistent appointment records.
- It is not suitable for multiple users or large datasets.

### Database-Based Approach

A relational database can store patients, departments, doctors, time slots, and appointments permanently.

Advantages:
- Data remains available after the application closes.
- Supports large datasets.
- Provides persistent appointment records.
- Multiple users can access shared data.
- Prevents duplicate bookings using database constraints.

Limitations:
- Requires database configuration and connectivity.
- More code is required for JDBC operations.
- Database failures must be handled.

### Comparison

For a small demonstration application, a Map is simpler and easier to implement. For a real hospital appointment system, a database is preferable because appointments must be persistent, shared, and reliable. Therefore, the Map approach is suitable for the prototype, while the database approach is better for a production system.