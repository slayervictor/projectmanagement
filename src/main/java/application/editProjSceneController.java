// Victor
package application;

import application.App;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.Calendar;
import java.io.IOException;

public class editProjSceneController {
    @FXML
    private ComboBox<String> activityLeaderComboBox;
    @FXML
    private Label projectNameLabel;
    @FXML
    private ListView<String> activityListView;
    @FXML
    private ObservableList<String> items;

    @FXML
    private String activeItem;
    private static StringProperty projectName = new SimpleStringProperty();
    public static ObservableValue<String> getProjectName() {
        return projectName;
    }

    public static void setProjectName(String username) {
        editProjSceneController.projectName.set(username);
    }


    private ObservableList<String> temp = FXCollections.observableArrayList();
    private ArrayList<String> actL = new ArrayList<String>();

    public void initialize()
    {
        UserDatabase employees = new UserDatabase();
        activityLeaderComboBox.setItems(employees.getAllEmployeeNames());
        activityLeaderComboBox.setValue(employees.getAllEmployeeNames().get(0));
        setProjectName(seeProjectsController.selectedProject);

        projectNameLabel.textProperty().bind(getProjectName());


        ProjectDatabase pdb = new ProjectDatabase();



        String currentProject = seeProjectsController.getCurrentProjectName();
        System.out.println(currentProject);



        ArrayList<Activity> arr = new ArrayList<Activity>();


        if (pdb.getActivitiesNonStatic(currentProject) != null){
            arr = pdb.getActivitiesNonStatic(currentProject);


            for (Activity act : arr)
            {
                temp.add(act.getActivityName() + "     Start Week: " + act.getStartWeek() + "   End Week: " + act.getEndWeek());
                actL.add(act.getActivityName());
            }



            System.out.println(temp);

            items= pdb.getObservableActivities(currentProject);
            activityListView.setItems(temp);

        }


    }

    @FXML
    public void handleBack() throws IOException
    {
        application.App.setRoot("seeProjects");
    }
    @FXML
    public void handleDelete() throws IOException, Exception
    {
        Calendar calendar = Calendar.getInstance();
        int weekNumber = calendar.get(Calendar.WEEK_OF_YEAR);

        try {
        	ProjectDatabase.deleteProject(seeProjectsController.selectedProject, weekNumber);
        } catch (Exception e) {
        	Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText("Project cannot be deleted after it has started");
			alert.show();
        }
        application.App.setRoot("seeProjects");

    }
    @FXML
    public void handleConfirm() throws IOException
    {
        App.setRoot("seeProjects");
    }
    @FXML
    public void handleDeleteActivity() throws IOException
    {
        activeItem=activityListView.getSelectionModel().getSelectedItem();
        String projectName = seeProjectsController.getCurrentProjectName();
        String activityName = activeItem;
        try {
            ///ArrayList<Activity> arr = new ArrayList<Activity>();
            temp.remove(activityListView.getSelectionModel().getSelectedIndex());



            ProjectDatabase.getInstance().deleteActivity(projectName, activeItem.split(" ")[0]);

            App.setRoot("editProjScene");
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    @FXML
    public void handleCreateNew() throws IOException
    {
        App.setRoot("createActivity");
        activeItem=activityListView.getSelectionModel().getSelectedItem();
        System.out.println(activeItem);

    }

    public String getActivityItem()
    {
        System.out.println(activeItem);
        return activeItem;
    }
}
