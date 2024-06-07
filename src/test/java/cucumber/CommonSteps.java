// Scaffolding by Maria
// Written by Maria
package cucumber;

import application.UserDatabase;
import application.ProjectDatabase;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.App;
import application.Project;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonSteps {
	
	private App app = new App();
	private UserDatabase database = new UserDatabase();
	private ProjectDatabase projectDatabase = new ProjectDatabase(); 
	static String lastErrorMessage = "";
	
	@Given("the project leader {string} is logged in")
	public void the_project_leader_is_logged_in(String leader) {
		boolean loggedIn = app.login(leader);
		assertTrue(loggedIn && database.getEmployee(leader).getProjectLeader(), leader + " is not a leader");
    }
	
	@Given("the employee {string} is logged into the system")
	public void the_employee_is_logged_into_the_system(String employeeId) {
	    boolean loggedIn = app.login(employeeId);
	    assertTrue(loggedIn, "Employee is not logged in");
	}

    @Given("the employee {string} exists")
    public void the_employee_exists(String id) {
    	assertTrue(database.validateUser(id), "Employee " + id + " not found");
    }
    
    @Given("the employee {string} does not exist in the system")
	public void the_employee_does_not_exist_in_the_system(String id) {
    	assertFalse(database.validateUser(id), "Employee " + id + " not found");
	}

    @Then("the system should display an error message {string}")
	public void the_system_should_display_an_error_message(String message) {
    	String actualMessage = lastErrorMessage.trim();
    	assertTrue(actualMessage.equalsIgnoreCase(message.trim()), "Expected error message not displayed");
	}

    @Given("the project {string} exists")
	public void the_project_exists(String projectName) {
    	Project project = projectDatabase.getProjectByName(projectName);
    	assertTrue(project != null, "Project does not exist: " + projectName);
	}
}
