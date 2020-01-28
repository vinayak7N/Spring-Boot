package com.springBoot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.dao.EmployeeDAO;
import com.springBoot.exception.EmployeeNotFoundException;
import com.springBoot.model.Employee;

@Service
public class EmployeeService implements IEmployeeService {

	@Autowired
	private EmployeeDAO empDao;

	List<Employee> employeeList = new ArrayList<>(
			Arrays.asList(new Employee(101, "Harry", "CSE"), new Employee(102, "Ben", "ME")));

	@Override
	public List<Employee> getAllEmployees() {

		// return employeeList;
		return empDao.findAll();
	}

	@Override
	public Employee getEmployeeById(int empId) {
		// return employeeList.stream().filter(e -> e.getEmpId() ==// empId).findFirst().get();
		Optional<Employee> optionalCustomer = empDao.findById(empId);
		if (!optionalCustomer.isPresent())
			throw new EmployeeNotFoundException("Invalid Id Employee not available...");
		return optionalCustomer.get();
	}

	@Override
	public void addEmployee(Employee employee) {
		// employeeList.add(employee);
		empDao.save(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		/*
		 * for (int i = 0; i < employeeList.size(); i++) { Employee e =
		 * employeeList.get(i); if (e.getEmpId() == employee.getEmpId()) {
		 * employeeList.set(i, employee); return; } }
		 */
		empDao.save(employee);
	}

	@Override
	public void deleteEmployee(int empId) {
		// employeeList.removeIf(e->e.getEmpId()==empId);
		empDao.deleteById(empId);
	}

}
