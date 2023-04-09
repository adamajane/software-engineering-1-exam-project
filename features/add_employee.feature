Feature: Add Employee
  Description: An employee is added to the system
  Actor: System Administrator

  Scenario: Add a new employee
    Given I have entered an employee ID "AVAJ"
    And I have specified the employee's role as "Developer"
    When I add the employee to the system
    Then the new employee should be added with the specified ID and role

  Scenario: Add an employee with an invalid ID
    Given I have entered an employee ID "1234"
    And I have specified the employee's role as "Developer"
    When I attempt to add the employee to the system
    Then I should receive an error message stating that the employee ID must only contain letters

  Scenario: Add an employee with an existing ID
    Given an employee with ID "AVAJ" already exists in the system
    And I have entered an employee ID "AVAJ"
    And I have specified the employee's role as "Developer"
    When I attempt to add the employee to the system
    Then I should receive an error message stating that the employee with ID "AVAJ" already exists