package mis.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import mis.data.EmployeeRepository;
import mis.model.Department;
import mis.model.Employee;
import mis.ui.SceneManager;
import mis.ui.Styles;

import java.time.LocalDate;

/**
 * SCENE 3 - Form that collects the Employee data (9 attributes, several control types)
 * and submits it to the repository.
 */
public class EmployeeFormView {

    // --- Input controls (one per attribute) ---
    private final TextField firstNameField = new TextField();
    private final TextField lastNameField = new TextField();
    private final TextField emailField = new TextField();
    private final RadioButton maleRadio = new RadioButton("Male");
    private final RadioButton femaleRadio = new RadioButton("Female");
    private final ComboBox<Department> departmentBox = new ComboBox<>();
    private final TextField salaryField = new TextField();
    private final DatePicker hireDatePicker = new DatePicker();
    private final CheckBox fullTimeCheck = new CheckBox("Full-time employee");
    private final Label errorLabel = new Label();

    public Scene getScene() {
        Label title = new Label("Add New Employee");
        title.setStyle(Styles.TITLE);

        // Configure controls
        firstNameField.setPromptText("e.g. Sara");
        lastNameField.setPromptText("e.g. Nassar");
        emailField.setPromptText("name@company.com");
        salaryField.setPromptText("e.g. 3500");
        departmentBox.getItems().addAll(Department.values());
        departmentBox.setPromptText("Select department");
        hireDatePicker.setValue(LocalDate.now());

        ToggleGroup genderGroup = new ToggleGroup(); // only one radio can be selected
        maleRadio.setToggleGroup(genderGroup);
        femaleRadio.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(15, maleRadio, femaleRadio);

        // Layout: GridPane (label column | input column)
        GridPane grid = new GridPane();
        grid.setHgap(12);
        grid.setVgap(12);
        int row = 0;
        grid.addRow(row++, new Label("First Name:"), firstNameField);
        grid.addRow(row++, new Label("Last Name:"), lastNameField);
        grid.addRow(row++, new Label("Email:"), emailField);
        grid.addRow(row++, new Label("Gender:"), genderBox);
        grid.addRow(row++, new Label("Department:"), departmentBox);
        grid.addRow(row++, new Label("Salary ($):"), salaryField);
        grid.addRow(row++, new Label("Hire Date:"), hireDatePicker);
        grid.addRow(row++, new Label("Contract:"), fullTimeCheck);

        errorLabel.setStyle(Styles.ERROR_TEXT);
        errorLabel.setWrapText(true);
        errorLabel.setMaxWidth(380);

        // Buttons
        Button submitBtn = new Button("Submit");
        submitBtn.setStyle(Styles.PRIMARY_BUTTON);
        submitBtn.setDefaultButton(true);
        submitBtn.setOnAction(e -> handleSubmit());

        Button clearBtn = new Button("Clear");
        clearBtn.setStyle(Styles.SECONDARY_BUTTON);
        clearBtn.setOnAction(e -> clearForm());

        Button viewBtn = new Button("View Table");
        viewBtn.setStyle(Styles.SECONDARY_BUTTON);
        viewBtn.setOnAction(e -> new EmployeeTableView().show());

        Button backBtn = new Button("Back");
        backBtn.setStyle(Styles.SECONDARY_BUTTON);
        backBtn.setOnAction(e ->
                SceneManager.switchTo(new DashboardView().getScene(), "Employee MIS - Dashboard"));

        HBox buttons = new HBox(10, submitBtn, clearBtn, viewBtn, backBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox card = new VBox(16, title, grid, errorLabel, buttons);
        card.setPadding(new Insets(30));
        card.setStyle(Styles.CARD);
        card.setAlignment(Pos.CENTER);

        VBox root = new VBox(card);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));
        root.setStyle(Styles.ROOT);

        return new Scene(root, 560, 560);
    }

    /** Validates the inputs, then saves a new Employee to the repository. */
    private void handleSubmit() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String salaryText = salaryField.getText().trim();
        Department department = departmentBox.getValue();
        LocalDate hireDate = hireDatePicker.getValue();

        // ---- Validation ----
        if (firstName.isEmpty() || lastName.isEmpty()) {
            showError("First name and last name are required.");
            return;
        }
        if (!email.matches("^[\\w.+-]+@[\\w-]+\\.[\\w.]+$")) {
            showError("Please enter a valid email address.");
            return;
        }
        if (!maleRadio.isSelected() && !femaleRadio.isSelected()) {
            showError("Please select a gender.");
            return;
        }
        if (department == null) {
            showError("Please select a department.");
            return;
        }
        double salary;
        try {
            salary = Double.parseDouble(salaryText);   // String -> double
            if (salary <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            showError("Salary must be a positive number.");
            return;
        }
        if (hireDate == null || hireDate.isAfter(LocalDate.now())) {
            showError("Hire date is required and cannot be in the future.");
            return;
        }

        String gender = maleRadio.isSelected() ? "Male" : "Female";
        boolean fullTime = fullTimeCheck.isSelected();

        // ---- Save ----
        Employee saved = EmployeeRepository.getInstance()
                .add(firstName, lastName, email, gender, department, salary, hireDate, fullTime);

        errorLabel.setText("");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);   // Alert = a small extra stage
        alert.setTitle("Success");
        alert.setHeaderText("Employee saved");
        alert.setContentText(saved + " was added with ID " + saved.getId() + ".");
        alert.showAndWait();

        clearForm();
    }

    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        emailField.clear();
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        departmentBox.setValue(null);
        salaryField.clear();
        hireDatePicker.setValue(LocalDate.now());
        fullTimeCheck.setSelected(false);
        errorLabel.setText("");
        firstNameField.requestFocus();
    }

    private void showError(String message) {
        errorLabel.setText(message);
    }
}
