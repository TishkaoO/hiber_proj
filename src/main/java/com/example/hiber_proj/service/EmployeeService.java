package com.example.hiber_proj.service;

import com.example.hiber_proj.dao.EmployeeDao;
import com.example.hiber_proj.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EmployeeService {
    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void saveEmployee(Employee employee) {
        employeeDao.saveEmployee(employee);
    }

    public Employee getEmployeeById(long id) {
        return employeeDao.findEmployeeById(id)
                .orElseThrow(() -> new NoSuchElementException("employee not found"));
    }

    public List<Employee> getAll() {
        return employeeDao.findAll();
    }

    public boolean deleteEmployeeById(long id) {
        return employeeDao.deleteEmployeeById(id);
    }

    public boolean update(long id, Employee employee) {
        return employeeDao.replace(id, employee);
    }

    public List<Employee> getEmployeesByPosition(String position) {
        return employeeDao.findEmployeesByPosition(position);
    }
}
