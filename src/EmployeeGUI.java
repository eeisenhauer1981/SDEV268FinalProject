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

public class EmployeeGUI extends Application {

    @Override
    public void start(Stage stage) {
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();

        Label middleNameLabel = new Label("Middle Name:");
        TextField middleNameField = new TextField();
        
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();

        Label nameSuffixLabel = new Label("Suffix:");
        TextField nameSuffixField = new TextField();

        Label departmentLabel = new Label("Department:");
        ComboBox<String> departmentBox = new ComboBox<>();
        departmentBox.getItems().addAll("MGMT", "DIS", "PRD", "HR");

        Label jobTitleLabel = new Label("Job Title:");
        TextField jobTitleField = new TextField();

        Label activeLabel = new Label("Active Employee?:");
        CheckBox activeCheckBox = new CheckBox("Employee is Active");

        Label hireDateLabel = new Label("Hire Date:");
        DatePicker hireDatePicker = new DatePicker();

        Label payTypeLabel = new Label("Pay Type:");
        ComboBox<String> payTypeBox = new ComboBox<>();
        payTypeBox.getItems().addAll("Hourly", "Salary");

        Label basePayLabel = new Label("Base Pay:");
        TextField basePayField = new TextField();

        Label birthDateLabel = new Label("Date of Birth:");
        DatePicker birthDatePicker = new DatePicker();
        
        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Female", "Male", "Non-Binary", "Not Reported");
        
        Label address1Label = new Label("Address Line 1:");
        TextField address1Field = new TextField();

        Label address2Label = new Label("Address Line 2:");
        TextField address2Field = new TextField();

        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();

        Label stateLabel = new Label("State:");
        TextField stateField = new TextField();

        Label zipLabel = new Label("Zip Code:");
        TextField zipField = new TextField();

        Label dependentsLabel = new Label("Number of Dependents:");
        TextField dependentsField = new TextField();

        Label medicalCoverageLabel = new Label("Medical Coverage Type:");
        ComboBox<String> medicalCoverageBox = new ComboBox<>();
        medicalCoverageBox.getItems().addAll("Family", "Single");
        
        Button submitButton = new Button("Submit");

        submitButton.setOnAction(e -> {
            String firstName = firstNameField.getText();
            String middleName = middleNameField.getText();
            String lastName = lastNameField.getText();
            String suffix = nameSuffixField.getText();
            String department = departmentBox.getValue();
            String jobTitle = jobTitleField.getText();
            boolean active = activeCheckBox.isSelected();
            LocalDate hireDate = hireDatePicker.getValue();
            String payType = payTypeBox.getValue();
            double basePay = Double.parseDouble(basePayField.getText());
            LocalDate dateOfBirth = birthDatePicker.getValue();
            String gender = genderBox.getValue();
            String address1 = address1Field.getText();
            String address2 = address2Field.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip = zipField.getText();
            int dependents = Integer.parseInt(dependentsField.getText());
            String medicalCoverageType = medicalCoverageBox.getValue();

            Employee newEmployee = new Employee (
                firstName,
                middleName,
                lastName,
                suffix,
                department,
                jobTitle,
                active,
                hireDate,
                payType,
                basePay,
                dateOfBirth,
                gender,
                address1,
                address2,
                city,
                state,
                zip,
                dependents,
                medicalCoverageType
            );

            newEmployee.printEmployeeInfo();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
            firstNameLabel,
            firstNameField,
            middleNameLabel,
            middleNameField,
            lastNameLabel,
            lastNameField,
            nameSuffixLabel,
            nameSuffixField,
            departmentLabel,
            departmentBox,
            jobTitleLabel,
            jobTitleField,
            activeLabel,
            activeCheckBox,
            hireDateLabel,
            hireDatePicker,
            payTypeLabel,
            payTypeBox,
            basePayLabel,
            basePayField,
            birthDateLabel,
            birthDatePicker,
            genderLabel,
            genderBox,
            address1Label,
            address1Field,
            address2Label,
            address2Field,
            cityLabel,
            cityField,
            stateLabel,
            stateField,
            zipLabel,
            zipField,
            dependentsLabel,
            dependentsField,
            medicalCoverageLabel,
            medicalCoverageBox,
            submitButton);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        Scene scene = new Scene(scrollPane, 400, 500);
        stage.setScene(scene);
        stage.setTitle("Employee Input");
        stage.show();
    }
}