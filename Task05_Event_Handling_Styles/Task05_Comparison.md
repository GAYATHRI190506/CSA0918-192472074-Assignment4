# Task 5 – Comparison

## 5.1 Comparison of Event-Handling Styles

| Style | Readability | Reuse | Access to Fields | Change Effort |
|---|---|---|---|---|
| Frame implements ActionListener | Simple for tiny programs | Low | Direct | Moderate |
| Named inner class | Good | Good | Direct | Low |
| Anonymous inner class | Good for one-off handlers | Low | Direct | Low |
| Lambda | Very concise | Moderate | Direct | Very Low |

For a large application, a named handler or shared Action object is usually preferable because responsibilities can be separated and reused across multiple controls.

## 5.2 Change Effort – Enter Key and Dialog

All four implementations were modified so that the user can press **Enter** in the amount field to perform the conversion. The conversion result is also displayed using a dialog box.

### Frame implements ActionListener
The frame itself implements `ActionListener`. The same `this` listener is registered with both the Convert button and the amount field.

**Change effort:** Moderate.

### Named Inner Class
The existing named `ConvertHandler` object is registered with both the Convert button and the amount field.

**Change effort:** Low.

### Anonymous Inner Class
The existing anonymous `ActionListener` object is registered with both the Convert button and the amount field.

**Change effort:** Low.

### Lambda
A single lambda listener is created and registered with both controls:

```java
ActionListener convertAction = e -> convert();

button.addActionListener(convertAction);
amount.addActionListener(convertAction);
```

**Change effort:** Very Low.

The lambda version requires the least code while still allowing both the button and Enter key to use the same conversion action.

## 5.3 Error Handling

The converter validates the amount entered by the user.

### Valid Input
Example:

```text
Input: 100
Target: USD
Output: 100.00 INR = 1.20 USD
```

The result is displayed in a dialog box.

### Invalid Input
Example:

```text
Input: abc
Output: Invalid amount
```

An error dialog is displayed instead of allowing the program to terminate with an exception.

## 5.4 Alternative Concept – Method Reference

An alternative approach is to use a method reference such as:

```java
this::convert
```

where the method is compatible with the `ActionListener` functional interface.

Another alternative is to create one shared `Action` object and connect it to multiple controls such as:

- Convert button
- Menu item
- Keyboard shortcut

### Comparison

Using one shared listener or `Action` reduces duplicated event-handling logic. It also makes it easier to add a new trigger because the same action can be reused.

A lambda is the most concise option for a small converter. For a larger application, a named handler or shared `Action` provides better organization and reuse.

## 5.5 Final Conclusion

The four approaches all successfully implement event-driven currency conversion.

- **Frame implements ActionListener:** simple but tightly couples the frame and event handling.
- **Named Inner Class:** provides good structure and reuse.
- **Anonymous Inner Class:** useful for a one-time event handler.
- **Lambda:** shortest and cleanest for simple actions.

For a small application, the **lambda approach** is concise and easy to maintain. For a large application with many controls and events, a **named handler or shared Action** is preferable because it provides better separation, reuse, and maintainability.