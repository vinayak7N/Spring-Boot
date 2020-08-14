package com.springjdbc.springjdbcproject.repository;

import com.springjdbc.springjdbcproject.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JDBCEmployeeRepository implements EmployeeRepository {


    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JDBCEmployeeRepository(JdbcTemplate jdbcTemplate) {

        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from employee", Integer.class);
    }

    @Override
    public int save(Employee employee) {
        return jdbcTemplate.update(
                "insert into employee(employee_id,name,age,salary) values(?,?,?,?)",
                employee.getId(), employee.getName(), employee.getAge(), employee.getSalary()
        );
    }

    @Override
    public int updateSalary(Employee employee, int id) {
        return jdbcTemplate.update("update employee set salary = ? where employee_id = ?",
                employee.getSalary(), id);
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("delete employee where employee_id = ?", id);
    }

    @Override
    public List<Employee> findAll() {
        return jdbcTemplate.query(
                "select * from employee",
                /*(rs, rowNum) -> new Employee(rs.getInt("employee_id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getInt("salary"))*/
                new EmployeeRowMapper()
        );
    }

    @Override
    public List<Employee> findByAge(int age) {
        return jdbcTemplate.query(
                "select * from employee where age = ?",
                new Object[]{age},
                /*(rs, rowNum) ->
                        new Employee(rs.getInt("employee_id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getInt("salary"))*/
                new EmployeeRowMapper()
        );
    }

    @Override
    public Optional<Employee> findById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from employee where employee_id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Employee(
                                rs.getInt("employee_id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getInt("salary")
                        ))

        );
    }

    @Override
    public String getNameById(int id) {
        return jdbcTemplate.queryForObject("select name from employee where employee_id = ?",
                new Object[]{id}, String.class);
    }
}
