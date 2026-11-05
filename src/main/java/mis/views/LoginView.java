package mis.views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import mis.service.AuthService;
import mis.ui.SceneManager;
import mis.ui.Styles;

/**
 * SCENE 1 - Sign-in form.
 * Built completely in code: no FXML, no Scene Builder.
 */
public class LoginView {

    private final TextField usernameField = new TextField();
    private final PasswordField passwordField = new PasswordField();
    private final Label errorLabel = new Label();

    public Scene getScene() {
        // ----- Header -----
        Label title = new Label("Employee Management");
        title.setStyle(Styles.TITLE);
        Label subtitle = new Label("Sign in to continue");
        subtitle.setStyle(Styles.SUBTITLE);

        // ----- Form (GridPane = rows & columns) -----
        usernameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(12);
        form.add(new Label("Username:"), 0, 0);
        form.add(usernameField, 1, 0);
        form.add(new Label("Password:"), 0, 1);
        form.add(passwordField, 1, 1);

        errorLabel.setStyle(Styles.ERROR_TEXT);

        // ----- Buttons -----
        Button loginBtn = new Button("Sign In");
        loginBtn.setStyle(Styles.PRIMARY_BUTTON);
        loginBtn.setMaxWidth(Double.MAX_VALUE);
        loginBtn.setDefaultButton(true);             // pressing Enter triggers it
        loginBtn.setOnAction(e -> handleLogin());    // event handler (lambda)

        Button exitBtn = new Button("Exit");
        exitBtn.setStyle(Styles.SECONDARY_BUTTON);
        exitBtn.setMaxWidth(Double.MAX_VALUE);
        exitBtn.setOnAction(e -> SceneManager.getPrimaryStage().close());

        Label hint = new Label("Demo accounts:  admin / admin123   |   user / 1234");
        hint.setStyle(Styles.SUBTITLE);

        // ----- Card layout -----
        VBox card = new VBox(14, title, subtitle, form, errorLabel, loginBtn, exitBtn, hint);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.setStyle(Styles.CARD);
        card.setMaxWidth(360);

        StackPane root = new StackPane(card);
        root.setPadding(new Insets(40));
        root.setStyle(Styles.ROOT);

        return new Scene(root, 480, 420);
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            errorLabel.setText("Please enter both username and password.");
            return;
        }

        if (AuthService.login(username, password)) {
            // Switch scene inside the SAME stage -> Dashboard
            SceneManager.switchTo(new DashboardView().getScene(), "Employee MIS - Dashboard");
        } else {
            errorLabel.setText("Invalid username or password.");
            passwordField.clear();
        }
    }
}
