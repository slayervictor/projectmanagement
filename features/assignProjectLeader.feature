Feature: Assign project leader  
	Description: Employees assigns a project leader to a project  
	Actor: Employee  

Scenario: Assigning an employee as the project leader  
	Given the project "Virtual Reality Expansion" exists  
	And the employee "JD01" exists  
	When assign "JD01" as the project leader of "Virtual Reality Expansion"  
	Then the employee "JD01" should be the project leader of the project

Scenario: Fail to assign project leader due to employee not existing  
	Given the project "Virtual Reality Expansion" exists  
	But the employee "xx" does not exist in the system  
	When trying to assign the employee "xx" as the project leader of "Virtual Reality Expansion"
	Then the system should display an error message "Employee not found"