package cucumber;

import application.Activity;
import application.Developer;
import application.Employee;
import application.ProjectLeader;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeSteps {

    private String employeeID;
    private String role;
    private Employee employee;
    private String errorMessage;
    private Activity activity;
    private double hours = 0;
    private boolean registerResult;

    @Given("I have entered an employee ID {string}")
    public void iHaveEnteredAnEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    @And("I have specified the employee's role as {string}")
    public void iHaveSpecifiedTheEmployeeSRole(String role) {
        this.role = role;
    }

    @When("I add the employee to the system")
    public void iAddTheEmployeeToTheSystem() {
        if (!Employee.isValidEmployeeID(employeeID)) {
            throw new IllegalArgumentException("Invalid employee ID. Try again.");
        }

        if (Employee.findEmployeeByID(employeeID) != null) {
            throw new IllegalArgumentException("Employee with ID " + employeeID + " already exists. Try again.");
        }

        if ("Project Leader".equalsIgnoreCase(role)) {
            employee = new ProjectLeader(employeeID);
        } else {
            employee = new Developer(employeeID);
        }

        Employee.addEmployee(employeeID, role);
    }

    @Then("the new employee should be added with the specified ID and role")
    public void theNewEmployeeShouldBeAddedWithTheSpecifiedIDAndRole() {
        Employee foundEmployee = Employee.findEmployeeByID(employeeID);
        assertNotNull(foundEmployee);
        assertEquals(employeeID, foundEmployee.getEmployeeID());
        assertEquals(role, foundEmployee.getRole());
    }

    @When("I attempt to add the employee to the system")
    public void iAttemptToAddTheEmployeeToTheSystem() {
        try {
            if (!Employee.isValidEmployeeID(employeeID)) {
                throw new IllegalArgumentException("Invalid employee ID. Try again.");
            }

            if (Employee.findEmployeeByID(employeeID) != null) {
                throw new IllegalArgumentException("Employee with ID " + employeeID + " already exists. Try again.");
            }

            if ("Project Leader".equalsIgnoreCase(role)) {
                employee = new ProjectLeader(employeeID);
            } else {
                employee = new Developer(employeeID);
            }
            Employee.getEmployees().add(employee);
        } catch (IllegalArgumentException e) {
            errorMessage = e.getMessage();
        }
    }

    @Then("I should receive an error message stating that the employee ID must only contain letters")
    public void iShouldReceiveAnErrorMessageStatingThatTheEmployeeIDMustOnlyContainLetters() {
        assertEquals("Invalid employee ID. Try again.", errorMessage);
    }

    @Given("an employee with ID {string} already exists in the system")
    public void anEmployeeWithIDAlreadyExistsInTheSystem(String existingEmployeeID) {
        Employee existingEmployee = new Developer(existingEmployeeID);
        Employee.getEmployees().add(existingEmployee);
    }

    @Then("I should receive an error message stating that the employee with ID {string} already exists")
    public void iShouldReceiveAnErrorMessageStatingThatTheEmployeeWithIDAlreadyExists(String existingEmployeeID) {
        assertEquals("Invalid employee ID. Try again.", errorMessage);
    }

    @Then("I should receive an error message stating that the employee ID can't be longer than {int} characters")
    public void iShouldReceiveAnErrorMessageStatingThatTheEmployeeIDCantBeLongerThanCharacters(int maxLength) {
        assertEquals("Invalid employee ID. Try again.", errorMessage);
    }

    @Given("an employee with ID {string} and role {string} exists")
    public void anEmployeeWithIDAndRoleExists(String employeeID, String role) {
        if ("Project Leader".equalsIgnoreCase(role)) {
            employee = new ProjectLeader(employeeID);
        } else {
            employee = new Developer(employeeID);
        }
        Employee.getEmployees().add(employee);
    }

    @When("they set themselves as available")
    public void theySetThemselvesAsAvailable() {
        employee.markAsAvailable();
    }

    @Then("they are available")
    public void theyAreAvailable() {
        assertTrue(employee.isAvailable());
    }

    @When("they set themselves as unavailable")
    public void theySetThemselvesAsUnavailable() {
        employee.markAsUnavailable();
    }

    @Then("they are unavailable")
    public void theyAreUnavailable() {
        assertFalse(employee.isAvailable());
    }
}
