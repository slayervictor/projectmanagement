// Scaffolding by Maria
// Written by Maria
package cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Project;
import application.ProjectDatabase;
import io.cucumber.java.en.*;

public class AssignProjectLeaderSteps {

	private ProjectDatabase projectDatabase = new ProjectDatabase();
	private Project project;
	static String lastErrorMessage = "";
	
	// Scenario "Assigning an employee as the project leader"
	
	// CommonSteps
	
	@When("assign {string} as the project leader of {string}")
	public void assign_as_the_project_leader_of(String employeeId, String projectName) throws Exception {
        project = projectDatabase.getProjectByName(projectName);
        project.setProjectLeader(employeeId);
	}

	@Then("the employee {string} should be the project leader of the project")
	public void should_be_the_project_leader_of(String employeeId) {
        assertTrue(project.getProjectLeader().equals(employeeId),
                   "Employee " + employeeId + " is not the project leader of the project");
	}

	// Scenario "Fail to assign project leader due to employee not existing"
	@When("trying to assign the employee {string} as the project leader of {string}")
	public void trying_to_assign_as_the_project_leader_of(String employeeId, String projectName) {
		project = projectDatabase.getProjectByName(projectName);
		try {
            project.setProjectLeader(employeeId); 
        } catch (Exception e) {
        	CommonSteps.lastErrorMessage = e.getMessage();
        }
	}

}
