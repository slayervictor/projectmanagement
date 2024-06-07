// Written by Maria
package whitebox;

import org.junit.jupiter.api.Test;

import application.Activity;
import application.Employee;
import application.Project;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTests {
	
	Employee emp1 = new Employee("jdoe","JD01",true,10);
	Employee emp2 = new Employee("bwayne","BW03",false,20);
	Project project = new Project("New project", 1, 2, false);
	Activity newActivity = new Activity("Design", 10, 0);
	
    @Test
    void testAssignActivityUnderLimit() {
        assertDoesNotThrow(() -> emp1.assignActivity(newActivity, emp1, project)); 
    }
    
    @Test
    void testAssignActivityAtLimit() {
        AssertionError exception = assertThrows(AssertionError.class, () -> emp1.assignActivity(newActivity, emp2, project));
        assertEquals("Employee not available", exception.getMessage());
    }
    
    @Test
    void testAssignNullActivity() {
        AssertionError exception = assertThrows(AssertionError.class, () -> emp1.assignActivity(null, emp2, project));
        assertEquals("Invalid Activity or Project", exception.getMessage());
    }
 
    @Test
    void testAssignActivityNullProject() {
    	Throwable exception = assertThrows(AssertionError.class, () -> emp1.assignActivity(newActivity, emp2, null));
        assertEquals("Invalid Activity or Project", exception.getMessage());
    }
    
    @Test
    void testAssignActivityNonLeader() {
    	AssertionError exception = assertThrows(AssertionError.class, () -> emp2.assignActivity(newActivity, emp1, project));
        assertEquals("Has to be project leader to assign activities", exception.getMessage());
    }
    
    @Test
    void testCreateProjectSufficientEmployees() throws Exception {
    	Project project = emp1.createProject("Project1", 1, 2, 3); // Needed 3, available 5

        // Assert - Check postconditions
        assertNotNull(project, "Project should not be null when preconditions are met");
        assertEquals("Project1", project.getProjectName(), "Project name should match the input");
    }
    
    @Test
    void testCreateProjectInsufficientEmployees() {
    	AssertionError exception = assertThrows(AssertionError.class, () -> {
            emp1.createProject("Project1", 1, 2, 20); // Needed 20, available 5
        }, "Expected an Exception to be thrown when there are not enough available employees");

        assertEquals("Not enough available employees", exception.getMessage(), "Error message should indicate insufficient employees");
    }
}

