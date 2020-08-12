package com.springjdbc.springjdbcproject.controller;

import com.springjdbc.springjdbcproject.model.Employee;
import com.springjdbc.springjdbcproject.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final List<Employee> employeeList = Arrays.asList(
            new Employee(101, "Heman", 25, 10000),
            new Employee(102, "IronMan", 35, 50000),
            new Employee(103, "Thor", 61, 80000)
    );
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    private final JdbcTemplate jdbcTemplate;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(JdbcTemplate jdbcTemplate, EmployeeRepository employeeRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.employeeRepository = employeeRepository;
        startupRun();
    }

    private void startupRun() {
        employeeList.forEach(
                employee -> {
                    log.info("♥Saving...{}", employee.getName());
                    employeeRepository.save(employee);
                }
        );
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) {
        employeeRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        log.info("[FIND_BY_ID]: {}", id);
        Employee employee = employeeRepository.findById(id).orElse(null);
        log.info("{}", employee);
        return employee;
    }

    @GetMapping("/count")
    public int totalEmployees() {
        log.info("[COUNT_OF_EMPLOYEES]");
        return employeeRepository.count();
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @GetMapping("/age/{age}")
    public List<Employee> employeesByAge(@PathVariable int age) {
        log.info("[COUNT_OF_EMPLOYEES_BY_AGE]: {}", age);
        List<Employee> employees = employeeRepository.findByAge(age);
        log.info("[FIND_BY_AGE] {}", employees);
        return employees;
    }

    @GetMapping("/name/{id}")
    public String getNameById(@PathVariable int id) {
        log.info("[NAME_BY_ID]: {}", id);
        String employeeName = employeeRepository.getNameById(id);
        log.info("[EMPLOYEE_NAME] {}", employeeName);
        return employeeName;
    }
}
