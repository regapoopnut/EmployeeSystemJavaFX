package mis.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * switchTo() - Replaces the content of the main window with a new scene.
 * Central place that handles SWITCHING SCENES WITHIN THE SAME STAGE.
 * All views call SceneManager.switchTo(...) instead of touching the Stage directly.
 */
public class SceneManager {

    private static Stage primaryStage;

    private SceneManager() {} // utility class, never instantiated

    public static void init(Stage stage) {
        primaryStage = stage;
    }

      /**
     * switchTo() - Replaces the content of the main window with a new scene.
     *
     * This is the core method of this class. It is called by every view whenever
     * the user navigates to a different screen. For example:
     *   - LoginView calls it to go to the Dashboard after a successful login
     *   - DashboardView calls it to go to the form when "Add Employee" is clicked
     *   - Any view calls it to go back to the Dashboard
     *
     * This does NOT create a new window. It replaces the content
     * inside the SAME window. The Stage stays open the whole time only
     * the Scene (what's shown inside it) changes.
     *
     * @param scene - the new Scene to display (built by whichever view is being switched to)
     * @param title - the text to show in the window's title bar
     */
    public static void switchTo(Scene scene, String title) {
        primaryStage.setScene(scene);   // <-- this is the scene switch
        primaryStage.setTitle(title);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }
}
