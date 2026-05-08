import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//menu for admin users to select actions
//called from LoginScreen when user enters successful admin credentials and clicks loginAdminButton
public class AdminMainMenuScreen {
    public Parent getView(MainApp app, Company company) {

        Label title = new Label("Admin Menu");

        //instructions
        Label instructionsLabel = new Label("Select an option.");

        //option buttons
        Button viewEmployeesButton = new Button("View Employee List");
        Button addEmployeeButton = new Button("Add New Employee");
        Button editEmployeeButton = new Button("Edit Employee Information");
        Button editTimeCardButton = new Button("Edit Employee Time Card");
        Button editPTOButton = new Button("Edit Employee PTO");
        Button processPayrollButton = new Button("Process Payroll");
        Button viewAppInfoButton = new Button("Payroll App Information");
        Button exitButton = new Button("Exit");

        //actions for each button
        viewEmployeesButton.setOnAction(e -> {
            app.showViewEmployees();
        });

        addEmployeeButton.setOnAction(e -> {
            app.showAddEmployee();
        });

        editEmployeeButton.setOnAction(e -> {
            //sends EditInfo so EmployeeSearchScreen knows to direct flow to EditEmployeeScreen after employee is found
            app.showEmployeeSearch("EditInfo");
        });

        editTimeCardButton.setOnAction(e -> {
            //sends EditTime so EmployeeSearchScreen knows to direct flow to EditTimeCardScreen after employee is found
            app.showEmployeeSearch("EditTime");
        });

        editPTOButton.setOnAction(e -> {
            //sends EditPTO so EmployeeSearchScreen knows to direct flow to EditPTOScreen after employee is found
            app.showEmployeeSearch("EditPTO");
        });

        processPayrollButton.setOnAction(e -> {
            //processPayroll runs processPayroll() then directs flow to ReviewPayrollScreen, where payroll can be approved
            company.processPayroll();
            app.showReviewPayroll();
        });

        viewAppInfoButton.setOnAction(e -> {
            app.showViewAppInfo();
        });

        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            instructionsLabel,
            viewEmployeesButton,
            addEmployeeButton,
            editEmployeeButton,
            editTimeCardButton,
            editPTOButton,
            processPayrollButton,
            viewAppInfoButton,
            exitButton
        );

        return layout;
    }
    
}
