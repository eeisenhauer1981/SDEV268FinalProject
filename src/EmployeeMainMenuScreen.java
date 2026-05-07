import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


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
            Employee foundEmployee = company.employeeSearch(company.activeUser.getEmployeeID());
            app.showEditTimeCard(foundEmployee, "Employee");
        });

        editPTOButton.setOnAction(e -> {
            Employee foundEmployee = company.employeeSearch(company.activeUser.getEmployeeID());
            app.showEditPTO(foundEmployee, "Employee");
        });

        calculatePayButton.setOnAction(e -> {
            Employee foundEmployee = company.employeeSearch(company.activeUser.getEmployeeID());
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
