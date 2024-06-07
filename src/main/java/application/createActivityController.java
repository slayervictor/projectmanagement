// Sebastian
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

import java.io.IOException;

public class createActivityController {
    @FXML
    private ComboBox<String> joeComboBox;
    @FXML
    private ComboBox<String> smithComboBox;
    @FXML
    private ComboBox<String> swayneComboBox;
    @FXML
    private ComboBox<String> clarkComboBox;
    @FXML
    private ComboBox<String> dysonComboBox;
    @FXML
    private TextField estimatedTimeTextField;
    @FXML
    private TextField nameTextField;

    @FXML
    private TextField startWeekTextField;
    @FXML
    private TextField endWeekTextField;
    @FXML
    private ObservableList<String> yesNOList;
    private String activityName;



    public void initialize()
    {
        yesNOList= FXCollections.observableArrayList("yes","no");
        joeComboBox.setItems(yesNOList);
        joeComboBox.setValue(yesNOList.get(1));
        smithComboBox.setItems(yesNOList);
        smithComboBox.setValue(yesNOList.get(1));
        swayneComboBox.setItems(yesNOList);
        swayneComboBox.setValue(yesNOList.get(1));
        clarkComboBox.setItems(yesNOList);
        clarkComboBox.setValue(yesNOList.get(1));
        dysonComboBox.setItems(yesNOList);
        dysonComboBox.setValue(yesNOList.get(1));
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
        estimatedTimeTextField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!keyEvent.getCharacter().matches("\\d*")) {
                keyEvent.consume();
            }
        });
    }

    public void handleBack() throws IOException
    {
        App.setRoot("editProjScene");
    }

    public void handleCreate() throws IOException
    {
        if(estimatedTimeTextField.getText()!= "" && startWeekTextField.getText()!= "" && endWeekTextField.getText()!="")
        {
            activityName=nameTextField.getText();
            //Activity activityName = new Activity();

            int estimate = Integer.valueOf(estimatedTimeTextField.getText());

            Activity newAct = new Activity(activityName, estimate, 0);

            ProjectDatabase pdb = new ProjectDatabase();
            String currentProject = seeProjectsController.getCurrentProjectName();
            System.out.println(currentProject);
            pdb.setActivity(currentProject, newAct);
            System.out.println(pdb.getActivities(currentProject));


            String joeIn = joeComboBox.getValue();
            String smithIn = smithComboBox.getValue();
            String wayneIn = swayneComboBox.getValue();
            String clarckIn = clarkComboBox.getValue();
            String dysonIn = dysonComboBox.getValue();
            UserDatabase udb = new UserDatabase();
            Employee e = new Employee();
            Project tempProj = pdb.getProjectByName(currentProject);

            newAct.setStartWeek(Integer.valueOf(startWeekTextField.getText()));
            newAct.setEndWeek(Integer.valueOf(endWeekTextField.getText()));

            if (joeIn == "yes") {
                try {
                    e.assignActivity(newAct, udb.getEmployee("JD01"), tempProj);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
            if (smithIn == "yes") {
                try {
                    e.assignActivity(newAct, udb.getEmployee("AS02"), tempProj);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if (wayneIn == "yes") {
                try {
                    e.assignActivity(newAct, udb.getEmployee("BW03"), tempProj);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if (clarckIn == "yes") {
                try {
                    e.assignActivity(newAct, udb.getEmployee("CW04"), tempProj);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            if (dysonIn == "yes") {
                try {
                    e.assignActivity(newAct, udb.getEmployee("DJ05"), tempProj);
                } catch (Exception e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }




            App.setRoot("editProjScene");
        }

    }
}
