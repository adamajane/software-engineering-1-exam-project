package example.cucumber;
import application.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

public class UpdateActivityNameSteps {

    private Employee employee;
    private Project project;
    private Activity activity;

    @Given("an employee with ID {string}")
    public void anEmployeeWithID(String employeeId) {
        employee = new Developer(employeeId);
    }

    @Given("a project with name {string} and type {string}")
    public void AProjectWithNameAndType(String projectName, String projectType) {
        project = new Project(projectName, ProjectType.valueOf(projectType.toUpperCase()));
        ProjectLeader.getProjects().add(project);
    }

    @Given("the project has an activity with name {string}")
    public void theProjectHasAnActivityWithName(String activityName) {
        int startYear = 2023;
        int startWeek = 1;
        int endYear = 2023;
        int endWeek = 52;
        int budgetedHours = 100;

        activity = new Activity(activityName, budgetedHours, startYear, startWeek, endYear, endWeek);
        project.getProjectActivities().add(activity);
    }

    @When("the employee updates the activity name from {string} to {string}")
    public void theEmployeeUpdatesTheActivityName(String oldActivityName, String newActivityName) {
        employee.updateActivityName(oldActivityName, newActivityName);
    }

    @Then("the activity name should be updated to {string}")
    public void theActivityNameShouldBeUpdatedTo(String newActivityName) {
        assertEquals(newActivityName, activity.getActivityName());
    }
}
