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
