Feature: Login
	Description: The employee logs in to the system
	Actors: Employee

Scenario: User logs in to the system using valid login details
	Given the user opens the application
	When the user enters their ID "JD01"
  Then the system redirects the user to the home page

Scenario: User inputs invalid credentials
	Given the user opens the application
	When the user enters invalid ID "nonExistingID"
  Then the system displays an error regarding the invalid credential