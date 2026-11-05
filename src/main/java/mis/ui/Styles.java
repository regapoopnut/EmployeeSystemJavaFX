package mis.ui;
 
public class Styles {
 
    private Styles() {}
 
    public static final String ROOT =
            "-fx-background-color: #667eea; -fx-font-family: 'Segoe UI', sans-serif;";
 
    public static final String TITLE =
            "-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #ffffff;";
 
    public static final String SUBTITLE =
            "-fx-font-size: 13px; -fx-text-fill: rgba(255,255,255,0.75);";
 
    public static final String PRIMARY_BUTTON =
            "-fx-background-color: #f59e0b; -fx-text-fill: #1c1917; -fx-font-weight: bold;"
          + " -fx-padding: 10 22; -fx-background-radius: 8; -fx-cursor: hand;";
 
    public static final String SECONDARY_BUTTON =
            "-fx-background-color: transparent; -fx-text-fill: #ffffff;"
          + " -fx-border-color: rgba(255,255,255,0.4); -fx-border-radius: 8;"
          + " -fx-padding: 10 22; -fx-background-radius: 8; -fx-cursor: hand;";
 
    public static final String DANGER_BUTTON =
            "-fx-background-color: #dc2626; -fx-text-fill: white;"
          + " -fx-padding: 10 22; -fx-background-radius: 8; -fx-cursor: hand;";
 
    public static final String ERROR_TEXT =
            "-fx-text-fill: #fcd34d; -fx-font-size: 12px;";
 
    public static final String CARD =
            "-fx-background-color: #7c3aed; -fx-background-radius: 12;"
          + " -fx-border-color: rgba(255,255,255,0.2); -fx-border-radius: 12; -fx-border-width: 1;"
          + " -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.25), 16, 0, 0, 6);";
}