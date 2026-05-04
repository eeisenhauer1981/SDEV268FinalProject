import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class FailedActionScreen {
    public Parent getView(MainApp app, String message) {
        //no access message
        Label successLabel = new Label("Action cannot be completed: " + message);
    
        //option buttons
        Button returnToAdminMenuButton = new Button("Return to Admin Main Menu");
        Button exitButton = new Button("Exit");

        returnToAdminMenuButton.setOnAction(e -> {
            app.showAdminMainMenu();
        });
            
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            successLabel,
            returnToAdminMenuButton,
            exitButton
        );

        return layout;
    }
}
