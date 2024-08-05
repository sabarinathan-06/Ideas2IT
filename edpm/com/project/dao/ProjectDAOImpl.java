package com.project.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.exception.EmployeeException;
import com.helper.HibernateConfiguration;
import com.model.Employee;
import com.model.Project;

/**
* This class provides methods to perform CRUD operations on the Project database table.
* This class interacts with the database to add, retrieve, update, and delete project records.
*
* It holds a singleton pattern for managing the database connection and ensures that it is
* a single instance of the database connection is used throughout the application.
*
* The class implements ProjectDAO Interface, providing a standardized interface for database
* operations related to projects.
*
* Author:
* - Sabarinathan
*/
public class ProjectDAOImpl implements ProjectDAO {

    private SessionFactory sessionFactory;

    public ProjectDAOImpl() {
        this.sessionFactory = HibernateConfiguration.getSessionFactory();
    }

    @Override
    public void addProject(Project project) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(project);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Issue while creating project " 
                                        + project.getProjectName(), e);
        }
    }

    @Override
    public Project getProjectById(int id) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Project.class, id);
        } catch (HibernateException e) {
            throw new EmployeeException("Issue while retrieving the project: " 
                                        + e.getMessage(), e);
        }
    }

    @Override
    public Project getProjectByName(String name) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            Query<Project> query = session.createQuery("FROM Project WHERE projectName = :name " 
                                                       + "AND isPresent = 1", Project.class);
            query.setParameter("name", name);
            Project project = query.uniqueResult();
            if (project != null && !isValidProjectName(project.getProjectName())) {
                return new Project();
            }
            return project;
        } catch (HibernateException e) {
            throw new EmployeeException("Issue while retrieving the project by name: " 
                                   + e.getMessage(), e);
        }
    }

    @Override
    public boolean isValidProjectName(String projectName) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Project "
                                                    + "WHERE projectName = :projectName",
                                                    Long.class);
            query.setParameter("projectName", projectName);
            Long count = query.uniqueResult();
            return count > 0;
        } catch (HibernateException e) {
            throw new EmployeeException("Error while validating project name " + projectName, e);
        }
    }

    @Override
    public void updateProject(int id, Project project) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Project existingProject = session.get(Project.class, id);
            if (existingProject != null && existingProject.getIsPresent() == 1) {
                existingProject.setProjectName(project.getProjectName());
                session.update(existingProject);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Issue while updating project " 
                                        + project.getProjectName(), e);
        }
    }

    @Override
    public List<Project> getAllProjects() throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            Query<Project> query = session.createQuery("FROM Project WHERE isPresent = 1",
                                                       Project.class);
            return query.list();
        } catch (HibernateException e) {
            throw new EmployeeException("Issue while retrieving the projects: " 
                                        + e.getMessage(), e);
        }
    }

    @Override
    public void removeProject(int id) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Project project = session.get(Project.class, id);
            if (project != null) {
                project.setIsPresent(0);
                session.update(project);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Issue while deleting project with id " + id + ": " 
                                   + e.getMessage(), e);
        }
    }

    @Override
    public void addEmployeeToProject(int employeeId, int projectId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Project project = session.get(Project.class, projectId);
            if (employee != null && project != null) {
                project.getEmployees().add(employee);
                session.update(project);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error assigning employee to project: " 
                                        + e.getMessage(), e);
        }
    }

    @Override
    public void removeEmployeeFromProject(int employeeId, int projectId) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Employee employee = session.get(Employee.class, employeeId);
            Project project = session.get(Project.class, projectId);
            if (employee != null && project != null) {
                project.getEmployees().remove(employee);
                session.update(project);
                transaction.commit();
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error in removing employee from project: " 
                                        + e.getMessage() + " Employee ID = " + employeeId, e);
        }
    }

    @Override
    public List<Employee> retrieveEmployeesByProject(int projectId) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            Query<Employee> query = session.createQuery("SELECT e FROM Employee e "
                                                        + "JOIN e.projects p "
                                                        + "WHERE p.id = :projectId", 
                                                        Employee.class);
            query.setParameter("projectId", projectId);
            return query.list();
        } catch (HibernateException e) {
            throw new EmployeeException("Error in retrieve employees by project: " + e.getMessage() 
                                   + " Project ID = " + projectId, e);
        }
    }
}