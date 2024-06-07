// Scaffolding by Maria
package cucumber;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import application.Activity;
import application.TimeRegistration;
import io.cucumber.java.en.*;

public class ViewTimeRegistrationSteps {
	
	private TimeRegistration registrationDatabase = new TimeRegistration();
	private String currentUserId;

	// Scenario "Employee checks their time registrations"
	@Given("the employee {string} has registered {double} hours for activity {string} in week {int}")
	public void has_registered_for_on(String employeeId, double regsHours, String activityName, int week) throws Exception {
		new Activity(activityName, (float) 10, (float) regsHours);
		currentUserId = employeeId;
		try {
			registrationDatabase.registerTime(employeeId, week, activityName, (float) regsHours);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
        
	}

	@When("the employee requests to view their time registrations for week {int}")
	public void requests_to_view_their_time_registrations_for_the_week_of(int week) {
		try {
			registrationDatabase.getTimesForWeek(currentUserId, week);
		} catch(Exception e) {
			CommonSteps.lastErrorMessage = e.getMessage();
		}
	}

	@Then("the time registrations for the employee shows the registered time including {int} hours for {string} in week {int}")
	public void the_time_registrations_for_shows_the_registered_time_including_for_on(int hours, String activityName, int week) {
		float registeredHours = registrationDatabase.getTime(currentUserId, week, activityName);
        assertEquals(hours, registeredHours, "The registered hours for " + activityName + " in week " + week + " do not match the expected hours.");
	}

	// Scenario "View time registrations with no registrations"
	@Given("the employee {string} has no registered time for week {int} for activity {string}")
	public void has_no_registered_time_for_the_current_week(String employeeId, int week, String activityName) {
		Map<String, Float> registeredHours = registrationDatabase.getTimesForWeek(employeeId, week);
		assertTrue(registeredHours != null);
	}

	@Then("the system should display no time registrations for week {int}")
	public void the_system_should_display_no_time_registrations(int week) {
		String registrationsOutput = registrationDatabase.viewTimeRegistrations(currentUserId, week);
        assertTrue(registrationsOutput.contains("No time registrations for week " + week), "Expected no time registrations for week " + week + ", but found: " + registrationsOutput);
	}
	
}
