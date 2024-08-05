package com.project.service;

import java.util.ArrayList;
import java.util.List;

import com.employee.service.EmployeeManagement;
import com.employee.service.EmployeeServiceImplementation;
import com.exception.EmployeeException;
import com.model.Employee;
import com.model.Project;
import com.project.dao.ProjectDAO;
import com.project.dao.ProjectDAOImpl;

/**
 * This class handle the all operation related to project based on user request.
 * @author 
 *     - Sabarinathan
 */
public class ProjectServiceImpl implements ProjectService {
    private ProjectDAO projectDAO = new ProjectDAOImpl(); 
    private EmployeeManagement employeeManagement = new EmployeeServiceImplementation();

    public void addProject(Project project) throws EmployeeException {
        projectDAO.addProject(project);
    }

    public boolean isValidProjectName(String projectName) throws EmployeeException {
        return projectDAO.isValidProjectName(projectName);
    }

    public void updateProject(int id, String newName) throws EmployeeException {
        Project project = projectDAO.getProjectById(id);
        if (project != null) {
            project.setProjectName(newName);
            projectDAO.updateProject(id, project);
        }
    }

    public Project getProjectById(int id) throws EmployeeException {
        return projectDAO.getProjectById(id);
    }

    public Project getProjectByName(String name) throws EmployeeException {
        return projectDAO.getProjectByName(name);
    }

    public List<Project> getAllProjects() throws EmployeeException {
        return projectDAO.getAllProjects();
    }

    public List<Project> listProject() throws EmployeeException {
        List<Project> activeProject = new ArrayList<>();
        List<Project> allProject = projectDAO.getAllProjects();
        for (Project project : allProject) {
            if (project.getIsPresent() == 1) {
                activeProject.add(project);
            }
        }
        return activeProject;
    }

    public int employeeSize() throws EmployeeException, EmployeeException {
        return employeeManagement.listEmployees().size();
    }

    public void removeProject(int id) throws EmployeeException {
        projectDAO.removeProject(id);
    }

    public void addEmployeeToProject(int employeeId, int projectId) throws EmployeeException {
        projectDAO.addEmployeeToProject(employeeId,projectId);
    }

    public void removeEmployeeFromProject(int employeeId, int projectId) throws EmployeeException {
        projectDAO.removeEmployeeFromProject(employeeId,projectId);
    }

    public List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException {
        return projectDAO.retrieveEmployeesByProject(projectId);
    }
}