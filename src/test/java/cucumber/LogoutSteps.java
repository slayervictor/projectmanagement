// Scaffolding by Maria
// Written by Maria
package cucumber;

import application.App;
import io.cucumber.java.en.*;

public class LogoutSteps {
	
	// Scenario "Employee logs out"
	@Given("the employee with ID {string} is logged in")
	public void the_employee_with_id_is_logged_in(String id) {
        App.setCurrentUser(id); // Set the current user
        System.out.println("Employee with ID " + id + " is logged in");
    }

	@When("the employee logs out")
	public void logs_out() throws Exception {
		App.logout();
	}

	@Then("{string} is logged out of the system")
	public void is_logged_out_of_the_system(String id) {
		System.out.println("Employee with ID " + id + " is logged out");
	}
}
