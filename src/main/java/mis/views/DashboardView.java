package mis.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import mis.data.EmployeeRepository;
import mis.service.AuthService;
import mis.ui.SceneManager;
import mis.ui.Styles;

/**
 * SCENE 2 - Dashboard (main menu) shown in the primary stage after sign-in.
 * From here the user can:
 *   - switch to the Add Employee scene (same stage)
 *   - open the employee table in a NEW stage (second window)
 *   - log out (switch back to the login scene)
 */
public class DashboardView {

    public Scene getScene() {
        Label title = new Label("Dashboard");
        title.setStyle(Styles.TITLE);

        Label welcome = new Label("Welcome, " + AuthService.getCurrentUser() + "!");
        welcome.setStyle(Styles.SUBTITLE);

        Label stats = new Label("Employees in system: " + EmployeeRepository.getInstance().count());
        stats.setStyle(Styles.SUBTITLE);

        Button addBtn = new Button("Add New Employee");
        addBtn.setStyle(Styles.PRIMARY_BUTTON);
        addBtn.setMaxWidth(Double.MAX_VALUE);
        addBtn.setOnAction(e ->
                SceneManager.switchTo(new EmployeeFormView().getScene(), "Employee MIS - Add Employee"));

        Button viewBtn = new Button("View All Employees");
        viewBtn.setStyle(Styles.PRIMARY_BUTTON);
        viewBtn.setMaxWidth(Double.MAX_VALUE);
        viewBtn.setOnAction(e -> new EmployeeTableView().show());   // opens a SECOND stage

        Button logoutBtn = new Button("Log Out");
        logoutBtn.setStyle(Styles.SECONDARY_BUTTON);
        logoutBtn.setMaxWidth(Double.MAX_VALUE);
        logoutBtn.setOnAction(e -> {
            AuthService.logout();
            SceneManager.switchTo(new LoginView().getScene(), "Employee MIS - Sign In");
        });

        Button exitBtn = new Button("Exit");
        exitBtn.setStyle(Styles.DANGER_BUTTON);
        exitBtn.setMaxWidth(Double.MAX_VALUE);
        exitBtn.setOnAction(e -> javafx.application.Platform.exit());

        VBox card = new VBox(12, title, welcome, stats, addBtn, viewBtn, logoutBtn, exitBtn);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.setStyle(Styles.CARD);
        card.setMaxWidth(340);

        VBox root = new VBox(card);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle(Styles.ROOT);

        return new Scene(root, 480, 460);
    }
}
