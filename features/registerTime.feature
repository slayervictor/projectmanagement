Feature: Register time  
	Description: Employees register their time used on an activity 
	Actor: Employee  

Scenario: Successful time registration  
	Given the employee "AS02" is logged into the system 
	When the employee "AS02" selects the activity "code review"  
	And enters 2 hours for the activity for week 3
	Then the system adds 2 hours for the activity 

Scenario: Incorrect time entry  
	Given the employee "AS02" is logged into the system  
	When the employee "AS02" selects the activity "code review" 
	And enters -2 hours for the activity for week 3
	Then the system should display an error message "Invalid time registration"