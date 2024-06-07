// Maria
package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.*;

import java.io.File;
import java.io.IOException;

public class UserDatabase {
	
	private static List<Employee> employees;
	
	// Constructor loading employees from a file into a list
    public UserDatabase() {
        employees = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("src/main/java/application/users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) throw new IOException("Empty line in database file");
                
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    String username = parts[0].trim();
                    String id = parts[1].trim();
                    boolean isProjectLeader = Boolean.parseBoolean(parts[2].trim().toLowerCase()); // Convert to boolean
                    int activities = Integer.parseInt(parts[3]);
                    employees.add(new Employee(username, id, isProjectLeader, activities));
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Method to validate an employee by ID
    public boolean validateUser(String id) {
        for (Employee emp : employees) {
            if (emp.getID().equals(id)) {
                return true;
            }
        }
        return false;
    }
    
    // Method to retrieve role by id
    public boolean getIsProjectLeader(String id) {
        Employee emp = getEmployee(id);
        if (emp != null) {
            return emp.getProjectLeader();
        }
        return false;
    }
    
    // Method to retrieve the full employee object by ID
    public Employee getEmployee(String id) {
    	if (!validateUser(id)) return null;
        for (Employee emp : employees) {
            if (emp.getID().equals(id)) {
                return emp;
            }
        }
        return null;
    }
    
    public List<Employee> getEmployees() {
    	return employees;
    }

    public static List<Employee> getAvailableEmployees() {
    	List<Employee> availableEmployees = new ArrayList<Employee>();
    	for (Employee emp : employees) {
    		if (emp.getNumberOfActivities() <= 20)
    			availableEmployees.add(emp);
    	}
    	
    	return availableEmployees;
    }

    public ObservableList<String> getAllEmployeeNames() {
        ObservableList<String> names = FXCollections.observableArrayList();
        for (Employee employee : employees) {
            names.add(employee.getUsername());
        }
        return names;
    }


}
