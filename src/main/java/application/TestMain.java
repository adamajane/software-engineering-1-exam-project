package application;

import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        while (true) {
            boolean running = true;
            System.out.println("Time Management App");
            System.out.println("1. Add Employee");
            System.out.println("2. Show employees");
            System.out.println("3. Create Project");
            System.out.println("4. Get Project overview");
            //System.out.println("5. Create Activity for Project");
            //System.out.println("6. Register Time");
            //System.out.println("7. Get Time Consumption Report");
            //System.out.println("8. Show All Employees");
            System.out.println("9. Exit");
            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    Employee.addEmployee();
                    break;
                case 2:
                    Employee.printEmployees();
                    break;
                case 3:
                    if (Employee.getEmployees().isEmpty()) {
                        System.out.println("No employees found. Please add employees first.");
                        break;
                    }
                    ProjectLeader.createProject(scanner);
                    break;
                case 4:
                    ProjectLeader.printProjects();
                    break;
                //case 5:
                //    createActivity();
                //    break;
                //case 6:
                //    registerTime();
                //    break;
                //case 7:
                //    getTimeConsumptionReport();
                //    break;
                //case 8:
                //    showAllEmployees();
                //    break;
                case 9:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

    }

}