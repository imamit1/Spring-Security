package com.springsecurity.controller;

import com.springsecurity.model.Student;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management")
public class ManagementController {

    private final static List<Student> ADMIN = Arrays.asList(
            new Student(200, "Love"),
            new Student(300, "Kush")
    );

    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return ADMIN;
    }

    @PostMapping
    public void saveAdmin(@RequestBody Student student) {
        System.out.println("Admin Saved" + student);
    }

    @DeleteMapping
    public String deleteStudent() {
        System.out.println("Delete is Done");
        return "Delete operation is done";
    }

    @PutMapping
    public String ChangeStudent() {
        System.out.println("Put oprtation is done");
        return "Put operation is done";
    }

}
