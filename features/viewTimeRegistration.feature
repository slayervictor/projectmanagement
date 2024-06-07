Feature: View time registration  
	Description: Employees can review the recorded time of their time registrations  
	Actor: Employee  

Scenario: Employee checks their time registrations  
	Given the employee "JD01" is logged into the system  
	And the employee "JD01" has registered 6.0 hours for activity "User Interface Design" in week 10
	When the employee requests to view their time registrations for week 10
	Then the time registrations for the employee shows the registered time including 6 hours for "User Interface Design" in week 10  

Scenario: View time registrations with no registrations  
	Given the employee "JD01" is logged into the system
	And the employee "JD01" has no registered time for week 11 for activity "User Interface Design"
	When the employee requests to view their time registrations for week 11  
	Then the system should display no time registrations for week 11