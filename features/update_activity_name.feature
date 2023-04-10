Feature: Update Activity Name

  As an employee
  I want to update the name of an activity
  So that the activity name reflects its current purpose

  Scenario: Update the name of an existing activity
    Given an employee with ID "OBO"
    And a project with name "Project A" and type "INTERNAL"
    And the project has an activity with name "Old Activity"
    When the employee updates the activity name from "Old Activity" to "New Activity"
    Then the activity name should be updated to "New Activity"

