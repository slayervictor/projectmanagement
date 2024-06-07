Feature: Modify project  
    Description: Project leaders can modify project details  
    Actor: Project leader  

Scenario: Changing the end date of a project  
    Given the project "Virtual Reality Expansion" with the end week 30 exists  
    And the project leader "JD01" is logged in  
    When the project leader "JD01" tries to change the end week of the project to a new week 40  
    Then the end week of the project should be 40  

Scenario: Fail to change the end date of a project due to past date  
    Given the project "Virtual Reality Expansion" with the end week 30 exists   
    And the project leader "JD01" is logged in  
    When the project leader "JD01" tries to change the end week of the project to a new week 20  
    Then the system should display an error message "End week cannot be in the past"