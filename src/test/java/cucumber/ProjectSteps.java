package cucumber;

import application.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static application.Project.getTimeConsumptionReport;
import static application.ProjectLeader.assignProjectLeader;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectSteps {

    private ProjectLeader projectLeader;
    private Project project;
    private String projectName;
    private ProjectType projectType;
    private int projectID;
    private Activity activity;

    @Before
    public void clearEmployees() {
        Employee.getEmployees().clear();
        ProjectLeader.getProjects().clear();
    }

    @Given("I am logged in as a project leader with ID {string}")
    public void iAmLoggedInAsAProjectLeaderWithID(String employeeID) {
        projectLeader = new ProjectLeader(employeeID);
    }

    @When("I create a project with name {string} and type {string}")
    public void iCreateAProjectWithNameAndType(String projectName, String projectType) {
        this.projectName = projectName;
        this.projectType = ProjectType.valueOf(projectType.toUpperCase());
        ProjectLeader.createProject(projectName, projectType);
        project = new Project(projectName, this.projectType);
        ProjectLeader.getProjects().add(project);
    }

    @Then("the project should be created with name {string} and type {string}")
    public void theProjectShouldBeCreatedWithNameAndType(String projectName, String projectType) {
        assertNotNull(project);
        assertEquals(projectName, project.getProjectName());
        assertEquals(ProjectType.valueOf(projectType.toUpperCase()), project.getProjectType());
    }

    @Given("that there's a project leader with employee ID {string}")
    public void thatThereSAProjectLeaderWithEmployeeID(String employeeID) {
        projectLeader = new ProjectLeader(employeeID);
        Employee.getEmployees().add(projectLeader);
    }

    @And("there is a project with ID {int} and name {string}")
    public void thereIsAProjectWithIDAndName(int projectID, String projectName) {
        this.projectID = projectID;
        project = new Project(projectName, ProjectType.INTERNAL);
        project.setProjectID(projectID);
        ProjectLeader.getProjects().add(project);
    }

    @When("the project with ID {int} is assigned to {string}")
    public void theProjectWithIDIsAssignedTo(int projectID, String employeeID) {
        assignProjectLeader(projectID, employeeID);
    }

    @Then("the project with ID {int} should have the project leader with ID {string}")
    public void theProjectWithIDShouldHaveTheProjectLeaderWithID(int projectID, String employeeID) {
        Project projectToCheck = Project.findProjectByID(projectID);
        assertNotNull(projectToCheck.getProjectLeader());
        assertEquals(employeeID, projectToCheck.getProjectLeader().getEmployeeID());
    }

    @Given("that there is a project in the system with ID {int} with the name {string}")
    public void thatThereIsAProjectInTheSystemWithID(int projectID, String projectName) {
        this.projectID = projectID;
        project = new Project(projectName, ProjectType.INTERNAL);
        project.setProjectID(projectID);
        ProjectLeader.getProjects().add(project);
    }

    @And("that there is an activity added to the project")
    public void thatThereIsAnActivityAddedToTheProject() {
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

    @When("the employee generates a time consumption for the project")
    public void theEmployeeGeneratesATimeConsumptionForTheProject() {
        getTimeConsumptionReport(projectID);
    }

    @Then("then a time consumption report is generated for the project")
    public void thenATimeConsumptionReportIsGeneratedForTheProject() {
        String report = project.makeTimeConsumptionReport();

        // Check if the report contains the key elements
        assertTrue(report.contains("Time Consumption Report for Project: " + projectID + " - " + project.getProjectName()));
        assertTrue(report.contains("Activity: " + activity.getActivityName()));
        assertTrue(report.contains("Budgeted Hours: " + activity.getBudgetedHours() + " hours"));
        assertTrue(report.contains("Start Year: " + activity.getStartYear()));
        assertTrue(report.contains("Start Week: " + activity.getStartWeek()));
        assertTrue(report.contains("End Year: " + activity.getEndYear()));
        assertTrue(report.contains("End Week: " + activity.getEndWeek()));
        assertTrue(report.contains("Employee Time Spent:"));
        assertTrue(report.contains("Total Time Spent on Project:"));
        assertTrue(report.contains("Expected Remaining Working Hours on Project:"));
    }


}

