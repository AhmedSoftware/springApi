package com.ahmedsoftware.springApi.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {
    
    
    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1,"Azad ali"),
            new Student(2,"Galiss ali"),
            new Student(3,"Azad ali")
    );
    
    @GetMapping(path = "/{studentId}")
    public Student getStudent(@PathVariable("studentId") Integer studentId){
        return STUDENT_LIST.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()->new IllegalStateException("student "+studentId+" does not found"));
    }
}
