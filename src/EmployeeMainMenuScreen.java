import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;


public class EmployeeMainMenuScreen {
    public Parent getView(MainApp app, Company company) {

        Label title = new Label("Employee Menu");

        //instructions
        Label instructionsLabel = new Label("Select an option.");

        //option buttons
        Button editTimeCardButton = new Button("Edit Your Time Card");
        Button editPTOButton = new Button("Edit PTO");
        Button calculatePayButton = new Button("View Estimated Pay Check");

        editTimeCardButton.setOnAction(e -> {
            Employee foundEmployee = company.employeeSearch(company.activeUser.getEmployeeID());
            app.showEditTimeCard(foundEmployee);
        });

        editPTOButton.setOnAction(e -> {
            app.showEditPTO();
        });

        calculatePayButton.setOnAction(e -> {
            app.showCalculatePay();
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            instructionsLabel,
            editTimeCardButton,
            editPTOButton,
            calculatePayButton
        );

        return layout;
    }
    
}
