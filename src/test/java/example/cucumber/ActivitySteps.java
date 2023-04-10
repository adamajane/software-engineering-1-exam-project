package example.cucumber;

import application.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;


import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ActivitySteps {

    private Project project;
    private Employee employee;
    private Activity activity;
    private Scanner scanner = new Scanner(System.in);

    @Before
    public void setUp() {

        if (project != null) {
            project.clearActivities();
        }

        ProjectLeader.getProjects().clear();
    }

    @Given("that there is a project in the system")
    public void thatThereIsAProjectInTheSystem() {
        ProjectLeader projectLeader = new ProjectLeader("AVAJ");
        ProjectLeader.getProjects().add(new Project("Test Project", ProjectType.INTERNAL));
        project = ProjectLeader.getProjects().get(0);
    }

    @Given("there is an employee in the system")
    public void thereIsAnEmployeeInTheSystem() {
        employee = new Developer("OBO");
        Employee.getEmployees().add(employee);
    }

    @When("the employee creates a new activity")
    public void theEmployeeCreatesANewActivity() {
        int projectId = Project.getProjectID();
        String input = projectId + "\nTest Activity\n10\n2023\n20\n2023\n25";
        scanner = new Scanner(input);
        Employee.createActivity(scanner);
        activity = Project.getActivities().get(0);
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
        assertEquals(0, Project.getActivities().size());
    }
}
