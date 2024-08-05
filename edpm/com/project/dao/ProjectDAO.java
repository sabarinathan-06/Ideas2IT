package com.project.dao;

import java.util.List;

import com.exception.EmployeeException;
import com.model.Project;
import com.model.Employee;

public interface ProjectDAO {

    /**
     * This method is used to add the project.
     * @param Project 
     *     - accept the object contains project details
     */
    public void addProject(Project project) throws EmployeeException;

    /**
     * This method is used to get project using ID.
     * @return Project
     *     - returns the Project object that contains project details
     */
    public Project getProjectById(int id) throws EmployeeException;

    /**
     * This method is used to get project using name.
     * @return Project
     *     - returns the project object that contains project details
     */
    public Project getProjectByName(String name) throws EmployeeException;

    /**
     * This method is used to check that peoject name is present or not in the project.
     * @param string
     *     - accept the string value
     */
    public boolean isValidProjectName(String projectName) throws EmployeeException;

    /**
     * This method is used to update the project.
     * @param Project
     *     - accept the project object that contains project details 
     */
    public void updateProject(int id, Project project) throws EmployeeException;

    /**
     * This method is used to get all the departments.
     * @return List 
     *     - contains list of project object
     */
    public List<Project> getAllProjects() throws EmployeeException;

    /**
     * This method is used to remove the project if present and sets isPresent as 0.
     * @param integer
     *    - accept the project id as integer
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