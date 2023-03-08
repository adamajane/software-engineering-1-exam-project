import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String name;
    private Employee projectManager;
    private List<Employee> assignedEmployees;
    private int budgetedHours;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<TimeEntry> timeEntries;

    public Activity(String name, int budgetedHours) {
        this.name = name;
        this.budgetedHours = budgetedHours;
        assignedEmployees = new ArrayList<>();
        timeEntries = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Employee getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Employee projectManager) {
        this.projectManager = projectManager;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void assignEmployee(Employee employee) {
        assignedEmployees.add(employee);
        employee.addActivity(this);
    }

    public void removeEmployee(Employee employee) {
        assignedEmployees.remove(employee);
        employee.removeActivity(this);
    }

    public int getBudgetedHours() {
        return budgetedHours;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<TimeEntry> getTimeEntries() {
        return timeEntries;
    }

    public void addTimeEntry(TimeEntry timeEntry) {
        timeEntries.add(timeEntry);
    }

    public int getSpentHours() {
        int totalSpentHours = 0;
        for (TimeEntry timeEntry : timeEntries) {
            totalSpentHours += timeEntry.getHours();
        }
        return totalSpentHours;
    }

    public int getRemainingHours() {
        return budgetedHours - getSpentHours();
    }

    public double getTotalActualHours() {
        double totalActualHours = 0.0;
        for (TimeEntry timeEntry : timeEntries) {
            totalActualHours += timeEntry.getHours();
        }
        return totalActualHours;
    }

}
