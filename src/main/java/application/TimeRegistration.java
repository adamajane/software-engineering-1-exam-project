package application;

import java.time.LocalDate;
import java.util.Scanner;

//Author: Adam Ajane (Contributors: Oliver Brandt)
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
        Employee employee = Employee.findEmployeeByID(employeeID);
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

        boolean success = employee.registerTime(activity, hours);
        if (success) {
            System.out.println("Time registered successfully.");
        }
    }

    public Employee getEmployee() {
        return this.employee;
    }

    public Activity getActivity() {
        return this.activity;
    }

    public double getHours() {
        return this.hours;
    }
}