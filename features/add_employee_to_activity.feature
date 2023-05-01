Feature: Add employee to activity
  Description: An employee is to an activity
  Actor: System administrator

  Scenario: Add employee to an activity
    Given that there is an employee in the system
    And that there is a project in the system
    And that there is an activity in the system
    When the admin adds the employee to the activity
    Then the employee is added to the activity
