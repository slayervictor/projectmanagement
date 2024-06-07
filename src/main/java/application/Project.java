// Victor
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

// Victor
public class Project {
    private String title;
    private int startWeek;
    private int endWeek;
    private boolean completed;
    private List<Activity> activities;
    private List<Employee> employees;
    private String projectLeader;
    private UserDatabase database = new UserDatabase();
    
    // Victor
    public Project(String title, int startWeek, int endWeek, boolean completed) {
        this.title = title;
        this.startWeek = startWeek;
        this.endWeek= endWeek;
        this.completed = completed;
        
        activities = new ArrayList<Activity>();
        employees = new ArrayList<Employee>();
    } 

    public boolean isCompleted() {
        return this.completed;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public List<Employee> listAvailableEmployees() {
        List<Employee> employees = database.getEmployees(); 
        List<Employee> employeeList = new ArrayList<Employee>();
        for (int i = 0; i < employees.size(); i++) {
          if (employees.get(i).getActivities().size() < 20) {
            employeeList.add(employees.get(i));
          }
        }
        return employeeList;
      } 

    public List<String> listAvailableEmployeesByID() {
        List<Employee> employees = database.getEmployees();
        List<String> employeeList = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
          if (employees.get(i).getNumberOfActivities() < 20) {
            employeeList.add(employees.get(i).getID());
          }
        }
        return employeeList;
      }

    public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

    public void removeEmployee(Employee employee) {
		employees.remove(employee);
	}

    public void addProjectActivity(Activity activity) {
    	this.activities.add(activity);
	}
    
    public Activity getActivityByName(String activityName) {
    	return activities.stream()
                .filter(a -> a.getActivityName().equals(activityName))
                .findFirst()
                .orElse(null);
    }
    
    public String getProjectLeader() {
    	return projectLeader;
    }
    
    public void setProjectLeader(String employee) throws Exception {
		if (database.getEmployee(employee) != null) 
			this.projectLeader = employee;
		else 
			throw new Exception("Employee not found");
    }

    public List<Activity> getActivities() {
        return this.activities;
    }
    
    public boolean hasActivityByName(String activityName) {
        return activities.stream().anyMatch(a -> a.getActivityName().equals(activityName));
    }
    
    public String getProjectName() {
    	return title;
    }
    
    public int getStartWeek() { return startWeek; }
    public int getEndWeek() { return endWeek; }
    
    public void setEndWeek(int newWeek) throws Exception {
    	if (newWeek < this.endWeek) throw new Exception("End week cannot be in the past");
    	
    	this.endWeek = newWeek;
    }

    public void removeActivity(Activity activity) {
		this.activities.remove(activity);
	}

    public ObservableList<String> getObservableActivities(String virtualRealityExpansion) {
        ObservableList<String> activityNameList = FXCollections.observableArrayList();
        for (Activity act : activities) {
            activityNameList.add(act.getActivityName());
        }
        return activityNameList;
    }
}
