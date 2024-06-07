// Marcel
package application;

import application.App;
//import io.cucumber.java.bs.A;
//import io.cucumber.java.bs.A;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class timeRegistrationController {

    @FXML
    private TextField hoursTextField;
    @FXML
    private ComboBox<String> itemComboBox;
    @FXML
    private ListView<String> itemsListView;
    private timetester timetesterInstance = timetester.getInstance();


    @FXML
    private void initialize() {
        //put project names in combo box
        
        ProjectDatabase pdb = new ProjectDatabase();
        ObservableList<String> projectNames = pdb.getProjectNames();
        ObservableList<String> joinedArr = null;
       
        for (String p : projectNames) {
            ObservableList<String> tempArr = pdb.getObservableActivities(p);
            if (joinedArr == null){
                joinedArr = tempArr;
            } else {
                joinedArr.addAll(tempArr);
            }  
        }
        System.out.println(joinedArr);
        ObservableList<String> projs = joinedArr;
        if (projs.isEmpty()) {
            projs.add("No Activities");
        }

        itemComboBox.setItems(projs);
        itemComboBox.setValue(projs.get(0)); // Set default value

        // Numeric input validation for hoursTextField
        hoursTextField.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            if (!keyEvent.getCharacter().matches("\\d*")) {
                keyEvent.consume();
            }
        });
        populateListView();
    }

    private void populateListView() {
        itemsListView.setItems(timetesterInstance.getItems());
    }

    @FXML
    private void handleBack() throws IOException {
        App.setRoot("afterLoginMain");
    }

    @FXML
    private void handleConfirm() throws IOException{
        //save the registered time and put it into the table
        String inputText = hoursTextField.getText();
        String inputProj = itemComboBox.getValue();

        int hoursToAdd = Integer.parseInt(inputText);

        Activity a = new Activity();
        Activity act = a.getActivityByName(inputProj);
        String name = act.getActivityName();

        act.updateHoursBetter(act, hoursToAdd);
        float hours = act.getSpentHours(); 


        //here i should get username and the date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm      dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        if(inputText!=""){timetesterInstance.addItem("Activity: " + name + "           Added hours: " +inputText+"          Total time spent: "+hours+"         [Time/Date]:  "+dtf.format(now));}
        itemsListView.refresh();  // Refresh the ListView to show the updated list
        System.out.println(timetesterInstance.getItems());
        //App.setRoot("afterLoginMain");
    }
}
