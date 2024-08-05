package com.employee.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.exception.EmployeeException;
import com.model.Department;
import com.model.Employee;
import com.helper.HibernateConfiguration;

/**
* This class is responsible for managing the Employee entities in the database.
* This class provides methods for performing CRUD (Create, Read, Update, Delete)
* operations on Employee records.
*
* It utilizes Hibernate for database connectivity and operations.
*
* Author:
* - Sabarinathan
*/
public class EmployeeDAOImpl implements EmployeeDAO {

    private SessionFactory sessionFactory;

    public EmployeeDAOImpl() {
        this.sessionFactory = HibernateConfiguration.getSessionFactory();
    }

    public void addOrUpdateEmployee(Employee employee) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(employee);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while adding or updating Employee " 
                                        + employee.getName(), e);
        }
    }

    public Employee getEmployeeById(int id) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            Employee employee = session.get(Employee.class, id);
            if (employee == null) {
                return null;
            }
            return employee;
        } catch (HibernateException e) {
            throw new EmployeeException("Error while getting Employee " + id, e);
        }
    }

    public boolean removeEmployee(int id) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Employee employee = session.get(Employee.class, id);
            if (employee != null) {
                transaction = session.beginTransaction();
                employee.setIsPresent(1);
                session.update(employee);
                transaction.commit();
                return true;
            } else {
                return false;
            }
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while removing Employee " + id, e);
        }
    }

    public List<Employee> getAllEmployees() throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> employees = session.createQuery("FROM Employee WHERE isPresent = 0",
                                               Employee.class).list();
            System.out.println("Size : " + employees.size());
            return employees;
        } catch (HibernateException e) {
            throw new EmployeeException("Error while getting all Employees", e);
        }
    }

    public List<Employee> retrieveEmployeesByDepartment(int id) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            List<Employee> employees = session.createQuery("FROM Employee WHERE department.id " 
                                              + "= :id AND isPresent = 1", Employee.class)
                                              .setParameter("id", id)
                                              .list();
            return employees;
        } catch (HibernateException e) {
            throw new EmployeeException("Error while retrieving Employees by department", e);
        }
    }
}
