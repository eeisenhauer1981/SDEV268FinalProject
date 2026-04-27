import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.Scanner;

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

        //temp scanner until GUI is added
        Scanner scanner = new Scanner(System.in);
        
        Company marshmallowHaven = new Company("Marshmallow Haven");
        marshmallowHaven.loadEmployeeData(scanner);
        System.out.println("Next Employee Number: " + marshmallowHaven.employeeNumber);
        marshmallowHaven.editEmployeeData(scanner);
        System.out.println("Done editing");
        marshmallowHaven.printEmployees();
        

        scanner.close();
    }
}