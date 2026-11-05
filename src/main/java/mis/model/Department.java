package mis.model;

/**
 * Department.java - Represents the possible departments an employee can belong to.
 *
 * An ENUM (short for enumeration) is a special type in Java used when a variable
 * should only ever be one of a fixed set of values. Instead of using plain Strings
 * like "Engineering" or "Sales" (which could be misspelled or inconsistent),
 * we use an enum so the compiler enforces that only valid departments can be used.
 *
 * This enum is used by:
 *   - Employee.java      --> stores the department as a Department type (not a String)
 *   - EmployeeFormView   --> populates the ComboBox with Department.values()
 *   - EmployeeTableView  --> displays the department in a TableColumn
 */
public enum Department {
    ENGINEERING("Engineering"),
    SALES("Sales"),
    MARKETING("Marketing"),
    HUMAN_RESOURCES("Human Resources"),
    FINANCE("Finance"),
    ACCOUNTING("Accounting"),
    CUSTOMER_SERVICE("Customer Service");

    private final String label;

    Department(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label; // what the ComboBox and TableView display
    }
}
