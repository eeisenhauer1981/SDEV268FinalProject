import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;

        showLogin();

        stage.setTitle("SDEV268 Payroll App");
        stage.show();
    }

    public void showLogin() {
        LoginScreen login = new LoginScreen();
        Scene scene = new Scene(login.getView(this), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showAdminMainMenu() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Admin Menu Placeholder"), 400, 300));
    }

    public void showEmployeeMainMenu() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Employee Menu Placeholder"), 400, 300));
    }

    public void showNoAccess() {
        NoAccessScreen noAccess = new NoAccessScreen();
        Scene scene = new Scene(noAccess.getView(this), 400, 300);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        Employee testEmployee = new Employee("Marshmallow Haven", 1, "Mary", "J", "Tester", "", "MGMT", "JobTitle", true, LocalDate.of(2023, 03, 17), "Salary", 55251.00, LocalDate.of(1999, 01, 01), "Female", "111 Address", "", "Fort Wayne", "IN", "46804", 3, "Family");
        testEmployee.printEmployeeInfo();
        String adminHashPassword = SecurityUtil.hashMD5("Adm1n!");
        User adminUser = new User("HR0001", adminHashPassword, Role.ADMIN, -1, true);
        AuthenticationManager.addUser("HR0001", adminUser);

        launch(args);

    }
}