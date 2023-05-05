Feature: Assigning a project leader to a project
  Description: A project leader is assigned to a project
  Actor: Project Leader

  Scenario: Assign a project leader to a project
    Given that there's a project leader with employee ID "AVAJ"
    And there is a project with ID 23001 and name "Test Project"
    When the project with ID 23001 is assigned to "AVAJ"
    Then the project with ID 23001 should have the project leader with ID "AVAJ"