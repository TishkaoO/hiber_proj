package com.example.hiber_proj.dao;

import com.example.hiber_proj.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EmployeeDao {

    private final SessionFactory sessionFactory;

    @Autowired
    public EmployeeDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.openSession();
        Employee entity = new Employee();
        try {
            session.beginTransaction();
            entity.setName(employee.getName());
            entity.setPosition(employee.getPosition());
            session.save(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    public Optional<Employee> findEmployeeById(long id) {
        Session session = sessionFactory.openSession();
        Optional<Employee> employeeOptional = null;
        try {
            session.beginTransaction();
            Optional<Employee> queryResult = session.createQuery("from Employee where id = :fId", Employee.class)
                    .setParameter("fId", id)
                    .uniqueResultOptional();
            session.getTransaction().commit();
            employeeOptional = queryResult;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return employeeOptional;
    }

    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        List<Employee> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery("from Employee", Employee.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public boolean deleteEmployeeById(long id) {
        var session = sessionFactory.openSession();
        boolean result = false;
        try {
            session.beginTransaction();
            int count = session.createQuery(
                            "DELETE Employee WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate();
            session.getTransaction().commit();
            result = count > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public boolean replace(long id, Employee employee) {
        boolean result = false;
        var session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            int count = session.createQuery(
                            "UPDATE Employee SET name = :fName, position = :fPosition WHERE id = :fId")
                    .setParameter("fName", employee.getName())
                    .setParameter("fPosition", employee.getPosition())
                    .setParameter("fId", employee.getId())
                    .executeUpdate();
            session.getTransaction().commit();
            result = count > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }

    public List<Employee> findEmployeesByPosition(String position) {
        var session = sessionFactory.openSession();
        List<Employee> result = new ArrayList<>();
        try {
            session.beginTransaction();
            result = session.createQuery(
                            "FROM Employee WHERE position = :fPosition")
                    .setParameter("fPosition", position)
                    .list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return result;
    }
}
