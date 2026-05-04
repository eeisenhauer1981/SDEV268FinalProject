import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class AdminMainMenuScreen {
    public Parent getView(MainApp app) {

        Label title = new Label("Admin Menu");

        //instructions
        Label instructionsLabel = new Label("Select an option.");

        //option buttons
        Button viewEmployeesButton = new Button("View Employee List");
        Button addEmployeeButton = new Button("Add New Employee");
        Button editEmployeeButton = new Button("Edit Employee Information");
        Button editTimeCardButton = new Button("Edit Employee Time Card");
        Button reviewPayrollButton = new Button("Review Employee Payroll Information");
        Button processPayrollButton = new Button("Process Payroll");
        Button viewAppInfoButton = new Button("Payroll App Information");

        viewEmployeesButton.setOnAction(e -> {
            app.showViewEmployees();
        });

        addEmployeeButton.setOnAction(e -> {
            app.showAddEmployee();
        });

        editEmployeeButton.setOnAction(e -> {
            app.showEmployeeSearch("EditInfo");
        });

        editTimeCardButton.setOnAction(e -> {
            app.showEmployeeSearch("EditTime");
        });

        reviewPayrollButton.setOnAction(e -> {
            app.showReviewPayroll();
        });

        processPayrollButton.setOnAction(e -> {
            app.showProcessPayroll();
        });

        viewAppInfoButton.setOnAction(e -> {
            app.showViewAppInfo();
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            instructionsLabel,
            viewEmployeesButton,
            addEmployeeButton,
            editEmployeeButton,
            editTimeCardButton,
            reviewPayrollButton,
            processPayrollButton,
            viewAppInfoButton
        );

        return layout;
    }
    
}
