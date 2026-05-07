import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class ReviewPayrollScreen {
    public Parent getView(MainApp app, Company company) {

        Label title = new Label("Paychecks");

        VBox payCheckList = new VBox(5);
        for (PayCheck check : company.getinProcessChecks()) {
            Label checkLabel = new Label(check.getPaycheckInfo());
            checkLabel.setWrapText(true);
            payCheckList.getChildren().add(checkLabel);
        }


        //option buttons
        Button approvePayrollButton = new Button("Approve Payroll");

        approvePayrollButton.setOnAction(e -> {
            company.addApprovedPayChecks();
            app.showSuccessfulAdminAction("Payroll processed.");
        });

        VBox layout = new VBox(10, title, payCheckList, approvePayrollButton);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
        
    }
}
