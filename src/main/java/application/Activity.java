package application;

import java.util.ArrayList;
import java.util.List;

public class Activity {
    private String activityName;
    private List<Employee> assignedEmployees;
    private int budgetedHours;
    private int startYear;
    private int startWeek;
    private int endYear;
    private int endWeek;
    private List<TimeRegistration> timeEntries;

    public Activity(String activityName, int budgetedHours) {
        this.activityName = activityName;
        this.budgetedHours = budgetedHours;
        assignedEmployees = new ArrayList<>();
        timeEntries = new ArrayList<>();
    }

    public String getActivityNameName() {
        return activityName;
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
        //employee.removeActivity(this);
    }

    public int getBudgetedHours() {
        return budgetedHours;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartWeek() {
        return startWeek;
    }

    public void setStartWeek(int startWeek) {
        this.startWeek = startWeek;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getEndWeek() {
        return endWeek;
    }

    public void setEndWeek(int endWeek) {
        this.endWeek = endWeek;
    }

    public List<TimeRegistration> getTimeEntries() {
        return timeEntries;
    }

    public void addTimeEntry(TimeRegistration timeEntry) {
        timeEntries.add(timeEntry);
    }

    public int getSpentHours() {
        int totalSpentHours = 0;
        for (TimeRegistration timeEntry : timeEntries) {
            totalSpentHours += timeEntry.getHours();
        }
        return totalSpentHours;
    }

    public int getRemainingHours() {
        return budgetedHours - getSpentHours();
    }

    public double getTotalActualHours() {
        double totalActualHours = 0.0;
        for (TimeRegistration timeEntry : timeEntries) {
            totalActualHours += timeEntry.getHours();
        }
        return totalActualHours;
    }

    public String getActivityName() {
        return activityName;
    }
}
