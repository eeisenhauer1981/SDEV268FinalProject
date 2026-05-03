import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class NoAccessScreen {
    public Parent getView(MainApp app) {

        Label title = new Label("Unauthorized");

        //no access message
        Label noAccessLabel = new Label("You are not authorized to perform that action.");
    
        //buttons
        Button returnToLoginButton = new Button("Return to Login Screen");
        Button exitButton = new Button("Exit");

        returnToLoginButton.setOnAction(e -> {
            app.showLogin();
        });
            
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            noAccessLabel,
            returnToLoginButton,
            exitButton
        );

        return layout;
    }
}
