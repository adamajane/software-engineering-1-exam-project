package cucumber;

import application.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

// Author: Adam Ajane
public class TimeRegistrationSteps {

    private Employee employee;
    private Activity activity;
    private boolean registerResult;

    @And("the employee is assigned to an activity")
    public void thatThereIsAnEmployeeInTheSystemWithAnActivity() {
        employee = new Developer("AVAJ");
        activity = new Activity("Test Activity", 20, 2023, 1, 2023, 2);
        Employee.addEmployee(employee.getEmployeeID(), employee.getRole());
        activity.assignEmployee(employee);
    }

    @When("the employee registers time for the activity")
    public void theEmployeeRegistersTimeForTheActivity() {
        TimeRegistration.registerTimeForEmployee(employee.getEmployeeID(), activity.getActivityName(), 10.0);
        registerResult = employee.registerTime(activity, 10.0);
    }

    @Then("the time should be registered for the employee")
    public void theTimeShouldBeRegisteredForTheEmployee() {
        assertTrue(registerResult);
        LocalDate currentDate = LocalDate.now();
        assertEquals(10.0, employee.getRegisteredHours(currentDate), 0.001);
    }
}
