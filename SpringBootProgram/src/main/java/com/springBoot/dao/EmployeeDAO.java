
package com.springBoot.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.springBoot.model.Employee;

@Repository
public interface EmployeeDAO extends CrudRepository<Employee, Integer> {

	@Override
	List<Employee> findAll();
}
