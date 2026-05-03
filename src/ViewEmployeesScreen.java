import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

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
        Button returnToAdminMenuButton = new Button("Return to Admin Main Menu");
        Button exitButton = new Button("Exit");

        returnToAdminMenuButton.setOnAction(e -> {
            app.showAdminMainMenu();
        });
            
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title, employeeList, returnToAdminMenuButton, exitButton);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
        
    }
}
