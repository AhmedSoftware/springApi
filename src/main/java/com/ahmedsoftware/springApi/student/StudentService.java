package com.ahmedsoftware.springApi.student;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    
    public  List<Student> studentList;
    
    public StudentService() {
        this.studentList = new ArrayList<>();
        studentList.add(new Student(1,"Ahmed ali"));
        studentList.add(new Student(2,"galiss ali"));
        studentList.add(new Student(3,"Azad ali"));
    }
    
    public List<Student> getAllStudent(){
        return studentList;
    }
    
    public Student getStudentById(Integer studentId){
        return studentList.stream()
                .filter(student -> studentId.equals(student.getStudentId()))
                .findFirst()
                .orElseThrow(()->new IllegalStateException("student "+studentId+"does not exist"));
    }
    
    public Student registerStudent(Student student){
        student.setStudentId(studentList.size()+1);
        studentList.add(student);
        System.out.println("register :" + student );
        return student;
    }
    
    public Student updateStudent(Integer studentId,Student student){
        Student _student = getStudentById(studentId);
        int indexStudent = studentList.indexOf(_student);
        _student.setStudentName(student.getStudentName());
        studentList.set(indexStudent,_student);
        System.out.println("update :" + student );
        return _student;
    }
    
    public void deleteStudent(Integer studentId){
        Student student = getStudentById(studentId);
        System.out.println("delete :" + student );
        studentList.remove(student);
    }
}
