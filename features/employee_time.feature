Feature: Show and register time for employee
  Description: An employee registers time
  Actor: Employee

  Scenario: Employee registers time for an assigned activity
    Given that there is an employee in the system
    And that there is a project in the system
    And that there is an activity in the system
    And the employee is assigned to an activity
    When the employee registers time for the activity
    Then the time should be registered for the employee