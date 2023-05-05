Feature: Make an get time consumption report
  Description: A consumption report is generated for a report
  Actor: System Administrator

  Scenario: Make a time consumption report for a project
    Given that there is a project in the system with ID 23001 with the name "Test project"
    And that there is an employee in the system
    And that there is an activity added to the project
    When the employee generates a time consumption for the project
    Then then a time consumption report is generated for the project