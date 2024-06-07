// Laurits
package application;

import java.io.IOException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class PrimaryController extends App {
    @FXML
    private TextField usernameTextField;
    @FXML
    private String Username;
    @FXML
    private void onEnter(ActionEvent ae) throws IOException
    {
        switchToSecondary();
    }

    @FXML
    private void switchToSecondary() throws IOException {
        setUsername(usernameTextField.getText());
        Username = usernameTextField.getText();
        System.out.println(Username+" ");

        if(login(Username))
        {
            App.setRoot("afterLoginMain");
        }

    }

    private static StringProperty username = new SimpleStringProperty();

    public static ObservableValue<String> getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        PrimaryController.username.set(username);
    }

}
