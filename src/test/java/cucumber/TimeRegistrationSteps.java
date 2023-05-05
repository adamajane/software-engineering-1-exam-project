package cucumber;

import application.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TimeRegistrationSteps {

    private Employee employee;
    private Project project;
    private Activity activity;
    private boolean registerResult;
    private TimeRegistration timeRegistration;

    @And("the employee is assigned to an activity")
    public void thatThereIsAnEmployeeInTheSystemWithAnActivity() {
        employee = new Developer("AVAJ");
        activity = new Activity("Test Activity", 20, 2023, 1, 2023, 2);
        activity.assignEmployee(employee);
    }

    @When("the employee registers time for the activity")
    public void theEmployeeRegistersTimeForTheActivity() {
        registerResult = employee.registerTime(activity, 10.0);
        //TimeRegistration.registerTimeForEmployee("AVAJ", "Test Activity", 10.0);
    }

    @Then("the time should be registered for the employee")
    public void theTimeShouldBeRegisteredForTheEmployee() {
        assertTrue(registerResult);
        LocalDate currentDate = LocalDate.now();
        assertEquals(10.0, employee.getRegisteredHours(currentDate), 0.001);
    }
// TODO: This feature file
    /*  Scenario: Employee updates time
    Given that there is an employee in the system
    And that there is a project in the system
    And that there is an activity in the system
    And the employee is assigned to an activity
    When the employee updates hours and date
    Then then the time should be updated for the employee*/

    /*@When("the employee updates hours and date")
    public void theEmployeeUpdatesHoursAndDate() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        for (TimeRegistration timeRegistrations : activity.getTimeRegistrations()) {
            if (timeRegistrations.getEmployee().getEmployeeID().equals(employee.getEmployeeID())) {
                timeRegistrations.updateHours(12.0);
                timeRegistrations.updateDate(newDate);
                timeRegistration = timeRegistrations;
                break;
            }
        }
    }

    @Then("then the time should be updated for the employee")
    public void thenTheTimeShouldBeUpdatedForTheEmployee() {
        assertEquals(12.0, timeRegistration.getHours(), 0.001);
        assertEquals(LocalDate.now().plusDays(1), timeRegistration.getDate());
    }*/

}
