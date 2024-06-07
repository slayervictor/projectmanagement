// Scaffolding by Maria
package cucumber;

import io.cucumber.java.en.*;
import application.App;

public class LoginSteps {

	// -- By Maria
    private App login = new App();
	
	@Given("the user opens the application")
	public void the_user_opens_the_application() {
		login.displayLoginPage();
	}

	@When("the user enters their ID {string}")
	public void the_user_enters_their_id(String id) {
		login.login(id);
	}
	// -- 

	@Then("the system redirects the user to the home page")
	public void the_system_redirects_the_user_to_the_home_page() {
		login.displayHomePage();
    }
	
	// By Maria
	@Given("the user enters invalid ID {string}")
	public void the_user_enters_invalid_id(String id) {
        the_user_enters_their_id(id);
    }

	@Then("the system displays an error regarding the invalid credential")
	public void the_system_displays_an_error_regarding_the_invalid_credentials() {
        System.out.println("Displaying error: Invalid credentials");
    }

}
