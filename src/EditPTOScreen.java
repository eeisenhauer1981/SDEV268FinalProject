import javafx.scene.Parent;
import java.time.LocalDate;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

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
