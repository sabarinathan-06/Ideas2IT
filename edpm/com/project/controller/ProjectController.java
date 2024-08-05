package com.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.model.Employee;
import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeServiceImplementation;
import com.exception.EmployeeException;
import com.model.Project;
import com.project.service.ProjectService;
import com.project.service.ProjectServiceImpl;

/**
 * This class handles all the operation related to project based on user request.
 * @author 
 *     - Sabarinathan
 */
public class ProjectController {
    private ProjectService projectService;
    private EmployeeManagement employeeManagement;
    Scanner scanner = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    public ProjectController() {
        this.projectService = new ProjectServiceImpl();
        this.employeeManagement = new EmployeeServiceImplementation();
    }

    /**
     * This method is used to display the choice for operation.
     */
    public void listOperation() {
        boolean continueRunning = true;
        while (continueRunning) {
            try {
                System.out.println("1 - Add Project");
                System.out.println("2 - Update Project");
                System.out.println("3 - Remove Project");
                System.out.println("4 - List Project");
                System.out.println("5 - Add Employee to Project");
                System.out.println("6 - Remove Employee from Project");
                System.out.println("7 - List Employees from the project");
                System.out.println("8 - Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                projectOperation(choice);
                if (choice == 8) {
                    continueRunning = false;
                }
            } catch(NumberFormatException e) {
                logger.warn("Invalid input.Please enter a valid integer choice");
            }
        }
    }

    /**
     * This method is used to select the operation based on user request.
     */
    public void projectOperation(int choice) {
        try {
            switch (choice) {
                case 1:
                    createProject();
                    break;

                case 2:
                    updateProject();
                    break;

                case 3:
                    removeProject();
                    break;

                case 4:
                    displayProjects();
                    break;

                case 5:
                    addEmployeeToProject();
                    break;

                case 6:
                    removeEmployeeFromProject();
                    break;

                case 7:
                    listEmployeesByProject();
                    break;

                case 8:
                    System.out.println("Exiting..!!!");
                    System.out.println("-------------------------------");
                    break;

                default:
                    System.out.println("Invalid choice. Please try with valid choice");
            }
        } catch (EmployeeException e) {
            logger.error(e.getMessage());
        } 
    }

    /**
     * This method is used to create project object with validation.
     */
    public void createProject() throws EmployeeException {
        System.out.println("Enter project details:");
        System.out.print("Enter Project name: ");
        String projectName = scanner.nextLine();
        boolean ispresent = projectService.isValidProjectName(projectName);
        if (ispresent) {
            logger.info("Project has already been presented.");
        } else {
            Project project = new Project(projectName);
            projectService.addProject(project);
            logger.info("Project added successfully with tha name: ", projectName);
        }
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to update the project name if project ID is presented in the list.
     */
    public void updateProject() throws EmployeeException {
        System.out.println("Enter project ID to update:");
        int projectId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        projectService.updateProject(projectId, newName);
        logger.info("Project updated successfully for the ID: ", projectId);
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to remove the project object.
     */
    public void removeProject() throws EmployeeException {
        System.out.println("Enter project ID to remove:");
        int projectId = scanner.nextInt();
        projectService.removeProject(projectId);
        logger.info("Project removed successfully with the ID: ", projectId);
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to display the availabe projects in a table format.
     */
    public void displayProjects() throws EmployeeException {
        List<Project> availableProjects = projectService.getAllProjects();
        System.out.println("--------------------------------");
        System.out.printf("| %-5s | %-20s |\n", "Id", "Name");
        for (Project project : availableProjects) {
            System.out.printf("| %-5s | %-20s |\n", project.getProjectId(), project.getProjectName()); 
        }
        System.out.println("--------------------------------");
    }

    /**
     * This method is used to display the employees in the project using project ID.
     */
    public void listEmployeesByProject() throws EmployeeException {
        displayProjects();
        System.out.println("Enter Project Id to list: ");
        int id = scanner.nextInt();
        List<Employee> projects = projectService.retrieveEmployeesByProject(id);
        Project project = projectService.getProjectById(id);
        if (projects.size() != 0) {
            System.out.println("Project Name = " + project.getProjectName());
            System.out.println("Assigned Employees:");
            System.out.println("--------------------------------");
            System.out.printf("| %-5s | %-20s |\n", "Id", "Name");
            for (Employee employee : projects) {
                System.out.printf("| %-5s | %-20s |\n", employee.getId(), employee.getName());            
            }
        System.out.println("--------------------------------");
        } else {
            logger.error("Project not found.");
        }
    }

    /**
     * This method is used to add employee to the project by using employee ID.
     */
    public void addEmployeeToProject() throws EmployeeException {
        displayProjects();
        System.out.println("Enter Project ID: ");
        int projectId = scanner.nextInt();
        Project project = projectService.getProjectById(projectId);
        System.out.println("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        Employee employee = employeeManagement.getEmployeeById(employeeId);
        if (project != null && employee != null) {
            projectService.addEmployeeToProject(employeeId, projectId);
            logger.info("Employee added to project successfully for the project ID: " + projectId 
                        + "and for the employee ID: " + employeeId);
        } else {
            logger.error("Project or Employee not found.");
        }
    }

    /**
     * This method is used to remove employee to the project by using employee ID.
     */
    public void removeEmployeeFromProject() throws EmployeeException {
        displayProjects();
        System.out.println("Enter Project ID: ");
        int projectId = scanner.nextInt();
        Project project = projectService.getProjectById(projectId);
        System.out.println("Enter Employee ID: ");
        int employeeId = scanner.nextInt();
        Employee employee = employeeManagement.getEmployeeById(employeeId);
        if (project != null && employee != null) {
            projectService.removeEmployeeFromProject(employeeId, projectId);
            logger.info("Employee removed from project successfully with the project ID: " + projectId 
                        + "and for the employee ID: " + employeeId);
        } else {
            logger.error("Project or Employee not found.");
        }
    }
}