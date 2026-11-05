# Task 5 – Comparison

| Style | Readability | Reuse | Access to fields | Change effort |
|---|---|---|---|---|
| Frame implements ActionListener | Simple for tiny programs | Low | Direct | Moderate |
| Named inner class | Good | Good | Direct | Low |
| Anonymous inner class | Good for one-off handlers | Low | Direct | Low |
| Lambda | Very concise | Moderate | Direct | Very low |

For a large application, a named handler or shared Action object is usually preferable because responsibilities can be separated and reused across multiple controls.
