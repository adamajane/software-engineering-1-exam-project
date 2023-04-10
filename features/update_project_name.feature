Feature: Update Project Name

  As a project leader
  I want to change the name of a project
  So that the project name reflects its current purpose

  Scenario: Change the name of an existing project
    Given a project leader with ID "OBO"
    And there is a project with name "Old Project" and type "INTERNAL" and id 23100
    When the project leader updates the name of the project with ID 23100 to "New Project"
    Then the project name should be updated to "New Project"
