import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;

public class MainApp /*extends Application*/ {

    /* Temporary remove until building GUI
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

    public void showMainMenu() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Main Menu Placeholder"), 400, 300));
    }
    */

    public static void main(String[] args) {
        //launch(args);
        Employee newEmployee = new Employee ("First", "M", "Last", "", "DIS", "JobTitle", true, LocalDate.of(2026, 4, 20), "Hourly", 23.56, LocalDate.of(1981, 2, 12), "F", "123 Main St", "", "Fort Wayne", "IN", "46804", 2, "Family");
        newEmployee.printEmployeeInfo();

        PayCheck newPayCheck = new PayCheck(newEmployee, 1);
        newPayCheck.calculatePayCheck();
        newPayCheck.printPaycheck();
    }
}