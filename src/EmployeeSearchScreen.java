import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class EmployeeSearchScreen {
    public Parent getView(MainApp app, Company company, String sender) {

        Label title = new Label("Search");

        //fields for user input
        Label enterIDLabel = new Label("Enter Employee ID to search:");
        TextField enterIDField = new TextField();

        //buttons
        Button employeeSearch = new Button("Search");

        employeeSearch.setOnAction(e -> {
            int searchID = Integer.parseInt(enterIDField.getText());
            Employee foundEmployee = company.employeeSearch(searchID);

            if(sender.equals("EditInfo")) {
                app.showEditEmployee(foundEmployee);
            }

            if(sender.equals("EditTime")) {
                app.showEditTimeCard(foundEmployee, "Admin");
            }
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            enterIDLabel,
            enterIDField,
            employeeSearch
        );

        return layout;
    }
    
}
