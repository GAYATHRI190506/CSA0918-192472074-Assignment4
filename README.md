# CSA0918-192472074-Assignment4

## Programming in Java – Assignment 4 – CO4
### Event-Driven GUI Design

**Student:** Madaraboina Gayathri  
**Register Number:** 192472074  
**Course:** Programming in Java (CSA09)  
**Java Version:** Java 26.0.1

## Tasks

- Task 1 – GUI Design Review
- Task 2 – From Requirement to Event-Driven Design
- Task 3 – From Sketch to Layout
- Task 4 – Event-Handling Forensics
- Task 5 – Same Screen, Different Event-Handling Styles
- Task 6 – Campus Event Registration Desktop Application

## How to Run

Open PowerShell in the required task folder and compile/run the Java file.

Example:

```powershell
javac FileName.java
java FileName
```

For Task 6, configure the MySQL credentials in `DBConnection.java` before running the application.

Task 6 requires MySQL Server and MySQL Connector/J.

## Evidence

Execution screenshots are stored inside the `screenshots` folder.

The screenshots include evidence for:
- Task 3 GUI layout and resizing
- Task 4 event handling and drawing
- Task 5 event-handling styles and validation
- Task 6 normal registration and invalid input

## Task 6 Database

Database used:

```text
Database: assignment4
Table: registrations
```

The Task 6 application uses JDBC with MySQL and includes:
- Student registration
- Email validation
- Duplicate registration prevention
- Database persistence
- Background database operations using `SwingWorker`
- Success and error dialogs

## GitHub

**Repository:**

`CSA0918-192472074-Assignment4`

Progressive commits were used during development to track the completion of each task.