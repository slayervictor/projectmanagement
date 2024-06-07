Feature: Create Project
	Description: The employee creates a project
	Actors: Employee

Scenario: Create project 
	Given an employee "JD01" tries to create a project "New Project1" with 1 employees
	And there are enough employees 1 that have time
	Then the project is created 

Scenario: There arent enough employees to work on project, project cannot be created 
	Given an employee "JD01" tries to create a project "New Project2" with 10 employees
	And there arent enough employees 10 that have time
	Then the system should display an error message "Not enough available employees" 
	And the project is not created