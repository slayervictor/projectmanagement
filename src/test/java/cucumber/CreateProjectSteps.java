// Scaffolding by Maria
package cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Employee;
import application.ProjectDatabase;
import application.UserDatabase;
import io.cucumber.java.en.*;

public class CreateProjectSteps {

	private UserDatabase userDatabase = new UserDatabase();
	private ProjectDatabase projectDatabase = new ProjectDatabase();
    private String projectName;
    private Employee currentEmployee;
	
	@Given("an employee {string} tries to create a project {string} with {int} employees")
	public void an_employee_tries_to_create_a_project_with_employees(String employeeId, String projectName, int numberOfEmployees) {
	    currentEmployee = userDatabase.getEmployee(employeeId);
	    this.projectName = projectName;
	    try {
	    	currentEmployee.createProject(projectName, 1, 2, numberOfEmployees);
	    } catch(Throwable e) {
	    	CommonSteps.lastErrorMessage = e.getMessage();
	    }
	}
	
	@And("there are enough employees {int} that have time")
	public void there_are_engough_employees_that_have_time(int neededEmployees) {
		assertTrue(UserDatabase.getAvailableEmployees().size() >= neededEmployees);
	}
	
	@And("there arent enough employees {int} that have time")
	public void there_arent_enough_employees_that_have_time(int neededEmployees) {
		assertTrue(UserDatabase.getAvailableEmployees().size() < neededEmployees);
	}

	@Then("the project is created")
	public void the_project_is_created() {
	    assertTrue(projectDatabase.getProjectByName(projectName) != null);
	}
	
	@Then("the project is not created")
	public void the_project_is_not_created() {
		assertTrue(projectDatabase.getProjectByName(projectName) == null);
	}
}
