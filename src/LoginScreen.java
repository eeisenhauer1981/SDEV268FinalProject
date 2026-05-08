import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.VBox;

public class LoginScreen {

    public Parent getView(MainApp app, Company company) {

        Label title = new Label("Login");

        //fields for user input
        Label usernameLabel = new Label("Enter your username:");
        TextField usernameField = new TextField();

        Label passwordLabel = new Label("Enter your password:");
        PasswordField passwordField = new PasswordField();

        //buttons - after entering login credentials, user must select login type
        Button loginAdminButton = new Button("Login as Admin");
        Button loginEmployeeButton = new Button("Login as Employee");
        Button exitButton = new Button("Exit");

        loginAdminButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            //verifies if user has entered valid admin credentials
            User adminUser = AuthenticationManager.authenticateAdmin(username, password);
            if(adminUser != null) {
                //if validated, user moves to admin main menu
                app.showAdminMainMenu();
            }
            else{
                //if not validated, user sees invalid message and can exit or return to the login screen
                app.showNoAccess();
            }
        });

        loginEmployeeButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            //verifies if user has entered valid employee credentials
            User employeeUser = AuthenticationManager.authenticateEmployee(username, password);
            if(employeeUser != null) {
                //if validated, user is set as company's active user, which is used to limit employee actions to logged in user's information
                company.setActiveUser(employeeUser);
                app.showEmployeeMainMenu();
            }
            else{
                //if not validated, user sees invalid message and can exit or return to the login screen
                app.showNoAccess();
            }
        });

        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            usernameLabel,
            usernameField,
            passwordLabel,
            passwordField,
            loginAdminButton,
            loginEmployeeButton
        );

        return layout;
    }
}