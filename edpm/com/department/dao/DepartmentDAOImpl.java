package com.department.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.exception.EmployeeException;
import com.model.Department;
import com.helper.HibernateConfiguration;

/**
* This class is responsible for managing the Department entities in the database. 
This class provides methods for performing CRUD (Create, Read, Update, Delete) 
*
* It utilizes JDBC for database connectivity and operations.
*
* It holds a singleton pattern for managing the database connection and ensures that it is
a single instance of the database connection is used throughout the application.
*
* Author:
* - Sabarinathan
*/
public class DepartmentDAOImpl implements DepartmentDAO {

    private SessionFactory sessionFactory;

    public DepartmentDAOImpl() {
        this.sessionFactory = HibernateConfiguration.getSessionFactory();
    }
    public void addDepartment(Department department) throws EmployeeException {
        System.out.println(sessionFactory);
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(department);
            transaction.commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while adding department " + department.getDepartmentName(), e);
        }
    }

    public Department getDepartmentById(int id) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Department.class, id);
        } catch (HibernateException e) {
            throw new EmployeeException("Error while getting department " + id, e);
        }
    }

    public Department getDepartmentByName(String name) throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department WHERE departmentName = :name", Department.class)
                    .setParameter("name", name)
                    .uniqueResult();
        } catch (HibernateException e) {
            throw new EmployeeException("Error while getting department " + name, e);
        }
    }

    public void updateDepartment(Department department) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(department);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while updating Department " + department.getDepartmentId(), e);
        }
    }

    public void removeDepartment(int id) throws EmployeeException {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Department department = session.get(Department.class, id);
            if (department != null) {
                transaction = session.beginTransaction(); 
                department.setIsPresent(0);
                session.update(department);
            }
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while removing Department " + id, e);
        }
    }

    public List<Department> getAllDepartments() throws EmployeeException {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Department", Department.class).getResultList();
        } catch (HibernateException e) {
            throw new EmployeeException("Error while retrieving departments", e);
        }
    }

    public int getDepartmentSize() throws EmployeeException {
        int size = 0;
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Query<Long> query = session.createQuery("select count(d.id) from department d", Long.class);
            size =query.uniqueResult().intValue();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new EmployeeException("Error while retrieving department size", e);
        }
        return size;
    }
}