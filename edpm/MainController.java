import java.util.Scanner;

import com.department.controller.DepartmentController;
import com.employee.controller.EmployeeController;
import com.project.controller.ProjectController;

/**
 * This class get user request operation to thier respective controllers.
 */
public class MainController {
    private EmployeeController employeeController;
    private DepartmentController departmentController;
    private ProjectController projectController;
    Scanner scanner = new Scanner(System.in);

    public MainController() {
        this.employeeController = new EmployeeController();
        this.departmentController = new DepartmentController();
        this.projectController = new ProjectController();
    }

/**
 * This method is used to display the choices fo the user.
 */
    public void listOperation() {
        boolean continueRunning = true;
        while (continueRunning) {
            try {
                System.out.println("EMPLOYEE MANAGEMENT");
                System.out.println("1 - Employee Operation");
                System.out.println("2 - Department Operation");
                System.out.println("3 - Project Operation");
                System.out.println("4 - Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                showMenu(choice);
                if (choice == 4) {
                    continueRunning = false;
                }
            } catch(NumberFormatException e) {
                System.out.println("Invalid input.Please enter a valid integer choice");
            }
        }
    }

/**
 * This method is used to select the operation based on user request.
 */
    public void showMenu(int choice) {
        switch (choice) {
            case 1:
                employeeController.listOperation();
                break;

            case 2:
                departmentController.listOperation();
                break;

            case 3:
                projectController.listOperation();
                break;

            case 4:
                System.out.println("Exiting..!!!");
                break;

            default:
                System.out.println("Invalid choice. Please try with valid choice");
        }
    }

    /**
     * This main method is used to handle all operation related to employee, 
department and project based on user request.
     */
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.listOperation();
    }
}