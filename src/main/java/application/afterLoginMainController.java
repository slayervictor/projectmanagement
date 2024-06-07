// Marcel
package application;

import application.App;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.io.IOException;

public class afterLoginMainController extends PrimaryController {

    @FXML
    private Label usernameLabel;

    public void initialize()
    {
        PrimaryController forUsername = new PrimaryController();
        usernameLabel.textProperty().bind(forUsername.getUsername());
    }


    @FXML
    private void handleLogOut() throws Exception {
        System.out.println("Logout initiated.");
        logout();
        application.App.setRoot("primary");
    }

    @FXML
    private void switchToTimeRegistration() throws IOException {
        application.App.setRoot("timeRegistration");
    }

    @FXML
    private void switchToSeeProjects() throws IOException {
        App.setRoot("seeProjects");
    }
}
