package application;

public class Developer extends Employee {

    public Developer(String employeeId) {
        super(employeeId);

    }

    @Override
    public String getRole() {
        return "Developer";
    }
}