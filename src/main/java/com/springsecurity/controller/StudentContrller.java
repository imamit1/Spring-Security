package com.springsecurity.controller;

import com.springsecurity.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api") //
public class StudentContrller {

    private final static List<Student> STUDENTS = Arrays.asList(
            new Student(101, "Ram"),
            new Student(102, "Shyam")
    );
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return STUDENTS;
    }

    @PostMapping
    public String saveStudent(@RequestBody Student student) {
        System.out.println("Post Student operation is done");
        return "Post student operation is done";
    }
}
