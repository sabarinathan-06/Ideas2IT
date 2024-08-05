package com.employee.service;

import java.util.ArrayList;
import java.util.List;

import com.model.Department;
import com.department.service.DepartmentManagement;
import com.employee.dao.EmployeeDAO;
import com.model.Employee;
import com.exception.EmployeeException;

/**
 * This class is responsible for maintaining Employee details by 
doing Employee related operations.
 */
public interface EmployeeManagement {

    /**
     * This method is used to create the employee.
     * @param Employee
     *     - accept the employee reference
     */
    public void createEmployee(Employee employee) throws EmployeeException;

    /**
     * This method is used to list the employees which is present.
     * @return List<Employee>
     *     - contains employee reference which is present
     */
    public List<Employee> listEmployees() throws EmployeeException;

    /**
     * This method is used to get employee using employeeId.
     * @param integer
     *    - accept the Id
     */
    public Employee getEmployeeById(int id) throws EmployeeException;

    /**
     * This method is used to get all the employees.
     * @return List<Employee>
     *    - contains employee reference
     */
    public List<Employee> getAllEmployees() throws EmployeeException;

    /**
     * This method is used to update the employee attributes individualy.
     * @param integer, string
     *    - accept the id, name, place, dateOfBirth, experience, department
     */
    public void updateEmployee(int id, String name, String place, String dob, 
                               int experience, String departmentName) throws EmployeeException;

    /**
     * This method is used to get employee id and get increamented.
     * @return integer 
     *     - contains employee id
     */
    public int getNextId() throws EmployeeException;

    /**
     * This method is used to remove the employee.
     * @param 
     *    - accept the string value
     */
    public boolean removeEmployee(int id) throws EmployeeException;

    /**
     * This method is used to get all the department.
     * @return List
     *     - returns the list that contains all the departments
     */
    public List<Department> getAllDepartments() throws EmployeeException;

    /**
     * This method is used to get the department using ID.
     * @param integer
     *     - accept the integer value
     */
    public Department getDepartmentById(int id)throws EmployeeException;

    /**
     * This method is used to set the employee's salary.
     * @param 
     *     - accept the integer value
     * @return double
     *     - it contains salary 
     */
    public double setSalary(int experience);

    /**
     * This method is used to get retrieve employees using department.
     * @param integer
     *     - accepts the  user id
     * @return List 
     *     - contains list of employee object
     */
    public List<Employee> retieriveEmployeesByDepartment(int id) throws EmployeeException;

}