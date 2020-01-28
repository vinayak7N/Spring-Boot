package com.springBoot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.model.Employee;
import com.springBoot.service.IEmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("/welcome")
	public String welcome() {
		return "Welcome";
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees")
	public List<Employee> getAllEmployee() {
		return employeeService.getAllEmployees();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/employees/{empId}")
	public Employee getEmployeeById(@PathVariable int empId) {
		return employeeService.getEmployeeById(empId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/employees")
	public void addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/employees/{empId}")
	public void updateEmployee(@RequestBody Employee employee) {
		employeeService.updateEmployee(employee);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/employees/{empId}")
	public void deleteEmployee(@PathVariable int empId) {
		employeeService.deleteEmployee(empId);
	}
}
