Feature: Create project activities 
    Description: Project leaders can add new activities to projects  
    Actor: Project leader  

Scenario: Adding new activities to a project  
    Given the project "Virtual Reality Expansion" exists  
    And the project leader "JD01" is logged in  
    When project leader adds an activity "User Interface Design" to the project "Virtual Reality Expansion" with an estimated time of 40 hours 
    Then the activity "User Interface Design" should be listed under the project  

Scenario: Fail to add activities to a project due to missing estimated time  
    Given the project "Virtual Reality Expansion" exists  
    And the project leader "JD01" is logged in  
    When project leader tries to add an activity "Backend Integration" to the project without estimated time  
    Then the system should display an error message "Estimated time is required for activity creation"