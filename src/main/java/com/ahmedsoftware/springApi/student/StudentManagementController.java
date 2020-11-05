package com.ahmedsoftware.springApi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    
    @Autowired
    StudentService studentService;
    
    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAllStudent();
    }
    
    @PostMapping
    public void registerNewStudent(@RequestBody  Student student){
        studentService.registerStudent(student);
    }
    
    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        studentService.deleteStudent(studentId);
    }
    
    @PutMapping(path = "/{studentId}")
    public Student updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody  Student student){
        return studentService.updateStudent(studentId,student);
    }
    
}
