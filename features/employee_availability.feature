Feature: Set availability of employee
  Description: An employee sets their availability
  Actor: Employee

  Scenario: Employee sets themselves as available
    Given an employee with ID "AVAJ" and role "Developer" exists
    When they set themselves as available
    Then they are available

  Scenario: Employee sets themselves as unavailable
    Given an employee with ID "AVAJ" and role "Developer" exists
    When they set themselves as unavailable
    Then they are unavailable