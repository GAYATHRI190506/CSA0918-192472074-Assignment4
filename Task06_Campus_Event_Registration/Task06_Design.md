# Task 6 – Design

## Users
- Student registering for an event.
- Committee member viewing registrations.

## Rules
- Student ID and name are mandatory.
- A student cannot register twice for the same event.
- Invalid input must be clearly reported.
- Database work must not block the Swing Event Dispatch Thread.

## Layers
- UI: `EventRegistrationApp`
- Service/validation: can be extracted from the UI for further refinement
- Data access: JDBC through `DBConnection`
- Database: MySQL `registrations` table

## Key test cases
1. Valid registration → success.
2. Empty ID/name → validation error.
3. Same student + same event → duplicate error.
4. Database unavailable → database error without freezing the GUI.
