package cucumber;

import application.Employee;
import application.Project;
import application.ProjectLeader;
import application.ProjectType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Before;

import static application.ProjectLeader.assignProjectLeader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectSteps {

    private ProjectLeader projectLeader;
    private Project project;
    private String projectName;
    private ProjectType projectType;
    private int projectID;

    @Before
    public void clearEmployees() {
        Employee.getEmployees().clear();
    }

    @Given("I am logged in as a project leader with ID {string}")
    public void iAmLoggedInAsAProjectLeaderWithID(String employeeID) {
        projectLeader = new ProjectLeader(employeeID);
    }

    @When("I create a project with name {string} and type {string}")
    public void iCreateAProjectWithNameAndType(String projectName, String projectType) {
        this.projectName = projectName;
        this.projectType = ProjectType.valueOf(projectType.toUpperCase());
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

}

