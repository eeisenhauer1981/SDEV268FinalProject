import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ViewEmployeesScreen {
    public Parent getView(MainApp app, Company company) {

        Label title = new Label("Employee List");

        VBox employeeList = new VBox(5);
        for (Employee emp : company.getEmployees()) {
            Label empLabel = new Label(emp.getEmployeeInfo());
            empLabel.setWrapText(true);
            employeeList.getChildren().add(empLabel);
        }


        //option buttons
        Button returnToLoginButton = new Button("Return to Login Screen");
        Button exitButton = new Button("Exit");

        returnToLoginButton.setOnAction(e -> {
            app.showLogin();
        });
            
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title, employeeList, returnToLoginButton, exitButton);
        

        return layout;
    }
}
