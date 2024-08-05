package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.department.service.DepartmentManagement;
import com.department.service.DepartmentServiceImpl;
import com.model.Department;
import com.model.Employee;
import com.employee.dao.EmployeeDAO;
import com.employee.dao.EmployeeDAOImpl;
import com.employee.service.EmployeeManagement;
import com.exception.EmployeeException;

/**
 * This class is responsible for maintaining Employee details by 
doing Employee related operations.
 *
 * Author:
 * - Sabarinathan
 */
public class EmployeeServiceImplementation implements EmployeeManagement {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private DepartmentManagement departmentManagement = new DepartmentServiceImpl();

    public void createEmployee(Employee employee) throws EmployeeException {
        employeeDAO.addOrUpdateEmployee(employee);

    }

    public List<Employee> listEmployees() throws EmployeeException {
        List<Employee> activeEmployees = new ArrayList<>();
        List<Employee> allEmployees = employeeDAO.getAllEmployees();
        for (Employee emp : allEmployees) {
            if (emp.getIsPresent() == 0) {
                activeEmployees.add(emp);
            }
        }
        return activeEmployees;
    }

    public Employee getEmployeeById(int id) throws EmployeeException {
        return employeeDAO.getEmployeeById(id);
    }

    public List<Employee> getAllEmployees() throws EmployeeException {
        return employeeDAO.getAllEmployees();
    }

    public void updateEmployee(int id, String name, String place, String dob, 
                               int experience, String departmentName) throws EmployeeException {
        Employee employee = employeeDAO.getEmployeeById(id);
        if (employee != null) {
            if (name != null && !name.isEmpty()) {
                employee.setName(name);
            }
            if (place != null && !place.isEmpty()) {
                employee.setPlace(place);
            }
            if (dob != null && !dob.isEmpty()) {
                employee.setDob(dob);
            }
            if (experience >= 0) {
                employee.setExperience(experience);
                employee.setSalary(setSalary(experience));
            }
            if (departmentName != null && !departmentName.isEmpty()) {
                Department department = departmentManagement.getDepartmentByName(departmentName);
                if (department != null) {
                    employee.setDepartment(department);
                }
            }
            employeeDAO.addOrUpdateEmployee(employee);
        }
    }

    public int getNextId() throws EmployeeException {
        return employeeDAO.getAllEmployees().size() + 1;
    }

    public boolean removeEmployee(int id) throws EmployeeException {
        return employeeDAO.removeEmployee(id);
    }

    public List<Department> getAllDepartments() throws EmployeeException {
        return departmentManagement.getAllDepartments();
    }

    public Department getDepartmentById(int id) throws EmployeeException {
        return departmentManagement.getDepartmentById(id);
    }

    public List<Employee> retieriveEmployeesByDepartment(int id) throws EmployeeException {
        return employeeDAO.retrieveEmployeesByDepartment(id);
    }

    public double setSalary(int experience) {
        double salary = 0;
        if (experience >= 0 && experience <= 3) {
            salary = 520000;
        } else if (experience > 3 && experience <= 5) {
            salary = 800000;
        } else if (experience > 5 && experience <= 8) {
            salary = 1200000;
        } else if (experience > 8 && experience <= 10) {
            salary = 1800000;
        } else {
            salary = 2500000;
        }
        return salary;
    }
}