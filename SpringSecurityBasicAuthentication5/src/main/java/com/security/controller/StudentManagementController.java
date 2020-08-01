package com.security.controller;

import com.security.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId) {
        return STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId()))
                .findFirst().orElse(null);
    }

    @GetMapping()
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @PostMapping()
    public void registerNewStudent(@RequestBody Student student) {
        System.out.println("Student created...");
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable Integer studentId) {
        System.out.println("Student deleted...");
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable Integer studentId, @RequestBody Student student) {
        System.out.println("Student is updated...");
    }

}
