// Written by Sebastian
package application;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class App extends Application{
	
	private static String currentUser; // Track the current logged-in user
	private static UserDatabase database = new UserDatabase();
	private Activity currentActivity;
	int currentWeek;
	
	public Activity getCurrentActivity() {
		return currentActivity;
	}
	
	public void setCurrentActivity(Activity act) {
		currentActivity = act;
	}
	
	// Method to set the current user when logging in
    public static void setCurrentUser(String id) {
    	Employee employee = database.getEmployee(id);
    	if (employee != null)
    		currentUser = id;
    	else
    		currentUser = null;
    }
    
    public static String getCurrentUser() {
    	return currentUser;
    }
    
    // Method to handle logging in
    public boolean login(String id) {
    	assert id != null : "ID cannot be null";
        if (database.validateUser(id)) {
            setCurrentUser(id); // Set the current user
            //displayHomePage(); // Redirect to home page TODO: Implement gui
            assert currentUser != null : "Failed to update current user";
            Employee loggedIn = database.getEmployee(id);

            loggedIn.setProjectLeader(true);
            return true;
        }
        // jdoe,JD01,true,10

        currentUser = null;
        assert currentUser == null : "Employee not in database";
        //displayLoginError();
        return false;
    }
    
    // Method to log out the current user
    public static void logout() throws Exception {
    	assert currentUser != null : "No user is currently logged in";

        System.out.println(currentUser + " has logged out");
        currentUser = null; // Clear the current user
        
        assert currentUser == null : "Logout not successful";
    }
	
	// Method to display login page - mock example
    public void displayLoginPage() {
        System.out.println("Login Page Displayed");
    }

    // Method to display home page
    public void displayHomePage() {
        System.out.println("Home Page Displayed");
    }

    // Method to show error message
    public void displayLoginError() {
        System.out.println("Invalid credentials");
    }
    
    public void setCurrentWeek(int newWeek) {
        if (newWeek < 0) {
	    	LocalDate date = LocalDate.now();
	        WeekFields weekFields = WeekFields.of(Locale.getDefault());
	        currentWeek = date.get(weekFields.weekOfWeekBasedYear());
        }
        else
        	currentWeek = newWeek;
    }

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("/dtu/example/ui/"+fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
