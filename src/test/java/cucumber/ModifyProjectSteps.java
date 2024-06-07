// Scaffolding by Maria
package cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Project;
import application.ProjectDatabase;
import io.cucumber.java.en.*;

public class ModifyProjectSteps {
	
	private ProjectDatabase projectDatabase = new ProjectDatabase();
    private Project project;

	@Given("the project {string} with the end week {int} exists")
	public void the_project_with_an_end_date_of_exists(String projectName, int endWeek) {
		project = projectDatabase.getProjectByName(projectName);
		if (project != null)
			assertTrue(project.getEndWeek() == endWeek);
		else
			System.out.println("Project: " + projectName + ", with start week: " + endWeek + " does not exist.");
	}
	
	// Both scenarios
	@When("the project leader {string} tries to change the end week of the project to a new week {int}")
	public void the_project_leader_tries_to_change_the_end_date_of_to(String leader, int newWeek) {
        try {
        	project.setEndWeek(newWeek);
        } catch(Exception e) {
        	CommonSteps.lastErrorMessage = e.getMessage();
        }
		
	}

	@Then("the end week of the project should be {int}")
	public void the_end_date_of_should_be(int endWeek) {
        assertTrue(endWeek == project.getEndWeek());
	}


}
