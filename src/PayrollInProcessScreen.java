import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

//message if payroll is in process and time cannot be edited
//called from EmployeeMainMenu if employee clicks editTimeCardButton or editPTOButton and company.payrollProcessing is true
public class PayrollInProcessScreen {
    public Parent getView(MainApp app) {

        Label inProcessLabel = new Label("Payroll is being processed. You cannot make any time card or PTO changes.");
    
        //option buttons
        Button returnToEmployeeMenuButton = new Button("Return to Employee Main Menu");
        Button exitButton = new Button("Exit");

        returnToEmployeeMenuButton.setOnAction(e -> {
            app.showEmployeeMainMenu();
        });
            
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            inProcessLabel,
            returnToEmployeeMenuButton,
            exitButton
        );

        return layout;
    }
}