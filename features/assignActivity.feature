Feature: Assign activities  
    Description: Project leaders can assign activities to employees  
    Actor: Project leader  

Scenario: Project leader assigns an activity to an employee  
    Given the project leader "JD01" is logged in  
    And the employee "AS02" exists  
    And the employee "AS02" has less than 20 activities assigned  
    And the project "Virtual Reality Expansion" has an activity "User Interface Design"  
    When the project leader "JD01" assigns "User Interface Design" to "AS02"  
    Then "AS02" should be assigned to "User Interface Design" in "Virtual Reality Expansion"  

Scenario: Attempt to assign an activity to a non-existent employee  
    Given the project leader "JD01" is logged in  
    And the project "Virtual Reality Expansion" has an activity "User Interface Design"  
    But the employee "xx" does not exist in the system  
    When the project leader "JD01" tries to assign "User Interface Design" to "xx"  
    Then the system should display an error message "Employee not found"  
    And "User Interface Design" should not be assigned to employee "xx"