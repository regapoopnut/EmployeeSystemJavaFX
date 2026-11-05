package mis.views;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mis.data.EmployeeRepository;
import mis.model.Department;
import mis.model.Employee;
import mis.ui.Styles;

import java.time.LocalDate;

/**
 * SECOND STAGE - A separate window containing a TableView of all employees.
 * The table is bound to the repository's ObservableList, so new employees
 * added in the form appear here instantly without any refresh code.
 */
public class EmployeeTableView {

    private final Stage stage = new Stage();   // <-- a brand-new window (multiple stages)
    private final TableView<Employee> table = new TableView<>();

    public void show() {
        Label title = new Label("All Employees");
        title.setStyle(Styles.TITLE);

        Label countLabel = new Label();
        countLabel.setStyle(Styles.SUBTITLE);

        // ----- Columns -----
        TableColumn<Employee, Number> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getId()));
        idCol.setPrefWidth(50);

        TableColumn<Employee, String> firstCol = new TableColumn<>("First Name");
        firstCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getFirstName()));

        TableColumn<Employee, String> lastCol = new TableColumn<>("Last Name");
        lastCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getLastName()));

        TableColumn<Employee, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getEmail()));
        emailCol.setPrefWidth(190);

        TableColumn<Employee, String> genderCol = new TableColumn<>("Gender");
        genderCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getGender()));

        TableColumn<Employee, Department> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getDepartment()));
        deptCol.setPrefWidth(120);

        TableColumn<Employee, Number> salaryCol = new TableColumn<>("Salary ($)");
        salaryCol.setCellValueFactory(c -> new SimpleDoubleProperty(c.getValue().getSalary()));

        TableColumn<Employee, LocalDate> dateCol = new TableColumn<>("Hire Date");
        dateCol.setCellValueFactory(c -> new SimpleObjectProperty<>(c.getValue().getHireDate()));

        TableColumn<Employee, String> typeCol = new TableColumn<>("Contract");
        typeCol.setCellValueFactory(c ->
                new SimpleStringProperty(c.getValue().isFullTime() ? "Full-time" : "Part-time"));

        table.getColumns().addAll(idCol, firstCol, lastCol, emailCol, genderCol,
                deptCol, salaryCol, dateCol, typeCol);

        // ----- Data binding -----
        table.setItems(EmployeeRepository.getInstance().getAll()); // live ObservableList
        table.setPlaceholder(new Label("No employees yet. Add one from the form."));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY_FLEX_LAST_COLUMN);

        // Keep the counter in sync whenever the list changes
        countLabel.setText("Total: " + table.getItems().size());
        table.getItems().addListener((javafx.collections.ListChangeListener<Employee>) change ->
                countLabel.setText("Total: " + table.getItems().size()));

        // ----- Buttons -----
        Button deleteBtn = new Button("Delete Selected");
        deleteBtn.setStyle(Styles.DANGER_BUTTON);
        deleteBtn.setOnAction(e -> deleteSelected());

        Button closeBtn = new Button("Close Window");
        closeBtn.setStyle(Styles.SECONDARY_BUTTON);
        closeBtn.setOnAction(e -> stage.close());

        HBox buttons = new HBox(10, deleteBtn, closeBtn);
        buttons.setAlignment(Pos.CENTER_RIGHT);

        VBox root = new VBox(12, title, countLabel, table, buttons);
        root.setPadding(new Insets(20));
        root.setStyle(Styles.ROOT);
        VBox.setVgrow(table, javafx.scene.layout.Priority.ALWAYS);

        stage.setScene(new Scene(root, 960, 480));
        stage.setTitle("Employee MIS - Employee Table");
        stage.show();
    }

    private void deleteSelected() {
        Employee selected = table.getSelectionModel().getSelectedItem();
        if (selected == null) {
            new Alert(Alert.AlertType.WARNING, "Please select a row first.").showAndWait();
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete " + selected.getFullName() + "?", ButtonType.YES, ButtonType.NO);
        confirm.setHeaderText(null);
        confirm.showAndWait().ifPresent(btn -> {
            if (btn == ButtonType.YES) {
                EmployeeRepository.getInstance().remove(selected);
            }
        });
    }
}
