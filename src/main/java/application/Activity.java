package application;

import java.util.ArrayList;
import java.util.List;

//Author: Alexander Vaaben (Contributors: Adam Ajane + Oliver Brandt)
public class Activity {
    private String activityName;
    private List<Employee> assignedEmployees;
    private int budgetedHours;
    private int startYear;
    private int startWeek;
    private int endYear;
    private int endWeek;
    private List<TimeRegistration> timeRegistrations;

    public Activity(String activityName, int budgetedHours, int startYear, int startWeek, int endYear, int endWeek) {
        this.activityName = activityName;
        this.budgetedHours = budgetedHours;
        this.startYear = startYear;
        this.startWeek = startWeek;
        this.endYear = endYear;
        this.endWeek = endWeek;
        assignedEmployees = new ArrayList<>();
        this.timeRegistrations = new ArrayList<>();
    }


    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void assignEmployee(Employee employee) {
        assignedEmployees.add(employee);
        employee.addActivity(this);
    }

    public int getBudgetedHours() {
        return budgetedHours;
    }

    public void setBudgetedHours(int budgetedHours) {
        this.budgetedHours = budgetedHours;
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

    public void addTimeRegistration(TimeRegistration timeRegistration) {
        timeRegistrations.add(timeRegistration);
    }

    public String getActivityName() {
        return activityName;
    }

    public List<TimeRegistration> getTimeRegistrations() {
        return timeRegistrations;
    }

    public double getExpectedWorkingHours() {
        return budgetedHours;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

}
