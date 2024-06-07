Feature: Log out  
    Description: Employee logs out of system  
    Actor: Employee  

Scenario: Employee logs out  
    Given the employee with ID "JD01" is logged in  
    When the employee logs out  
    Then "JD01" is logged out of the system