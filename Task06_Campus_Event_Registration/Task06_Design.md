# Task 6 – Campus Event Registration Desktop App

## 6.1 Users

- Student registering for a campus event.
- Committee member viewing registered students.

## 6.2 Functional Rules

- Student ID is mandatory.
- Student name is mandatory.
- Email is mandatory.
- Email format must be validated.
- A student cannot register twice for the same event.
- Invalid input must be clearly reported.
- Registration data must be stored permanently in MySQL.
- Database operations must not block the Swing Event Dispatch Thread.
- JDBC resources must be closed safely.

## 6.3 System Layers

### UI Layer
`EventRegistrationApp`

Responsibilities:
- Collect student information.
- Display available events.
- Validate basic input.
- Display registration results.
- Display existing registrations.

### Data Access Layer
`DBConnection`

Responsibilities:
- Establish the JDBC connection to MySQL.
- Provide a reusable database connection.

### Database Layer

MySQL database:

`assignment4`

Table:

`registrations`

Important fields:

- `student_id`
- `student_name`
- `email`
- `event_name`
- `registered_at`

The composite primary key `(student_id, event_name)` prevents duplicate registration for the same student and event.

## 6.4 Class Diagram

```text
+-----------------------------+
|   EventRegistrationApp      |
+-----------------------------+
| - studentId                 |
| - studentName               |
| - email                     |
| - event                     |
| - table                     |
| - status                    |
+-----------------------------+
| + registerStudent()         |
| + loadAll()                 |
| + main()                    |
+--------------+--------------+
               |
               | JDBC
               v
+-----------------------------+
|       DBConnection          |
+-----------------------------+
| - URL                       |
| - USER                      |
| - PASSWORD                  |
+-----------------------------+
| + getConnection()           |
+--------------+--------------+
               |
               | JDBC
               v
+-----------------------------+
|          MySQL              |
|        assignment4          |
+-----------------------------+
|       registrations         |
+-----------------------------+
```

## 6.5 Registration Pseudocode

```text
START

Read Student ID
Read Student Name
Read Email
Read selected Event

IF Student ID is empty
    Display invalid input
    STOP
END IF

IF Student Name is empty
    Display invalid input
    STOP
END IF

IF Email is empty
    Display invalid input
    STOP
END IF

IF Email format is invalid
    Display invalid email
    STOP
END IF

Start SwingWorker

Connect to MySQL

INSERT student ID, name, email and event

IF insertion succeeds
    Display registration successful
ELSE IF duplicate constraint occurs
    Display duplicate registration error
ELSE
    Display database error
END IF

Update registration table

END
```

## 6.6 Loading Registration Data

```text
START

Start SwingWorker

Connect to MySQL

Execute SELECT query

FOR each returned record
    Add record to table model
END FOR

Display records in JTable

IF database error occurs
    Display load error
END IF

END
```

## 6.7 Event Flow

```text
User enters details
        |
        v
Click Register
        |
        v
Input validation
        |
        v
SwingWorker starts
        |
        v
JDBC connection
       