package com.springjdbc.springjdbcproject.repository;

import com.springjdbc.springjdbcproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class NamedParameterJDBCEmployeeRepository implements EmployeeRepository {


    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public NamedParameterJDBCEmployeeRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {

        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }


    @Override
    public int count() {

        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select count(*) from employee",
                    new MapSqlParameterSource(), Integer.class);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("unable to fetch count...");
            return -1;
        }
    }

    @Override
    public int save(Employee employee) {
        return namedParameterJdbcTemplate.update(
                "insert into employee(employee_id,name,age,salary) values(:id,:name,:age,:salary)",
                new BeanPropertySqlParameterSource(employee)
        );
    }

    @Override
    public int updateSalary(Employee employee, int id) {

        try {
            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("id", id);
            map.addValue("salary", employee.getSalary());
            return namedParameterJdbcTemplate.update("update employee set salary = :salary where employee_id = :id",
                    map);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Unable to update employee...");
            return -1;
        }
    }

    @Override
    public int deleteById(int id) {

        try {
            MapSqlParameterSource map = new MapSqlParameterSource();
            map.addValue("id", id);
            return namedParameterJdbcTemplate.update("delete from employee where employee_id = :id",
                    map);
        } catch (EmptyResultDataAccessException e) {
            System.out.println("Unable to delete employee...");
            return -1;
        }
    }

    @Override
    public List<Employee> findAll() {
        return namedParameterJdbcTemplate.query("select * from employee", new EmployeeRowMapper());
    }

    @Override
    public List<Employee> findByAge(int age) {
        return namedParameterJdbcTemplate.query(
                "select * from employee where age = :age",
                new MapSqlParameterSource("age", age), new EmployeeRowMapper());
    }

    @Override
    public Optional<Employee> findById(int id) {

        try {
            return Optional.ofNullable((Employee) namedParameterJdbcTemplate.queryForObject(
                    "select * from employee where employee_id = :id",
                    new MapSqlParameterSource("id", id), new EmployeeRowMapper()));

        } catch (EmptyResultDataAccessException e) {
            System.out.println("Employee not found...");
            return null;
        }
    }

    @Override
    public String getNameById(int id) {

        try {
            return namedParameterJdbcTemplate.queryForObject(
                    "select name from employee where employee_id = :id",
                    new MapSqlParameterSource("id", id), String.class);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}