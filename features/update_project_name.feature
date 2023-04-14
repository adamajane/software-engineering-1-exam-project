Feature: Update Project Name
  Description: A project is updated with a new name
  Actor: Project Leader or Developer

  Scenario: Change the name of an existing project
    Given a project leader with ID "OBO"
    And there is a project with name "Old Project" and type "INTERNAL" and id 23100
    When the project leader updates the name of the project with ID 23100 to "New Project"
    Then the project name should be updated to "New Project"

