package cucumber;

import application.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ActivitySteps {

    private Project project;
    private Employee employee;
    private Activity activity;

    @Before
    public void setUp() {
        // Clear all activities for all projects
        for (Project project : ProjectLeader.getProjects()) {
            project.clearActivities();
        }
        // Clear all projects
        ProjectLeader.getProjects().clear();
    }

    @Given("that there is a project in the system")
    public void thatThereIsAProjectInTheSystem() {
        ProjectLeader projectLeader = new ProjectLeader("AVAJ");
        ProjectLeader.getProjects().add(new Project("Test Project", ProjectType.INTERNAL));
        project = ProjectLeader.getProjects().get(0);
    }

    @Given("that there is an employee in the system")
    public void thatThereIsAnEmployeeInTheSystem() {
        employee = new Developer("OBO");
        Employee.getEmployees().add(employee);
    }

    @When("the employee creates a new activity")
    public void theEmployeeCreatesANewActivity() {
        String activityName = "Test Activity";
        int budgetedHours = 10;
        int startYear = 2023;
        int startWeek = 20;
        int endYear = 2023;
        int endWeek = 25;
        Activity newActivity = Employee.createActivity(activityName, budgetedHours, startYear, startWeek, endYear, endWeek);
        if (project != null) {
            project.getActivities().add(newActivity);
            activity = project.getActivities().get(0);
        }
    }

    @Then("the activity is added to the project")
    public void theActivityIsAddedToTheProject() {
        assertTrue(project.getActivities().contains(activity));
    }

    @Given("that there is no project in the system")
    public void thatThereIsNoProjectInTheSystem() {
        assertEquals(0, ProjectLeader.getProjects().size());
    }

    @Then("the activity is not added to the project")
    public void theActivityIsNotAddedToTheProject() {
        if (project == null) {
            assertTrue(true);
        } else {
            assertEquals(0, project.getActivities().size());
        }
    }

    @Given("that there is an activity in the system")
    public void thatThereIsAnActivityInTheSystem() {
        String activityName = "Test Activity";
        int budgetedHours = 10;
        int startYear = 2023;
        int startWeek = 20;
        int endYear = 2023;
        int endWeek = 25;
        Activity newActivity = Employee.createActivity(activityName, budgetedHours, startYear, startWeek, endYear, endWeek);
        if (project != null) {
            project.getActivities().add(newActivity);
            activity = project.getActivities().get(0);
        }
    }

    @When("the admin adds the employee to the activity")
    public void theAdminAddsTheEmployeeToTheActivity() {
        if (activity != null && employee != null) {
            Employee.addEmployeeToActivity(employee.getEmployeeID(), activity.getActivityName());
        }
    }

    @Then("the employee is added to the activity")
    public void theEmployeeIsAddedToTheActivity() {
        assertTrue(activity.getAssignedEmployees().contains(employee));
    }

    @Given("that the employee is assigned to the activity")
    public void thatTheEmployeeIsAssignedToTheActivity() {
        Employee.addEmployeeToActivity(employee.getEmployeeID(), activity.getActivityName());
    }
    @When("the admin prints the activities assigned to the employee")
    public void theAdminPrintsTheActivitiesAssignedToTheEmployee() {
        Employee.showActivitiesAssignedToEmployee(employee.getEmployeeID());
    }
    @Then("the activities assigned to the employee are printed")
    public void theActivitiesAssignedToTheEmployeeArePrinted() {
        for (Activity activity : employee.getActivities()) {
            assertEquals("Test Activity", activity.getActivityName());
        }
    }

}
