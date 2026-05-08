import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//menu for admin users to select actions
//called from LoginScreen when user enters successful employee credentials and clicks loginEmployeeButton
public class EmployeeMainMenuScreen {
    public Parent getView(MainApp app, Company company, Dates dates) {

        Label title = new Label("Employee Menu");

        //instructions
        Label instructionsLabel = new Label("Select an option.");

        //option buttons
        Button editTimeCardButton = new Button("Edit Your Time Card");
        Button editPTOButton = new Button("Edit PTO");
        Button calculatePayButton = new Button("View Estimated Pay Check");
        Button exitButton = new Button("Exit");

        editTimeCardButton.setOnAction(e -> {
            //if payroll is being processed, employees cannot edit time punches
            if(company.getPayrollProcessing()) {
                app.showPayrollInProcess();
            }
            else {
                //verifies current employee logged in
                Employee foundEmployee = company.employeeSearch(company.activeUser.getEmployeeID());
                //sends identity of employee to edit and sender identification so EditTimeCardScreen can direct flow correctly
                app.showEditTimeCard(foundEmployee, "Employee");
            }
        });

        editPTOButton.setOnAction(e -> {
            //if payroll is being processed, employees cannot edit PTO
            if(company.getPayrollProcessing()) {
                app.showPayrollInProcess();
            }
            else {
                //verifies current employee logged in
                Employee foundEmployee = company.employeeSearch(company.activeUser.getEmployeeID());
                //sends identity of employee to edit and sender identification so EditTimeCardScreen can direct flow correctly
                app.showEditPTO(foundEmployee, "Employee");
            }
        });

        calculatePayButton.setOnAction(e -> {
            //verifies current employee logged in
            Employee foundEmployee = company.employeeSearch(company.activeUser.getEmployeeID());
            //processes a single paycheck and outputs pay info on PreviewPaycheckScreen
            PayCheck samplePaycheck = new PayCheck (foundEmployee, company, dates);
            app.showPreviewPaycheck(samplePaycheck);
        });

        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            instructionsLabel,
            editTimeCardButton,
            editPTOButton,
            calculatePayButton,
            exitButton
        );

        return layout;
    }
    
}
