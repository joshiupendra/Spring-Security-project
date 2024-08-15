package com.seemay.springSec.controller;

import com.seemay.springSec.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    List<Student> students = new ArrayList<>(List.of(
            new Student(1, "Upendra", 70),
            new Student(2, "Seemay", 45),
            new Student(3, "Joshi", 56)
    ));

    @GetMapping()
    public List<Student> getStudent() {
        return students;
    }

    @PostMapping()
    public void addStudent(@RequestBody Student newStudent) {
        students.add(newStudent);
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");
    }
}
