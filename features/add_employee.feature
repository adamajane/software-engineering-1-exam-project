Feature: Add Employee
  Description: An employee is added to the system
  Actor: System Administrator

  Scenario: Add a new employee
    Given I have entered an employee ID "AVAJ"
    And I have specified the employee's role as "Developer"
    When I add the employee to the system
    Then the new employee should be added with the specified ID and role