import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class LoginScreen {

    public Parent getView(MainApp app) {

        Label title = new Label("Login Screen");

        Button loginButton = new Button("Go to Main App");

        loginButton.setOnAction(e -> {
            app.showMainMenu();
        });

        VBox layout = new VBox(10, title, loginButton);

        return layout;
    }
}