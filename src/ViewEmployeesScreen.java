import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import java.util.HashMap;

public class ViewEmployeesScreen {
    public Parent getView(MainApp app, Company company) {

        Label title = new Label("Employee List");

        for(HashMap.Entry<Integer, Employee> entry : company.employees.entrySet())
            Employee outputEmployee = entry.getValue();
            Label idLabel = new Label("Employee ID: " + outputEmployee.getEmployeeID());
        System.out.println("Name: " + firstName + " " + middleName + " " + lastName + " " + suffix);
        System.out.println("Department: " + department);
        System.out.println("Job Title: " + jobTitle);
        if (active) {
            System.out.println("Status: Active");
        }
        else {
            System.out.println("Status: Terminated");            
        }
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Pay Type: " + payType);
        if (payType.equalsIgnoreCase("Hourly")) {
            System.out.println("Hourly Rate: $" + basePay);
        }
        else {
            System.out.println("Annual Salary: $" + basePay);  
        }
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);        
        System.out.print("Address: " + address1 + ", ");
        if (address2 != null) {
            System.out.print(address2 + ", ");
        }
        System.out.println(city + ", " + state + " " + zip);
        System.out.println("Dependents: " + dependents);
        System.out.println("Medical Coverage: " + medicalCoverageType);
        System.out.println("Email address: " + emailAddress);
        System.out.println();

        //option buttons
        Button returnToLoginButton = new Button("Return to Login Screen");
        Button exitButton = new Button("Exit");

        returnToLoginButton.setOnAction(e -> {
            app.showLogin();
        });
            
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        VBox layout = new VBox(10, title);
        layout.getChildren().addAll(
            testNameLabel
        );

        return layout;
    }
}
