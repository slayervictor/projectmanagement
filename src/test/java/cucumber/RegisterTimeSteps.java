// Scaffolding by Maria
package cucumber;

import static org.junit.jupiter.api.Assertions.assertTrue;

import application.Activity;
import application.TimeRegistration;
import io.cucumber.java.en.*;

public class RegisterTimeSteps {
	
	private Activity currentActivity;
	private String currentEmployee;
	private TimeRegistration timeRegs = new TimeRegistration();
	private float previousHours;


	// Scenario "Successful time registration"
	@When("the employee {string} selects the activity {string}")
	public void the_employee_selects_the_activity(String employeeId, String activityName) {
		try {
			currentEmployee = employeeId;
			currentActivity = new Activity(activityName, 20, 0);
			timeRegs.registerTime(employeeId, 20, activityName, previousHours);
		} catch(Throwable e) {
			System.out.println(e.getMessage());
		}
	}

	@And("enters {float} hours for the activity for week {int}")
	public void enters_for_the_activity(float hours, int week) {
		try {
			previousHours = currentActivity.getSpentHours();
            timeRegs.registerTime(currentEmployee, week, currentActivity.getActivityName(), hours);
        } catch (Throwable e) {
            CommonSteps.lastErrorMessage = e.getMessage();
        }
	}

	@Then("the system adds {int} hours for the activity")
	public void the_system_adds_for(int hours) {
		System.out.println(previousHours+hours);
		System.out.println(currentActivity.getSpentHours());
	    assertTrue(previousHours+hours == currentActivity.getSpentHours());
	}
	
}
