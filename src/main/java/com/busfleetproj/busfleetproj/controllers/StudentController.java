package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.entities.Student;
import com.busfleetproj.busfleetproj.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping("/students")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping("/students/{id}")
    public Student getStudent(@PathVariable int id) {
        return studentService.getStudent(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/students")
    public void addStudent(@RequestBody Student student) {
        studentService.addStudent(student);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/students/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/students/{id}")
    public void updateStudent(@PathVariable int id, @RequestBody Student student) {
        studentService.updateStudent(id, student);
    }

}
