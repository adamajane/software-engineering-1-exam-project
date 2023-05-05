Feature: Create and add project
  Description: A project is added to the system
  Actor: Project Leader

  Scenario: Create a new project
    Given I am logged in as a project leader with ID "AVAJ"
    When I create a project with name "Project A" and type "INTERNAL"
    Then the project should be created with name "Project A" and type "INTERNAL"
