Feature: Delete Project 
	Description: The employee deletes a project 
	Actors: Employee 

Scenario: Delete project before the scheduled start date  
	Given a project titled "Old Project" scheduled to start in week 20 exists
	And the week is 15  
	When the user attempts to delete the project "Old Project"  
	Then the project "Old Project" should be deleted  

Scenario: Attempt to delete a project after the scheduled start date  
	Given a project titled "Virtual Reality Expansion" scheduled to start in week 12 exists  
	And the week is 15  
	When the user attempts to delete the project "Virtual Reality Expansion"  
	Then the system should display an error message "Project cannot be deleted after it has started"  
	And the project "Virtual Reality Expansion" should not be deleted