package example.cucumber;

import application.Developer;
import application.Employee;
import application.ProjectLeader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class EmployeeSteps {

    private String employeeID;
    private String role;
    private Employee employee;

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
        if ("Project Leader".equalsIgnoreCase(role)) {
            employee = new ProjectLeader(employeeID);
        } else {
            employee = new Developer(employeeID);
        }
        Employee.getEmployees().add(employee);
    }

    @Then("the new employee should be added with the specified ID and role")
    public void theNewEmployeeShouldBeAddedWithTheSpecifiedIDAndRole() {
        Employee foundEmployee = Employee.findEmployeeById(employeeID);
        assertNotNull(foundEmployee);
        assertEquals(employeeID, foundEmployee.getEmployeeID());
        assertEquals(role, foundEmployee.getRole());
    }

}
