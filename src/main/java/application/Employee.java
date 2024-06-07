// Written by Maria
// Last edited: 22-04-2024 by Maria
package application;

import java.util.*;

public class Employee extends App {
	
	private String username;
	private String id;
	private boolean isProjectLeader;
	private List<Activity> assignedActivities;
	private int numberOfActivities;
	
	public Employee(String username, String id, boolean projectLeader, int activitiesAmount) {
		// Check valid ID
		if(id.length() == 4)
        	this.id = id;
        else
        	throw new IllegalArgumentException("ID must be 4 characters");
		
		// Initialize fields
		this.username = username;
        this.isProjectLeader = projectLeader;
        this.assignedActivities = new ArrayList<>();
        this.numberOfActivities = activitiesAmount;
    }

	public Employee(){
		
	}
	
	// Method to assign an activity
    public void assignActivity(Activity activity, Employee employee, Project project) throws Exception {
    	// Preconditions
        assert this.isProjectLeader : "Has to be project leader to assign activities";
        assert activity != null && project != null : "Invalid Activity or Project";
        assert employee != null : "Employee not found";
    	
        if (!this.isProjectLeader) 
        	throw new IllegalArgumentException("Has to be project leader to assign activities");
        
    	List<String> employees = project.listAvailableEmployeesByID();
    	boolean isEmployeeFound = false;
    	for (String emp : employees) {
    		if (employee.getID().equals(emp)) {
    			employee.updateActivities(activity);
        		project.addEmployee(employee);
        		isEmployeeFound = true;
    		}
    	}
    	
    	// Postcondition
        assert isEmployeeFound : "Employee not available";
        if (!isEmployeeFound) 
        	throw new Exception("Employee not available");
    }
    
    // Method to check if an activity with a given name exists
    public boolean hasActivityByName(String activityName) {
        return assignedActivities.stream().anyMatch(a -> a.getActivityName().equals(activityName));
    }
	
	public Project createProject(String projectName, int startWeek, int endWeek, int neededEmployees) throws Exception {
		assert projectName != null && !projectName.isEmpty() : "Project name must not be null or empty";
	    assert startWeek > 0 && endWeek >= startWeek && endWeek < 53 : "Start and end weeks must be positive and end week should be after start week";
	    assert neededEmployees > 0 : "Number of needed employees must be positive";
	    
		int availableEmployees = UserDatabase.getAvailableEmployees().size();
		assert availableEmployees >= neededEmployees : "Not enough available employees";
		
		if (neededEmployees > availableEmployees)
			throw new Exception("Not enough available employees");
		
		Project newProject = ProjectDatabase.createNewProject(projectName, startWeek, endWeek);
	    assert newProject != null : "A new project must be successfully created";
	    return newProject;
	}	
	
	public void deleteProject(Project deletedProject) throws Exception {
		String projectName = deletedProject.getProjectName();
		ProjectDatabase.deleteProject(projectName, currentWeek);
	}
	
	public void updateActivities(Activity activity) {
		assignedActivities.add(activity);
	}

	public List<Activity> getActivities() {
		return assignedActivities;
	}

	public void setProjectLeader(boolean projectLeader) {
		this.isProjectLeader = projectLeader;
	}

	public boolean getProjectLeader() {
		return this.isProjectLeader;
	}

	public String getUsername() {
        return username;
    }

	public String getID() {
		return id;
	}
	
	public int getNumberOfActivities() {
		return numberOfActivities;
	}

	

}
