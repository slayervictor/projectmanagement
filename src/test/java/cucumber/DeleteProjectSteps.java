// Scaffolding by Maria
package cucumber;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Project;
import application.ProjectDatabase;
import io.cucumber.java.en.*;
import javafx.scene.control.Alert;

public class DeleteProjectSteps {

	private ProjectDatabase projectDatabase = new ProjectDatabase();
    private int currentWeek;
	private Project project;
    
	@Given("a project titled {string} scheduled to start in week {int} exists")
	public void a_project_titled_scheduled_to_start_in_week(String projectName, int startWeek) {
		project = projectDatabase.getProjectByName(projectName);
		if (project != null)
			assertTrue(project.getStartWeek() == startWeek);
		else
			System.out.println("Project: " + projectName + ", with start week: " + startWeek + " does not exist.");
	}

	@Given("the week is {int}")
	public void the_week_is(int currentWeek) {
	    this.currentWeek = currentWeek;
	}

	// Scenario "Delete project before the scheduled start date"
	@When("the user attempts to delete the project {string}")
	public void the_user_attempts_to_delete_the_project(String projectName) {
		try {
            ProjectDatabase.deleteProject(projectName, currentWeek);
        } catch (Exception e) {
            CommonSteps.lastErrorMessage = e.getMessage();
        }
	}

	@Then("the project {string} should be deleted")
	public void the_project_should_be_deleted(String projectName) {
		assertTrue(projectDatabase.getProjectByName(projectName) == null);
	}

	// Scenario "Attempt to delete a project after the scheduled start date"
	// See CommonSteps
	@Then("the project {string} should not be deleted")
	public void the_project_should_not_be_deleted(String projectName) {
		assertFalse(projectDatabase.getProjectByName(projectName) == null);
	}
}
