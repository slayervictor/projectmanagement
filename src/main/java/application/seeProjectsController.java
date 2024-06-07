// Laurits
package application;

import application.App;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.io.IOException;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

public class seeProjectsController extends afterLoginMainController
{
    @FXML
    private TextField ProjectTextField;

    @FXML
    private ListView<String> ProjectList;
    private static ListView<String> ProjectListStatic = new ListView<String>();

    public static String newProjectName;

    public static String selectedProject;

    @FXML
    private ObservableList<String> items = FXCollections.observableArrayList();

    @FXML
    public void initialize()
    {
        ProjectDatabase forProjectNames = new ProjectDatabase();
        items = forProjectNames.getProjectNames();
        ProjectList.setItems(items);
        ProjectListStatic = ProjectList;
    }

    @FXML
    private void handleBack() throws IOException {
        // Logic to handle the back button goes here
        String Projlist=ProjectList.getSelectionModel().getSelectedItem();
        System.out.println(Projlist);
        application.App.setRoot("afterLoginMain");
    }

    @FXML
    private void handleCreate() throws IOException {
        try {
            newProjectName = ProjectTextField.getText();
            //need to make edit button and then delete button
            if (!newProjectName.isEmpty()) {
                application.App.setRoot("step1ProjCreate");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void handleEdit() throws IOException
    {
        String Projlist=ProjectList.getSelectionModel().getSelectedItem();
        if(Projlist!=null)
        {
            selectedProject=Projlist;
            App.setRoot("editProjScene");
        }
    }

    public String getNewProjectName()
    {
        return newProjectName;
    }

    public static String getCurrentProjectName(){

        String currentProject = ProjectListStatic.getSelectionModel().getSelectedItem();
        return currentProject;
    }


}
