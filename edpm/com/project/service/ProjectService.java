package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeServiceImplementation;
import com.exception.EmployeeException;
import com.model.Employee;
import com.model.Project;
import com.project.dao.ProjectDAO;
import com.project.service.ProjectServiceImpl;

/**
 * This class handle the all operation related to project based on user request.
 * @author 
 *     - Sabarinathan
 */
public interface ProjectService {

    /**
     * This method is used to add the Project.
     * @param Project 
     *     - accept the object contains Project details
     */
    public void addProject(Project project) throws EmployeeException;

    /**
     * This method is used to check that peoject name is present or not in the project.
     * @param string
     *     - accept the string value
     */
    public boolean isValidProjectName(String projectName) throws EmployeeException;

    /**
     * This method is used to update the project.
     * @param integer, string
     *     - accept the integer and string value
     */
    public void updateProject(int id, String newName) throws EmployeeException;

    /**
     * This method is used to get the department using ID.
     * @param integer
     *     - accept the integer value
     */
    public Project getProjectById(int id) throws EmployeeException;

    /**
     * This method is used to get the project by using Name.
     * @param string
     *     - accept the string value
     * @return project
     *     - returns the object that contains project details
     */
    public Project getProjectByName(String name) throws EmployeeException;

    /**
     * This method is used to get all the department.
     * @return List
     *     - returns the list that contains all the departments
     */
    public List<Project> getAllProjects() throws EmployeeException;

    /**
     * This method is used to display the projects.
     * @return List 
     *    - contains all the projects which is present
     */
    public List<Project> listProject() throws EmployeeException;

    /**
     * This method is used to get employee size.
     * @return integer 
     *     - contain size of employee lists
     */
    public int employeeSize() throws EmployeeException, EmployeeException;

    /**
     * This method is used to remove the department.
     */
    public void removeProject(int id) throws EmployeeException;

    /**
     * This method is used to add employee to the project.
     * @param 
     *    - accepts the employee id and project id
     */
    public void addEmployeeToProject(int employeeId, int projectId) throws EmployeeException;

    /**
     * This method is used to remove employee from the project.
     * @param 
     *    - accepts the employee id and project id
     */
    public void removeEmployeeFromProject(int employeeId, int projectId) throws EmployeeException;

    /**
     * This method is used to retrieve employees by project.
     * @param 
     *    - accepts the project id
     */
    public List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException;

}