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

public class EditEmployeeScreen {
    public Parent getView(MainApp app, Company company, Employee editEmployee) {
        //employee data fields
        Label firstNameLabel = new Label("First Name:");
        TextField firstNameField = new TextField();
        firstNameField.setText(editEmployee.getFirstName());

        Label middleNameLabel = new Label("Middle Name:");
        TextField middleNameField = new TextField();
        middleNameField.setText(editEmployee.getMiddleName());
        
        Label lastNameLabel = new Label("Last Name:");
        TextField lastNameField = new TextField();
        lastNameField.setText(editEmployee.getLastName());

        Label nameSuffixLabel = new Label("Suffix:");
        TextField nameSuffixField = new TextField();
        nameSuffixField.setText(editEmployee.getSuffix());

        Label departmentLabel = new Label("Department:");
        ComboBox<String> departmentBox = new ComboBox<>();
        departmentBox.getItems().addAll("MGMT", "DIS", "PRD", "HR");
        departmentBox.setValue(editEmployee.getDepartment());

        Label jobTitleLabel = new Label("Job Title:");
        TextField jobTitleField = new TextField();
        jobTitleField.setText(editEmployee.getJobTitle());

        Label activeLabel = new Label("Active Employee?:");
        CheckBox activeCheckBox = new CheckBox("Employee is Active");
        activeCheckBox.setSelected(editEmployee.getActive());

        Label hireDateLabel = new Label("Hire Date:");
        DatePicker hireDatePicker = new DatePicker();
        hireDatePicker.setValue(editEmployee.getHireDate());

        Label payTypeLabel = new Label("Pay Type:");
        ComboBox<String> payTypeBox = new ComboBox<>();
        payTypeBox.getItems().addAll("Hourly", "Salary");
        payTypeBox.setValue(editEmployee.getPayType());

        Label basePayLabel = new Label("Base Pay:");
        TextField basePayField = new TextField();
        basePayField.setText(String.valueOf(editEmployee.getBasePay()));

        Label birthDateLabel = new Label("Birth Date:");
        DatePicker birthDatePicker = new DatePicker();
        birthDatePicker.setValue(editEmployee.getDateOfBirth());
        
        Label genderLabel = new Label("Gender:");
        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Female", "Male", "Non-Binary", "Not Reported");
        genderBox.setValue(editEmployee.getGender());
        
        Label address1Label = new Label("Address Line 1:");
        TextField address1Field = new TextField();
        address1Field.setText(editEmployee.getAddress1());

        Label address2Label = new Label("Address Line 2:");
        TextField address2Field = new TextField();
        address2Field.setText(editEmployee.getAddress2());

        Label cityLabel = new Label("City:");
        TextField cityField = new TextField();
        cityField.setText(editEmployee.getCity());

        Label stateLabel = new Label("State:");
        TextField stateField = new TextField();
        stateField.setText(editEmployee.getState());

        Label zipLabel = new Label("Zip Code:");
        TextField zipField = new TextField();
        zipField.setText(editEmployee.getZip());

        Label dependentsLabel = new Label("Number of Dependents:");
        TextField dependentsField = new TextField();
        dependentsField.setText(String.valueOf(editEmployee.getDependents()));

        Label medicalCoverageLabel = new Label("Medical Coverage Type:");
        ComboBox<String> medicalCoverageBox = new ComboBox<>();
        medicalCoverageBox.getItems().addAll("Family", "Single");
        medicalCoverageBox.setValue(editEmployee.getMedicalCoverageType());
        
        Button submitButton = new Button("Submit Changes");

        submitButton.setOnAction(e -> {
            editEmployee.setFirstName(firstNameField.getText());
            editEmployee.setMiddleName(middleNameField.getText());
            editEmployee.setLastName(lastNameField.getText());
            editEmployee.setSuffix(nameSuffixField.getText());
            editEmployee.setDepartment(departmentBox.getValue());
            editEmployee.setJobTitle(jobTitleField.getText());
            editEmployee.setActive(activeCheckBox.isSelected());
            editEmployee.setHiredate(hireDatePicker.getValue());
            editEmployee.setPayType(payTypeBox.getValue());
            editEmployee.setBasePay(Double.parseDouble(basePayField.getText()));
            editEmployee.setDateOfBirth(birthDatePicker.getValue());
            editEmployee.setGender(genderBox.getValue());
            editEmployee.setAddress1(address1Field.getText());
            editEmployee.setAddress2(address2Field.getText());
            editEmployee.setCity(cityField.getText());
            editEmployee.setState(stateField.getText());
            editEmployee.setZip(zipField.getText());
            editEmployee.setDependents(Integer.parseInt(dependentsField.getText()));
            editEmployee.setMedicalCoverageType(medicalCoverageBox.getValue());

            app.showSuccessfulAction("Employee successfully edited");
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
        return scrollPane;
    }
}
