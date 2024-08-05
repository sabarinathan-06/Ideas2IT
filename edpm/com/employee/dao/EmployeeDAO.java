package com.employee.dao;

import java.sql.SQLException;
import java.util.List;
import com.model.Employee;
import com.exception.EmployeeException;

/**
* Interface for Employee defines methods for CRUD operations on Employee objects.
*/
public interface EmployeeDAO {

    /**
     * This method is used to add the employee employee object to the list.
     * @param Employee
     *    - accepts the employee object which contains employee attributes 
     */
    void addOrUpdateEmployee(Employee employee) throws EmployeeException;

    /**
     * This method is used to get the employee using ID.
     * @param integer
     *     - accepts user id
     * @return Employee 
     *     - contains employee attributes as object
     */
    Employee getEmployeeById(int id) throws EmployeeException;

    /**
     * This method is used to soft delete the employee object by using employee id and sets
the isPresent variable to 0 which is responsible for soft deletion.
     * @param integer
     *     - accepts the  user id
     * @return boolean
     *     - contains boolean value to get deleted or not
     */
    boolean removeEmployee(int id) throws EmployeeException;

    /**
     * This method is used to get all the employee.
     * @return List 
     *     - contains list of employee object
     */
    List<Employee> getAllEmployees() throws EmployeeException;

    /**
     * This method is used to get retrieve employees using department.
     * @param integer
     *     - accepts the  user id
     * @return List 
     *     - contains list of employee object
     */
    public List<Employee> retrieveEmployeesByDepartment(int id) throws EmployeeException;

}