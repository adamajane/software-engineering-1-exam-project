Feature: Add activity
  Description: An activity is added to a project
  Actor: Project Leader or Developer

  Scenario: Create a new activity
    Given that there is a project in the system
    And there is an employee in the system
    When the employee creates a new activity
    Then the activity is added to the project
