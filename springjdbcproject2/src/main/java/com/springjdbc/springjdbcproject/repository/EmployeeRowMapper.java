package com.springjdbc.springjdbcproject.repository;

import com.springjdbc.springjdbcproject.model.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper {
    @Override
    public Employee mapRow(ResultSet resultSet, int i) throws SQLException {

        Employee employee = new Employee();
        employee.setId(resultSet.getInt("employee_id"));
        employee.setName(resultSet.getString("name"));
        employee.setAge(resultSet.getInt("age"));
        employee.setSalary(resultSet.getInt("salary"));
        return employee;
    }
}
