// Marcel
package application;

import application.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.util.ArrayList;

public class step1ProjCreateController extends ProjectDatabase {

    private int startweek;
    private int endweek;
    @FXML
    private TextField startWeekTextField;
    @FXML
    private TextField endWeekTextField;
    private String projectName;

    @FXML
    public void initialize()
    {


        startWeekTextField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!keyEvent.getCharacter().matches("\\d*")) {
                keyEvent.consume();
            }
        });

        endWeekTextField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!keyEvent.getCharacter().matches("\\d*")) {
                keyEvent.consume();
            }
        });

    }


    @FXML
    private void handleBack() throws IOException {
        application.App.setRoot("seeProjects");
    }
    @FXML
    private void handleNext() throws Exception {

        startweek= Integer.parseInt(startWeekTextField.getText());
        endweek= Integer.parseInt(endWeekTextField.getText());
        seeProjectsController projectname= new seeProjectsController();
        projectName=projectname.getNewProjectName();

        createNewProject(projectName, startweek, endweek);

        ProjectDatabase forProjectNames = new ProjectDatabase();

        System.out.print(forProjectNames.getProjectNames());
        App.setRoot("seeProjects");

    }
}
