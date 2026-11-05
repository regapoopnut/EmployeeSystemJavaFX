package mis.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mis.model.Department;
import mis.model.Employee;

import java.time.LocalDate;

/**
 * EmployeeRepository.java  The in memory database for the application.
 *
 * This class uses the SINGLETON design pattern, so that only one instance of this class can exist.
 * This is important because every part of the app (the form, the table, the dashboard) must share 
 * the exact same list of employees. If each view created its own repository, they would each 
 * have separate empty lists and nothing would work together.
 */
public class EmployeeRepository {

    private static final EmployeeRepository INSTANCE = new EmployeeRepository();

    // The list that holds all Employee objects.
    // ObservableList is special because when you add or remove an employee, it automatically
    // fires an event that tells the TableView to update itself on screen.
    // FXCollections.observableArrayList() creates an empty observable list for us.
    private final ObservableList<Employee> employees = FXCollections.observableArrayList();
    private int nextId = 1;

    private EmployeeRepository() {
        // Seed data so the table is not empty during the demo.
        add("Lina", "Haddad", "lina.haddad@company.com", "Female",
                Department.ENGINEERING, 4200, LocalDate.of(2022, 3, 14), true);
        add("Omar", "Khalil", "omar.khalil@company.com", "Male",
                Department.SALES, 2800, LocalDate.of(2023, 9, 1), false);
        add("Salem", "Kasem", "salem.kasem@company.com", "Male",
                Department.CUSTOMER_SERVICE, 1300, LocalDate.of(2024, 2, 23), true);
        add("Amina", "Rashid", "amina.rashid@company.com", "Female",
                Department.ACCOUNTING, 3200, LocalDate.of(2023, 6, 15), true);
    }

    public static EmployeeRepository getInstance() {
        return INSTANCE;
    }

    /** Creates the Employee with an auto-generated id and stores it. */
    public Employee add(String firstName, String lastName, String email, String gender,
                        Department department, double salary, LocalDate hireDate, boolean fullTime) {
        Employee e = new Employee(nextId++, firstName, lastName, email, gender,
                department, salary, hireDate, fullTime);
        employees.add(e);
        return e;
    }

    public void remove(Employee e) {
        employees.remove(e);
    }

    public ObservableList<Employee> getAll() {
        return employees;
    }

    public int count() {
        return employees.size();
    }
}
