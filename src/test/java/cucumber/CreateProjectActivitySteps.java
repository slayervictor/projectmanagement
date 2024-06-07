// Scaffolding by Maria
// Written by Maria
package cucumber;

import application.Project;
import application.Activity;

import io.cucumber.java.en.*;

public class CreateProjectActivitySteps {

    private Project currentProject = new Project("Virtual Reality Expansion", 12, 14, false); // Sample project
    private Activity currentActivity;
	
	// Scenario "Adding new activities to a project"
	@When("project leader adds an activity {string} to the project {string} with an estimated time of {int} hours")
	public void adds_an_activity_to_the_project_with_an_estimated_time_of(String activityName, String projectName, int estimatedTime) {
		if (!currentProject.getProjectName().equals(projectName)) {
            throw new IllegalStateException("Mismatch in project context");
        }
        currentActivity = new Activity(activityName, estimatedTime, 0);
        currentProject.addProjectActivity(currentActivity);
	}

	@Then("the activity {string} should be listed under the project")
	public void the_activity_with_estimated_time_should_be_listed_under(String activityName) {
        currentProject.getActivityByName(activityName);
	}

	// Scenario "Fail to add activities to a project due to missing estimated time"
	@When("project leader tries to add an activity {string} to the project without estimated time")
	public void tries_to_add_an_activity_to_the_project_without_estimated_time(String activityName) {
		try {
			currentProject.addProjectActivity(new Activity(activityName));
		} catch(Exception e) {
			CommonSteps.lastErrorMessage = e.getMessage();
		}
	}

}
