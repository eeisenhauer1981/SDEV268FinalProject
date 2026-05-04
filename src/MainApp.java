import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.time.LocalDate;

public class MainApp extends Application {
    private Company company = new Company("Marshmallow Haven");
    private Dates dates = new Dates();

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        
        Employee testEmployee1 = new Employee(1, "Mary", "J", "Tester", "", "MGMT", "JobTitle", true, LocalDate.of(2023, 03, 17), "Salary", 55251.00, LocalDate.of(1999, 01, 01), "Female", "111 Address", "", "Fort Wayne", "IN", "46804", 3, "Family", "MaryTester1@MarshmallowHaven.com");
        company.increaseEmployeeCount();
        company.addEmployee(1, testEmployee1);
        Employee testEmployee2 = new Employee(2, "Jack", "F", "Employee", "", "DIS", "JobTitle2", true, LocalDate.of(2026, 07, 24), "Hourly", 55.25, LocalDate.of(2003, 02, 02), "Male", "555 Address", "Apt. 2", "Fort Wayne", "IN", "46801", 0, "Single", "JackEmployee2@MarshmallowHaven.com");
        company.increaseEmployeeCount();
        company.addEmployee(2, testEmployee2);
        String adminHashPassword = SecurityUtil.hashMD5("Adm1n!");
        User adminUser = new User("HR0001", adminHashPassword, Role.ADMIN, -1, true);
        AuthenticationManager.addUser("HR0001", adminUser);

        this.primaryStage = stage;

        showLogin();

        stage.setTitle("SDEV268 Payroll App");
        stage.show();
    }

    public void showLogin() {
        LoginScreen login = new LoginScreen();
        Scene scene = new Scene(login.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    //main menu screens
    public void showAdminMainMenu() {
        AdminMainMenuScreen adminMainMenu = new AdminMainMenuScreen();
        Scene scene = new Scene(adminMainMenu.getView(this), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showEmployeeMainMenu() {
        EmployeeMainMenuScreen employeeMainMenu = new EmployeeMainMenuScreen();
        Scene scene = new Scene(employeeMainMenu.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    //action screens
    public void showViewEmployees() {
        ViewEmployeesScreen viewEmployees = new ViewEmployeesScreen();
        Scene scene = new Scene(viewEmployees.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showAddEmployee() {
        AddEmployeeScreen addEmployee = new AddEmployeeScreen();
        Scene scene = new Scene(addEmployee.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showEmployeeSearch(String sender) {
        EmployeeSearchScreen searchEmployee = new EmployeeSearchScreen();
        Scene scene = new Scene(searchEmployee.getView(this, company, sender), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showEditEmployee(Employee foundEmployee) {
        EditEmployeeScreen editEmployee = new EditEmployeeScreen();
        Scene scene = new Scene(editEmployee.getView(this, company, foundEmployee), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showEditTimeCard(Employee punchEmployee) {
        EditTimeCardScreen editTimeCard = new EditTimeCardScreen();
        Scene scene = new Scene(editTimeCard.getView(this, company, dates, punchEmployee), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showEditPTO() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Edit PTO Placeholder"), 400, 300));
    }

    public void showReviewPayroll() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Review Payroll Placeholder"), 400, 300));
    }

    public void showProcessPayroll() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Process Payroll Placeholder"), 400, 300));
    }

    public void showCalculatePay() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Paycheck Calculator Placeholder"), 400, 300));
    }

    public void showViewAppInfo() {
        primaryStage.setScene(new Scene(new javafx.scene.control.Label("Show App Info Placeholder"), 400, 300));
    }

    //message screens
    public void showNoAccess() {
        NoAccessScreen noAccess = new NoAccessScreen();
        Scene scene = new Scene(noAccess.getView(this), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showSuccessfulAction(String message) {
        SuccessfulActionScreen success = new SuccessfulActionScreen();
        Scene scene = new Scene(success.getView(this, message), 400, 300);
        primaryStage.setScene(scene);
    }

    public static void main(String[] args) {
        
        launch(args);

    }
}