package com.department.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.helper.DataBaseConnection;
import com.model.Department;
import com.model.Employee;
import com.exception.EmployeeException;

/**
* This class is responsible for managing the Department entities in the database. 
This class provides methods for performing CRUD (Create, Read, Update, Delete) 
*
* It utilizes JDBC for database connectivity and operations.
*
* It holds a singleton pattern for managing the database connection and ensures that it is
a single instance of the database connection is used throughout the application.
*/
public interface DepartmentDAO {

    /**
     * This method is used to add the department.
     * @param department 
     *     - accept the object contains department details
     */
    public void addDepartment(Department department) throws EmployeeException;

    /**
     * This method is used to get department using ID.
     * @return Department
     *     - returns the department object that contains department details
     */
    public Department getDepartmentById(int id) throws EmployeeException;

    /**
     * This method is used to get department using name.
     * @return Department
     *     - returns the department object that contains department details
     */
    public Department getDepartmentByName(String name) throws EmployeeException;

    /**
     * This method is used to update the department.
     * @param integer, string
     *     - accept the department ID and name that want to update
     */
    public void updateDepartment(Department department) throws EmployeeException;

    /**
     * This method is used to get department size.
     * @return int
     *     - returns the size of department
     */
    public int getDepartmentSize() throws EmployeeException;

    /**
     * This method is used to remove the department if present and sets isPresent as 0.
     * @param integer
     *    - accept the id as integer
     */
    public void removeDepartment(int id) throws EmployeeException;

    /**
     * This method is used to get all the departments.
     * @return List 
     *     - contains list of department object
     */
    public List<Department> getAllDepartments() throws EmployeeException;
}