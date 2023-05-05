package cucumber;

import application.Project;
import application.ProjectLeader;
import application.ProjectType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class UpdateProjectSteps {

    private ProjectLeader projectLeader;
    private Project project;

    @Given("a project leader with ID {string}")
    public void aProjectLeaderWithID(String employeeId) {
        projectLeader = new ProjectLeader(employeeId);
    }

    @Given("there is a project with name {string} and type {string} and id {int}")
    public void aProjectWithNameAndType(String projectName, String projectType, int id) {
        project = new Project(projectName, ProjectType.valueOf(projectType.toUpperCase()));
        project.setProjectID(id); // Set the project ID manually
        ProjectLeader.getProjects().add(project);
    }

    @When("the project leader updates the name of the project with ID {int} to {string}")
    public void theProjectLeaderUpdatesTheNameOfTheProjectWithIDTo(int projectId, String newName) {
        ProjectLeader.updateProjectName(projectId, newName);
    }

    @Then("the project name should be updated to {string}")
    public void theProjectNameShouldBeUpdatedTo(String newName) {
        Project updatedProject = Project.findProjectByID(project.getProjectID());
        assertEquals(newName, updatedProject.getProjectName());
    }



}
