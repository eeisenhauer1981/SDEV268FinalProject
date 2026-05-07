import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;

public class PreviewPaycheckScreen {
    public Parent getView(MainApp app, PayCheck previewPayCheck) {

        Label disclaimer = new Label("This is not a finalized paycheck. Your pay may be different when payroll is finalized.");

        Label previewCheckLabel = new Label(previewPayCheck.getPaycheckInfo());
        previewCheckLabel.setWrapText(true);


        //option buttons
        Button returnToMainMenuButton = new Button("Return to Employee Main Menu");

        returnToMainMenuButton.setOnAction(e -> {
            app.showEmployeeMainMenu();
        });

        VBox layout = new VBox(10, disclaimer, previewCheckLabel, returnToMainMenuButton);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(layout);
        scrollPane.setFitToWidth(true);
        return scrollPane;
        
    }
}
