Feature: Update activity name
  Description: An activity is updated with a new name
  Actor: Project Leader or Developer

  Scenario: Update the name of an existing activity
    Given an employee with ID "OBO"
    And a project with name "Project A" and type "INTERNAL"
    And the project has an activity with name "Old Activity"
    When the employee updates the activity name from "Old Activity" to "New Activity"
    Then the activity name should be updated to "New Activity"

