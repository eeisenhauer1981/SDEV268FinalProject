import javafx.scene.Parent;
import java.time.LocalDate;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

//gets date input and number of days (int), verifies validity, and replaces entry if one exists or adds entry if one does not
//called from AdminMainMenuScreen when user clicks editPTOButton or from EmployeeMainMenuScreen when user clicks editPTOButton
//takes a sender flag to direct flow after actions are complete
public class EditPTOScreen {
    public Parent getView(MainApp app, Company company, Dates dates, Employee punchEmployee, String sender) {
        //data entry fields
        Label PTODateLabel = new Label("Select PTO Date to Add or Delete:");
        DatePicker PTODatePicker = new DatePicker();

        Label daysOffLabel = new Label("Enter Days Off to Add or delete:");
        TextField daysOffField = new TextField();
        
        Button submitButton = new Button("Submit New Request");
        Button deleteButton = new Button("Delete Existing Request");

        submitButton.setOnAction(e -> {
            LocalDate PTODate = PTODatePicker.getValue();
            int daysOff = Integer.parseInt(daysOffField.getText());
            //verifies that entered dates are on or after current date
            if(PTODate.isAfter(LocalDate.now()) || PTODate.isEqual(LocalDate.now())){
                punchEmployee.newPTO(PTODate, daysOff);
            }
            else {
                Alert invalidDateAlert = new Alert(AlertType.ERROR);
                invalidDateAlert.setTitle("Invalid Date");
                invalidDateAlert.setContentText("You cannot add PTO for a date in the past. Try again.");
                invalidDateAlert.showAndWait();
                return;
            }

            //sender directs flow to correct success screen so users can navigate back to correct main menu (admin or employee)
            if(sender.equals("Admin")) {
                app.showSuccessfulAdminAction("PTO updated");
            }
            else {
                app.showSuccessfulEmployeeAction("PTO updated");
            }

        });

        deleteButton.setOnAction(e -> {
            LocalDate PTODate = PTODatePicker.getValue();
            int daysOff = Integer.parseInt(daysOffField.getText());
            //verifies that entered dates are on or after current date
            if(PTODate.isAfter(LocalDate.now()) || PTODate.isEqual(LocalDate.now())){
                punchEmployee.deletePTO(PTODate, daysOff);
            }
            else {
                Alert invalidDateAlert = new Alert(AlertType.ERROR);
                invalidDateAlert.setTitle("Invalid Date");
                invalidDateAlert.setContentText("You cannot delete PTO for a date in the past. Try again.");
                invalidDateAlert.showAndWait();
                return;
            }

            //sender directs flow to correct success screen so users can navigate back to correct main menu (admin or employee)
            if(sender.equals("Admin")) {
                app.showSuccessfulAdminAction("PTO deleted");
            }
            else {
                app.showSuccessfulEmployeeAction("PTO updated");
            }

        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            PTODateLabel,
            PTODatePicker,
            daysOffLabel,
            daysOffField,
            submitButton,
            deleteButton);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }
}
