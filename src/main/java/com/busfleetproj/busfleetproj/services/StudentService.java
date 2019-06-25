package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Student;
import com.busfleetproj.busfleetproj.repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        studentRepository.findAll()
                .forEach(students::add);

        return students;
    }

    public Student getStudent(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if (studentOptional.isPresent())
            return studentOptional.get();
        else
            return new Student();
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(int id, Student student) {
        studentRepository.save(student);
    }

    public void updateStudents(List<Student> students){
        studentRepository.saveAll(students);
    }


}
