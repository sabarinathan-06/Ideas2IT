package com.department.controller;

import java.util.List;
import java.util.Scanner;

import com.department.service.DepartmentManagement;
import com.department.service.DepartmentServiceImpl;
import com.exception.EmployeeException;
import com.model.Department;

/**
 * This class handles all the operation related to department based on user request.
 */
public class DepartmentController {
    private DepartmentManagement departmentManagement;
    private Department department;
    Scanner scanner = new Scanner(System.in);

    public DepartmentController() {
        this.departmentManagement = new DepartmentServiceImpl();
        this.department = new Department();
    }

    /**
     * This method is used to display the choice for operation.
     */
    public void listOperation() {
        boolean continueRunning = true;
        while (continueRunning) {
            try {
                System.out.println("1 - Add Department");
                System.out.println("2 - Update Department");
                System.out.println("3 - Remove Department");
                System.out.println("4 - List Department");
                System.out.println("5 - Exit");
                System.out.print("Enter your choice: ");
                int choice = Integer.parseInt(scanner.nextLine());
                departmentOperation(choice);
                if (choice == 5) {
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
    public void departmentOperation(int choice) {
        try {
            switch (choice) {
                case 1:
                    createDepartment();
                    break;

                case 2:
                    updateDepartment();
                    break;

                case 3:
                    removeDepartment();
                    break;

                case 4:
                    displayDepartments();
                    break;

                case 5:
                    System.out.println("Exiting..!!!");
                    System.out.println("-------------------------------");
                    break;

                default:
                    System.out.println("Invalid choice. Please try with valid choice");
            }
        } catch (EmployeeException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * This method is used to create department object with validation.
     */
    public void createDepartment() throws EmployeeException {
        System.out.println("Enter department details:");
        System.out.print("Enter Department name: ");
        String departmentName = scanner.nextLine();
        Department department = new Department(departmentName);
        departmentManagement.addDepartment(department);
        System.out.println("Department added successfully.");
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to update department details the if user ID is presented in the list.
     */
    public void updateDepartment() throws EmployeeException {
        displayDepartments();
        System.out.println("Enter department ID to update:");
        int departmentId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        departmentManagement.updateDepartment(departmentId, newName);
        System.out.println("Department updated successfully.");
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to remove the department object.
     */
    public void removeDepartment() throws EmployeeException {
        System.out.println("Enter department ID to remove:");
        int deptId = scanner.nextInt();
        scanner.nextLine();
        departmentManagement.removeDepartment(deptId);
        System.out.println("Department removed successfully.");
        System.out.println("-------------------------------");
    }

    /**
     * This method is used to display departments with all the attributes in a table format.
     */
    public void displayDepartments() throws EmployeeException {
        List<Department> activeDepartment = departmentManagement.getAllDepartments();
        System.out.println("-------------------");
        System.out.printf("| %-5s | %-20s |\n", "Id", "Name");
        for (Department department : activeDepartment) {
            System.out.printf("| %-5s | %-10s |\n", department.getDepartmentId(), department.getDepartmentName()); 
        }
        System.out.println("-------------------");
    }

    /**
     * This method is used to display department by using ID.
     */
    public void listDepartmentById() throws EmployeeException {
        System.out.println("Enter Department Id to list: ");
        int id = scanner.nextInt();
        System.out.println(departmentManagement.getDepartmentById(id));
    }
}