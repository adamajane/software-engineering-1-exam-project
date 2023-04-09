Feature: Add Project
  Description: A project is added to the system
  Actor: Project Leader

  Scenario: Create a new project
    Given I am logged in as a project leader with ID "PL001"
    When I create a project with name "Project A" and type "INTERNAL"
    Then the project should be created with name "Project A" and type "INTERNAL"

  Scenario: Assign a project leader to a project
    Given I am logged in as a project leader with ID "PL001"
    And there is a project with ID 1 and name "Project Alpha"
    When I assign the project leader with ID "PL002" to the project with ID 1
    Then the project with ID 1 should have the project leader with ID "PL002"