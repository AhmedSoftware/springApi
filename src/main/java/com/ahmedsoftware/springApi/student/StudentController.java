package com.ahmedsoftware.springApi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    
    
   @Autowired
   StudentService studentService;
    
    @GetMapping(path = "/{studentId}")
    @PreAuthorize("hasAuthority('student:read')")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return studentService.getStudentById(studentId);
    }
}
