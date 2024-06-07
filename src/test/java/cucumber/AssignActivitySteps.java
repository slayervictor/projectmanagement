// Scaffolding by Maria
// Written by Maria
package cucumber;

import application.UserDatabase;
import application.Activity; 
import application.Project;
import application.Employee;

import io.cucumber.java.en.*;


public class AssignActivitySteps {
	
    private Activity activity = new Activity("User Interface Design", 40, 0);
    private UserDatabase database = new UserDatabase();
    private Project project = new Project("Virtual Reality Expansion", 12, 14, false); // Sample project
	
	// Scenario "Project leader assigns an activity to an employee"
	@Given("the employee {string} has less than 20 activities assigned")
	public void the_has_less_than_activities_assigned(String employee) {
		Employee emp = database.getEmployee(employee);
        if (emp.getActivities().size() >= 20) {
            throw new RuntimeException(employee + " has too many activities assigned");
        }
	}

	@Given("the project {string} has an activity {string}")
	public void the_project_has_an_activity(String projectName, String act) {
		// Ensure projectName matches the existing project
        if (!projectName.equals(project.getProjectName())) 
            throw new RuntimeException("Project: " + projectName + " does not exist");

        if (activity.getActivityName().equals(act))
        	project.addProjectActivity(activity);
	}
	
	@When("the project leader {string} assigns {string} to {string}")
	public void the_project_leader_assigns_to(String leader, String activityName, String employee) throws Exception {
        try {
        	Employee emp = database.getEmployee(employee);
        	Employee projectLeader = database.getEmployee(leader);
        	projectLeader.assignActivity(activity, emp, project);
        } catch(Throwable e) {
        	CommonSteps.lastErrorMessage = e.getMessage();
        }
	}
	
	@Then("{string} should be assigned to {string} in {string}")
	public void should_be_assigned_to_in(String employee, String activityName, String projectName) {
        Employee emp = database.getEmployee(employee);
        if (emp != null && emp.hasActivityByName(activityName)) 
            System.out.println(employee + " is assigned to " + activityName + " in " + projectName);
        else 
            throw new RuntimeException(employee + " wasn't assigned to " + activityName);
    }

	// Scenario "Attempt to assign an activity to a non-existent employee"
	@When("the project leader {string} tries to assign {string} to {string}")
	public void the_project_leader_tries_to_assign_to(String leader, String act, String employee) throws Exception {
		the_project_leader_assigns_to(leader, act, employee);
	}

	// See CommonSteps for error message

	@Then("{string} should not be assigned to employee {string}")
	public void should_not_be_assigned_to_anyone(String activity, String id) {
		Employee emp = database.getEmployee(id);
		if (emp != null)
			if (emp.hasActivityByName(activity))
				System.out.println("Mismanaged activity");
	}
	
}
