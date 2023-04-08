/*package example.junit;

import application.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static application.Employee.addEmployee;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {
    private Employee developer;
    private Employee projectLeader;
    private Activity activity;

    @BeforeEach
    void setUp() {
        developer = new Developer("DEV001");
        projectLeader = new ProjectLeader("PL001");
        activity = new Activity("Test Activity", 10, 2023, 15, 2023, 16);
    }

    @BeforeEach
    void clearEmployeeList() {
        Employee.getEmployees().clear();
    }


    @Test
    void testAddEmployee() {
        // Set up user input simulation
        ByteArrayInputStream in = new ByteArrayInputStream("DEV002\nNo\n".getBytes());
        System.setIn(in);

        // Call the method being tested
        addEmployee();

        // Verify that the list of employees has the expected size and properties
        ArrayList<Employee> employees = Employee.getEmployees();
        assertEquals(1, employees.size());
        assertEquals("DEV002", employees.get(0).getEmployeeID());
        assertEquals("Developer", employees.get(0).getRole());

        // Set up another user input simulation
        in = new ByteArrayInputStream("PL002\nYes\n".getBytes());
        System.setIn(in);

        // Call the method again with different input
        addEmployee();

        // Verify that the list of employees has the expected size and properties
        employees = Employee.getEmployees();
        assertEquals(2, employees.size());
        assertEquals("DEV002", employees.get(0).getEmployeeID());
        assertEquals("Developer", employees.get(0).getRole());
        assertEquals("PL002", employees.get(1).getEmployeeID());
        assertEquals("Project Leader", employees.get(1).getRole());
    }

    void findEmployeeById() {
        Employee devEmployee = new Developer("DEV002");
        Employee plEmployee = new ProjectLeader("PL002");

        Employee.getEmployees().add(devEmployee);
        Employee.getEmployees().add(plEmployee);

        Employee foundEmployee = Employee.findEmployeeById("DEV002");
        assertNotNull(foundEmployee);
        assertEquals("DEV002", foundEmployee.getEmployeeID());
        assertEquals("Developer", foundEmployee.getRole());

        foundEmployee = Employee.findEmployeeById("PL002");
        assertNotNull(foundEmployee);
        assertEquals("PL002", foundEmployee.getEmployeeID());
        assertEquals("Project Leader", foundEmployee.getRole());

        assertNull(Employee.findEmployeeById("INVALID_ID"));
    }

    @Test
    void testCreateActivity() {
        // Set up user input simulation
        ByteArrayInputStream in = new ByteArrayInputStream("1\nProject A Activity\n100\n2023\n15\n2023\n18\n".getBytes());
        System.setIn(in);

        // Create some employees and projects
        Employee.getEmployees().add(new Developer("DEV001"));
        Project projectA = new Project("Project A", ProjectType.INTERNAL);
        projectA.setProjectID(1); // Set the projectID
        ProjectLeader.getProjects().add(projectA);

        // Call the method being tested
        Employee.createActivity(new Scanner(System.in));

        // Verify that the project activity list has been updated as expected
        Project project = Project.findProjectByID(1);
        assertNotNull(project);
        assertEquals(1, project.getActivities().size());
        Activity activity = project.getActivities().get(0);
        assertEquals("Project A Activity", activity.getActivityName());
        assertEquals(100, activity.getBudgetedHours());
        assertEquals(2023, activity.getStartYear());
        assertEquals(15, activity.getStartWeek());
        assertEquals(2023, activity.getEndYear());
        assertEquals(18, activity.getEndWeek());

        // Set up user input simulation for a non-existing project ID
        in = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(in);

        // Call the method again with a non-existing project ID
        Employee.createActivity(new Scanner(System.in));

        // Verify that the method does not add any activity to the project list
        project = Project.findProjectByID(2);
        assertNull(project);
    }



    @Test
    void registerTime() {
        developer.addActivity(activity);
        activity.assignEmployee(developer);
        developer.registerTime(activity, 4);

        double registeredHours = developer.getRegisteredHours(LocalDate.now());
        assertEquals(4, registeredHours, 0.5);

        developer.registerTime(activity, 2);
        registeredHours = developer.getRegisteredHours(LocalDate.now());
        assertEquals(6, registeredHours, 0.5);
    }

    @Test
    void registerTimeForUnassignedActivity() {
        developer.registerTime(activity, 4);

        double registeredHours = developer.getRegisteredHours(LocalDate.now());
        assertEquals(0, registeredHours, 0.5);
    }

    @Test
    void getActivitiesInWeek() {
        Activity activity1 = new Activity("Activity1", 10, 2023, 15, 2023, 16);
        Activity activity2 = new Activity("Activity2", 20, 2023, 16, 2023, 17);
        Activity activity3 = new Activity("Activity3", 30, 2023, 15, 2023, 16);

        developer.addActivity(activity1);
        developer.addActivity(activity2);
        developer.addActivity(activity3);

        assertEquals(2, developer.getActivitiesInWeek(2023, 15));
        assertEquals(1, developer.getActivitiesInWeek(2023, 16));
    }
}

*/