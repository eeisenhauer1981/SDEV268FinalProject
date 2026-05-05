import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainApp extends Application {
    private Company company = new Company("Marshmallow Haven");
    private Dates dates = new Dates();

    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        
        loadDemoEmployees();
        String adminHashPassword = SecurityUtil.hashMD5("Adm1n!");
        User adminUser = new User("HR0001", adminHashPassword, Role.ADMIN, -1, true);
        AuthenticationManager.addUser("HR0001", adminUser);

        this.primaryStage = stage;

        showLogin();

        stage.setTitle("SDEV268 Payroll App");
        stage.show();
    }

    public void loadDemoEmployees() {
        try (InputStream is = getClass().getResourceAsStream("/Payroll Project Load Employees - Emily Eisenhauer.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
        
            String employeeData;

            if (is == null) {
                throw new RuntimeException("File not found in resources!");
            }

            while ((employeeData = reader.readLine()) != null) {
                String[] dataFields = employeeData.split("\t");
            
                String newFirstName = dataFields[0];
                String newMiddleName = dataFields[1];
                String newLastName = dataFields[2];
                String newSuffix = dataFields[3];
                String newDepartment = dataFields[4];
                String newJobTitle = dataFields[5];
                boolean newActive = Boolean.parseBoolean(dataFields[6]);
                LocalDate newHireDate = LocalDate.parse(dataFields[7]);
                String newPayType = dataFields[8];
                double newBasePay = Double.parseDouble(dataFields[9]);
                LocalDate newBirthDate = LocalDate.parse(dataFields[10]);
                String newGender = dataFields[11];
                String newAddress1 = dataFields[12];
                String newAddress2 = dataFields[13];
                String newCity = dataFields[14];
                String newState = dataFields[15];
                String newZip = dataFields[16];
                int newDependents = Integer.parseInt(dataFields[17]);
                String newMedicalCoverage = dataFields[18];

                company.increaseEmployeeCount();
                String newEmailAddress = company.createEmailAddress(newFirstName, newLastName, company.getEmployeeCount());
                Employee newEmployee = new Employee(company.getEmployeeCount(), newFirstName, newMiddleName, newLastName, newSuffix, newDepartment, newJobTitle, newActive, newHireDate, newPayType, newBasePay, newBirthDate, newGender, newAddress1, newAddress2, newCity, newState, newZip, newDependents, newMedicalCoverage, newEmailAddress);
                company.addEmployee(company.getEmployeeCount(), newEmployee);
            }
            System.out.println("Employees loaded");
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        

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