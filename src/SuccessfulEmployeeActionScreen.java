import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//message screen when an employee user successfully completes an action
//user has option to return to Employee main menu to complete more actions or exit the program
public class SuccessfulEmployeeActionScreen {
    public Parent getView(MainApp app, String message) {
        //no access message
        Label successLabel = new Label("Successful entry: " + message);
    
        //option buttons
        Button returnToAdminMenuButton = new Button("Return to Employee Main Menu");
        Button exitButton = new Button("Exit");

        returnToAdminMenuButton.setOnAction(e -> {
            app.showEmployeeMainMenu();
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