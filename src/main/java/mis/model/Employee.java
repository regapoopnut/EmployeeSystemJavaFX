package mis.model;

import java.time.LocalDate;

/**
 * Employee.java - The core business entity of the application.
 *
 * This class is a MODEL which means it purely represents data, it has no UI code in it.
 * It follows the principle of ENCAPSULATION: all fields are private (hidden from
 * outside), and the only way to read them is through public getter methods.
 *
 * This class is also IMMUTABLE which means once an Employee object is created, its values
 * can never be changed. All fields are 'final', meaning they can only be assigned
 * once (in the constructor). This prevents accidental modification of employee data.
 *
 * The 9 attributes cover a variety of Java data types as required by the project:
 *   int        - id
 *   String     - firstName, lastName, email, gender
 *   enum       - department (Department type)
 *   double     - salary
 *   LocalDate  - hireDate
 *   boolean    - fullTime
 */
public class Employee {

    private final int id;                // int        - auto-generated
    private final String firstName;      // String     - TextField
    private final String lastName;       // String     - TextField
    private final String email;          // String     - TextField (validated)
    private final String gender;         // String     - RadioButtons
    private final Department department; // enum       - ComboBox
    private final double salary;         // double     - TextField (parsed)
    private final LocalDate hireDate;    // LocalDate  - DatePicker
    private final boolean fullTime;      // boolean    - CheckBox

    public Employee(int id, String firstName, String lastName, String email, String gender,
                    Department department, double salary, LocalDate hireDate, boolean fullTime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.hireDate = hireDate;
        this.fullTime = fullTime;
    }

    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getFullName() { return firstName + " " + lastName; }
    public String getEmail() { return email; }
    public String getGender() { return gender; }
    public Department getDepartment() { return department; }
    public double getSalary() { return salary; }
    public LocalDate getHireDate() { return hireDate; }
    public boolean isFullTime() { return fullTime; }

    @Override
    public String toString() {
        return "Employee #" + id + " " + getFullName();
    }
}
