package example.cucumber;

import application.Project;
import application.ProjectLeader;
import application.ProjectType;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProjectSteps {

    private ProjectLeader projectLeader;
    private Project project;
    private String projectName;
    private ProjectType projectType;
    private int projectId;

    @Given("I am logged in as a project leader with ID {string}")
    public void iAmLoggedInAsAProjectLeaderWithID(String employeeId) {
        ProjectLeader projectLeader = new ProjectLeader(employeeId);
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


    @And("there is a project with ID {int} and name {string}")
    public void thereIsAProjectWithIDAndName(int projectId, String projectName) {
        this.projectId = projectId;
        project = new Project(projectName, ProjectType.INTERNAL);
        project.setProjectID(projectId);
        ProjectLeader.getProjects().add(project);
    }


    @When("I assign the project leader with ID {string} to the project with ID {int}")
    public void iAssignTheProjectLeaderWithIDToTheProjectWithID(String employeeId, int projectId) {
        ProjectLeader newProjectLeader = new ProjectLeader(employeeId);
        Project projectToAssign = Project.findProjectByID(projectId);
        projectToAssign.assignProjectLeader(newProjectLeader);
    }

    @Then("the project with ID {int} should have the project leader with ID {string}")
    public void theProjectWithIDShouldHaveTheProjectLeaderWithID(Integer projectId, String employeeId) {
        Project projectToCheck = Project.findProjectByID(projectId);
        assertNotNull(projectToCheck.getProjectLeader());
        assertEquals(employeeId, projectToCheck.getProjectLeader().getEmployeeID());
    }
}

