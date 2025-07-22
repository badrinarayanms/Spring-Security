package com.badri.SpringSecurity.controller;

import com.badri.SpringSecurity.model.Student;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {
    List<Student> students=new ArrayList<>(List.of(
            new Student(1,"badri","backend"),
            new Student(1,"surya","fullstack")
    ));

    @GetMapping("/students")
    @PreAuthorize("s_homepage")
    public List<Student> getall(){
//        System.out.println(authentication.getName());
        System.out.println("authentication.getName()");
        return  students;
    }

    @GetMapping("/csrf")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }
    @PostMapping("/students")
    void addstud(@RequestBody Student student){
        students.add(student);
    }
}
