package com.example.hiber_proj.controller;

import com.example.hiber_proj.entity.Employee;
import com.example.hiber_proj.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/employee-info")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @PostMapping("/save")
    public void saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable long id) {
        return employeeService.getEmployeeById(id);
    }

    @DeleteMapping("/{id}")
    public boolean deleteEmployeeById(@PathVariable long id) {
        return employeeService.deleteEmployeeById(id);
    }

    @PutMapping("/{id}")
    public boolean update(long id, @RequestBody Employee employee) {
        return employeeService.update(id, employee);
    }

    @GetMapping("/employee-position")
    public List<Employee> getEmployeesByPosition(@RequestParam String position) {
        return employeeService.getEmployeesByPosition(position);
    }
}
