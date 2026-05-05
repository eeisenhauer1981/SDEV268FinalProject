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

public class EditTimeCardScreen {
    public Parent getView(MainApp app, Company company, Dates dates, Employee punchEmployee, String sender) {
        //data entry fields

        Label punchDateLabel = new Label("Select Work Date:");
        DatePicker punchDatePicker = new DatePicker();

        Label hoursWorkedLabel = new Label("Hours Worked:");
        TextField hoursWorkedField = new TextField();
        
        Button submitButton = new Button("Submit Hours");

        submitButton.setOnAction(e -> {
            LocalDate punchDate = punchDatePicker.getValue();
            double hoursWorked = Double.parseDouble(hoursWorkedField.getText());
            if(dates.isValidTimeEntryDate(punchDate)) {
                punchEmployee.editTimePunch(punchDate, hoursWorked);
            }
            else {
                Alert invalidDateAlert = new Alert(AlertType.ERROR);
                invalidDateAlert.setTitle("Invalid Date");
                invalidDateAlert.setContentText("The date you selected is outside of the available pay period. Try again.");
                invalidDateAlert.showAndWait();
                return;
            }

            if(sender.equals("Admin")) {
                app.showSuccessfulAdminAction("Time Card updated");
            }
            else {
                app.showSuccessfulEmployeeAction("Time Card updated");
            }

        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            punchDateLabel,
            punchDatePicker,
            hoursWorkedLabel,
            hoursWorkedField,
            submitButton);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
    }
}
