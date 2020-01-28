package com.springBoot.service;

import java.util.List;

import com.springBoot.model.Employee;

public interface IEmployeeService {

	List<Employee> getAllEmployees();

	Employee getEmployeeById(int empId);

	void addEmployee(Employee employee);

	void updateEmployee(Employee employee);

	void deleteEmployee(int empId);
}
