import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainApp extends Application {
    //creates Dates object for verifying payroll and timeclock dates
    private Dates dates = new Dates();
    //creates a Company object
    private Company company = new Company("Marshmallow Haven", dates);
    //creates a Stage for GUI
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        //loads 11 employees from file for demo
        loadDemoEmployees();
        //loads timeclock entries for some employees for paycheck calculation demo
        addDemoTimes();
        //creates admin user credentials
        String adminHashPassword = SecurityUtil.hashMD5("Adm1n!");
        User adminUser = new User("HR0001", adminHashPassword, Role.ADMIN, -1, true);
        AuthenticationManager.addUser("HR0001", adminUser);
    
        this.primaryStage = stage;

        showLogin();

        stage.setTitle("SDEV268 Payroll App");
        stage.show();
    }    

    //functions to load screens
    public void showLogin() {
        LoginScreen login = new LoginScreen();
        Scene scene = new Scene(login.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showAdminMainMenu() {
        AdminMainMenuScreen adminMainMenu = new AdminMainMenuScreen();
        Scene scene = new Scene(adminMainMenu.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showEmployeeMainMenu() {
        EmployeeMainMenuScreen employeeMainMenu = new EmployeeMainMenuScreen();
        Scene scene = new Scene(employeeMainMenu.getView(this, company, dates), 400, 300);
        primaryStage.setScene(scene);
    }

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

    public void showEditTimeCard(Employee punchEmployee, String source) {
        EditTimeCardScreen editTimeCard = new EditTimeCardScreen();
        Scene scene = new Scene(editTimeCard.getView(this, company, dates, punchEmployee, source), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showEditPTO(Employee PTOEmployee, String source) {
        EditPTOScreen editPTO = new EditPTOScreen();
        Scene scene = new Scene(editPTO.getView(this, company, dates, PTOEmployee, source), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showReviewPayroll() {
        ReviewPayrollScreen reviewPayroll = new ReviewPayrollScreen();
        Scene scene = new Scene(reviewPayroll.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showPreviewPaycheck(PayCheck previewPaycheck) {
        PreviewPaycheckScreen reviewPaycheck = new PreviewPaycheckScreen();
        Scene scene = new Scene(reviewPaycheck.getView(this, previewPaycheck), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showViewAppInfo() {
        AppInfoScreen appInfo = new AppInfoScreen();
        Scene scene = new Scene(appInfo.getView(this, company), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showNoAccess() {
        NoAccessScreen noAccess = new NoAccessScreen();
        Scene scene = new Scene(noAccess.getView(this), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showSuccessfulAdminAction(String message) {
        SuccessfulAdminActionScreen success = new SuccessfulAdminActionScreen();
        Scene scene = new Scene(success.getView(this, message), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showSuccessfulEmployeeAction(String message) {
        SuccessfulEmployeeActionScreen success = new SuccessfulEmployeeActionScreen();
        Scene scene = new Scene(success.getView(this, message), 400, 300);
        primaryStage.setScene(scene);
    }

    public void showPayrollInProcess() {
        PayrollInProcessScreen inProcess = new PayrollInProcessScreen();
        Scene scene = new Scene(inProcess.getView(this), 400, 300);
        primaryStage.setScene(scene);
    }

    public void loadDemoEmployees() {
        try (InputStream is = getClass().getResourceAsStream("/Payroll Project Load Employees - Emily Eisenhauer.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
        
            String employeeData;

            //loops through file and adds new employees
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

                //company employee count starts at 0 and increases before a new employee is added so that it can be passed as employeeID
                company.increaseEmployeeCount();
                String newEmailAddress = company.createEmailAddress(newFirstName, newLastName, company.getEmployeeCount());
                Employee newEmployee = new Employee(company.getEmployeeCount(), newFirstName, newMiddleName, newLastName, newSuffix, newDepartment, newJobTitle, newActive, newHireDate, newPayType, newBasePay, newBirthDate, newGender, newAddress1, newAddress2, newCity, newState, newZip, newDependents, newMedicalCoverage, newEmailAddress);
                company.addEmployee(company.getEmployeeCount(), newEmployee);
                
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addDemoTimes() {
        //loads sample times for pay period 5/2 - 5/8, which would be the most recently closed pay period as of estimated project grading time
        Employee entryEmployee = company.employees.get(2);
        entryEmployee.setTimePunch(LocalDate.of(2026, 04, 27), 6.5);
        entryEmployee.setTimePunch(LocalDate.of(2026, 04, 28), 6.5);
        entryEmployee.setTimePunch(LocalDate.of(2026, 04, 29), 6.5);

        entryEmployee = company.employees.get(3);
        entryEmployee.setTimePunch(LocalDate.of(2026, 04, 25), 6.0);
        entryEmployee.setTimePunch(LocalDate.of(2026, 04, 26), 8.0);
        entryEmployee.setTimePunch(LocalDate.of(2026, 04, 27), 10.0);

        entryEmployee = company.employees.get(5);
        entryEmployee.setPTO(LocalDate.of(2026, 04, 27), 1);
    }

    public static void main(String[] args) {
        
        launch(args);

    }
}