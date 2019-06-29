package com.busfleetproj.busfleetproj.controllers;

import com.busfleetproj.busfleetproj.dto.StudentDTO;
import com.busfleetproj.busfleetproj.entities.Student;
import com.busfleetproj.busfleetproj.services.StudentService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@Api(value = "Student Management System")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View all student objects", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "View student object by id", response = Student.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public Student getStudent(
            @ApiParam(value = "Id of student object to retrieve", required = true)
            @PathVariable int id) {
        return studentService.getStudent(id);
    }

    @PostMapping
    @ApiOperation(value = "Add student object to database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void addStudent(
            @ApiParam(value = "Student object to add", required = true)
            @Valid @RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(studentDTO, student);
        studentService.addStudent(student);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete student object from database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void deleteStudent(
            @ApiParam(value = "Id of student to delete", required = true)
            @PathVariable int id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Update student object in database")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated object"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")})
    public void updateStudent(
            @ApiParam(value = "Id of student object to update", required = true)
            @PathVariable int id,
            @ApiParam(value = "Updated student object", required = true)
            @Valid @RequestBody StudentDTO studentDTO) {
        Student student = new Student();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(studentDTO, student);
        studentService.updateStudent(id, student);
    }

}
