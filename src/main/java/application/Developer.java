package application;

public class Developer extends Employee {

    public Developer(String employeeId) {
        super(employeeId);

    }

    // Overrides the getRole() method in Employee
    @Override
    public String getRole() {
        return "Developer";
    }
}