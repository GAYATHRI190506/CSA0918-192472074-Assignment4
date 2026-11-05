# Task 1 – GUI Design Review

## 1.1 Diagnosis
1. Interface and business logic are tightly coupled.
2. `==` is incorrectly used for String comparison.
3. Empty/non-numeric input is not validated.
4. Database work is performed on the Event Dispatch Thread.
5. `Thread.sleep(3000)` freezes the GUI.
6. The Receipt button has no separate behavior.
7. SQL is built using string concatenation, creating SQL injection risk.
8. Database resources are not closed safely.
9. Checked exceptions are not handled.
10. User feedback is poor because only console output is used.

## 1.2 Repair Design
Recommended structure:
- `FeeWindow` – collects input and displays results.
- `FeeService` – validates input and performs payment logic.
- Optional DAO/repository – performs JDBC operations.

The event handler should validate basic GUI input, call the service, and update the GUI with success/error feedback. Long-running database work should not block the Event Dispatch Thread.

## 1.3 Design Defence
The redesigned separation makes the GUI easier to test because validation and payment logic can be tested independently. The service can be reused by another interface without changing business rules. Database code is isolated, reducing maintenance effort. The GUI remains focused on collecting input and presenting results, so changes to the payment process do not require rewriting the entire event handler.
