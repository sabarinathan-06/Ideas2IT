package com.department.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.exception.EmployeeException;
import com.department.dao.DepartmentDAO;
import com.model.Department;
import com.department.service.DepartmentManagement;
import com.model.Employee;

/**
 * This class handle the all operation related to department based on user request.
 * @author 
 *     - Sabarinathan
 */
public interface DepartmentManagement {

    /**
     * This method is used to add the department.
     * @param department 
     *     - accept the object contains department details
     */
    public void addDepartment(Department department) throws EmployeeException;

    /**
     * This method is used to update the department.
     * @param integer, string
     *     - accept the department ID and name that want to update
     */
    public void updateDepartment(int id, String newName) throws EmployeeException;

    /**
     * This method is used to get the department using ID.
     * @param integer
     *     - accept the integer value
     * @return Departmnet
     *     - returns the object that contains department details
     */
    public Department getDepartmentById(int id) throws EmployeeException;

    /**
     * This method is used to get the department using Name.
     * @param string
     *     - accept the string value
     * @return Departmnet
     *     - returns the object that contains department details
     */
    public Department getDepartmentByName(String name) throws EmployeeException;

    /**
     * This method is used to get all the department in the list.
     * @return List
     *     - returns the list that contains all the departments
     */
    public List<Department> getAllDepartments() throws EmployeeException;

    /**
     * This method is used to get department size.
     * @return int
     *     - returns the size of department
     */
    public int getDepartmentSize() throws EmployeeException;

    /**
     * This method is used to display the employee.
     * @param 
     *    - accept the string value
     */
    public List<Department> getDepartments() throws EmployeeException;

    /**
     * This method is used to remove the department.
     * @param integer
     *    - accept the id as integer
     */
    public void removeDepartment(int id) throws EmployeeException;
}