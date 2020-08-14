package com.springjdbc.springjdbcproject.repository;

import com.springjdbc.springjdbcproject.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {

    int count();

    int save(Employee employee);

    int updateSalary(Employee employee,int id);

    int deleteById(int id);

    List<Employee> findAll();

    List<Employee> findByAge(int age);

    Optional<Employee> findById(int id);

    String getNameById(int id);
}
