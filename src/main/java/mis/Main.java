// This file is part of the 'mis' package which is a way of grouping related classes together
// Packages are like folders that organize the project
package mis;

// Importing the Application class from JavaFX which is the base class all JavaFX apps must extend
import javafx.application.Application;
// Stage represents the main window of the application
import javafx.stage.Stage;
// Our own SceneManager class which handles switching between scenes inside the same window
import mis.ui.SceneManager;
// Our own LoginView class which builds and returns the sign in screen
import mis.views.LoginView;

/**
 * Entry point of the application.
 * JavaFX calls start() and hands us the PRIMARY STAGE.
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Give the SceneManager control of the primary stage so any view can swap scenes.
        SceneManager.init(primaryStage);

        // First scene shown inside the primary stage is the sign in form.
        SceneManager.switchTo(new LoginView().getScene(), "Employee Management - Sign In");

        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args); // starts the JavaFX runtime which calls start()
    }
}
