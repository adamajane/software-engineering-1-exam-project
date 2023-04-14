Feature: Update activity date
  Description: An activity is updated with a new date
  Actor: Project Leader or Developer

  Scenario: Update the date of an existing activity
    Given an employee with ID "OBO"
    And a project with name "Project A" and type "INTERNAL"
    And the project has an activity with name "Test Activity"
    When the employee with ID "OBO" updates the date of the activity with name "Test Activity"
    Then the activity date is updated

