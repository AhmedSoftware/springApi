package com.ahmedsoftware.springApi.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {
    
    @Autowired
    StudentService studentService;
    
    //hasRole('ROLE_') hasAnyRole('ROLE_1','ROLE_2') hasAuthority('permission') hasAnyAuthority('permission1','permission2')
    
    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN,ROLE_ADMINTRAINEE')")
    public List<Student> getAllStudents(){
        return studentService.getAllStudent();
    }
    
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public Student registerNewStudent(@RequestBody  Student student){
        return studentService.registerStudent(student);
    }
    
    @DeleteMapping(path = "/{studentId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        studentService.deleteStudent(studentId);
    }
    
    @PutMapping(path = "/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public Student updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody  Student student){
        return studentService.updateStudent(studentId,student);
    }
    
}
