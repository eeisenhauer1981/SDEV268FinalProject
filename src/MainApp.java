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
        Company company = new Company("Marshmallow Haven");
        Dates dates = new Dates();

        String dateString = "2026-04-03";
        LocalDate testHireDate = LocalDate.parse(dateString);
        dateString = "1999-02-02";
        LocalDate testBirthDate = LocalDate.parse(dateString);

        Employee testEmployee = new Employee(1, "First", "M", "Last", "Suff", "MGMT", "Job", true, testHireDate, "Hourly", 55.55, testBirthDate, "Female", "111 Street", "", "Fort Wayne", "IN", "46804", 3, "Family");
        testEmployee.newTimePunch(scanner, dates);
        testEmployee.newTimePunch(scanner, dates);
        System.out.println("Total Hours: " + testEmployee.getHoursWorked(dates));

        scanner.close();
    }
}