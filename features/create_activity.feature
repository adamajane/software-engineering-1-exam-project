Feature: Create and add activity
  Description: An activity is created and added to a project
  Actor: Project Leader or Developer

  Scenario: Create a new activity
    Given that there is a project in the system
    And that there is an employee in the system
    When the employee creates a new activity
    Then the activity is added to the project

  Scenario: Create a new activity when there is no project
    Given that there is no project in the system
    And that there is an employee in the system
    When the employee creates a new activity
    Then the activity is not added to the project
