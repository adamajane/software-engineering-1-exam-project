package application;

import java.time.LocalDate;
import java.util.Scanner;

public class TimeRegistration {
    private Employee employee;
    private Activity activity;
    private LocalDate date;
    private double hours;

    public TimeRegistration(Employee employee, Activity activity, LocalDate date, double hours) {
        this.employee = employee;
        this.activity = activity;
        this.date = date;
        this.hours = hours;
    }

    public static void registerTimeForEmployee(String employeeID, String activityName, double hours) {

        Employee employee = Employee.findEmployeeById(employeeID);
        if (employee == null) {
            System.out.println("Employee not found.");
            return;
        }

        Activity activity = null;
        for (Project project : ProjectLeader.getProjects()) {
            activity = project.findActivityByName(activityName);
            if (activity != null) {
                break;
            }
        }

        if (activity == null) {
            System.out.println("Activity not found.");
            return;
        }

        employee.registerTime(activity, hours);
        System.out.println("Time registered successfully.");
    }

    public void updateHours(double newHours) {
        this.hours = newHours;
    }

    public void updateDate(LocalDate newDate) {
        this.date = newDate;
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public double getHours() {
        return this.hours;
    }
}