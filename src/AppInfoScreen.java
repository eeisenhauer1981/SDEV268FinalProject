import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AppInfoScreen {

    public Parent getView(MainApp app, Company company) {

        Label title = new Label("SDEV 268 Final Project");

        //fields for user input
        Label appNameLabel = new Label("LearnEx Payroll Calculator");

        Label versionLabel = new Label("v8.4");


        //buttons
        Button returnToMainButton = new Button("Return to Admin Main Menu");
        Button exitButton = new Button("Exit");

        returnToMainButton.setOnAction(e -> {
                app.showAdminMainMenu();

        });

        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            appNameLabel,
            versionLabel,
            returnToMainButton,
            exitButton
        );

        return layout;
    }
}