package com.busfleetproj.busfleetproj.services;

import com.busfleetproj.busfleetproj.entities.Student;
import com.busfleetproj.busfleetproj.repos.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames={"students"})
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Cacheable
    public List<Student> getAllStudents() {

        log.info("Retrieving student objects from database");
        return studentRepository.findAll();
    }

    @Cacheable(key = "#id")
    public Student getStudent(int id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        log.info("Retrieving student object id#" + id + " from database");
        if (studentOptional.isPresent()) {
            log.info("Retrieving student object id#" + id + " from database");
            return studentOptional.get();
        } else {
            log.error("Student object id#" + id + " not found in database");
            return new Student();
        }
    }

    @Cacheable
    public List<Student> getStudents(List<Integer> studentIds) {
        log.info("Retrieving student objects from database");
        return studentRepository.findAllById(studentIds);
    }

    public void addStudent(Student student) {
        studentRepository.save(student);
        log.info("Saving student object to database");
    }

    @CacheEvict(key = "#id")
    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
        log.info("Deleting student object id#" + id + "from database");
    }

    @CachePut(key = "#id")
    public Student updateStudent(int id, Student student) {
        studentRepository.save(student);
        log.info("Updating student object id#" + id + " in database");
        return student;
    }

    @CacheEvict(allEntries = true)
    public void updateStudents(List<Student> students) {
        studentRepository.saveAll(students);
        log.info("Updating student objects in database");
    }
    @CacheEvict(allEntries = true)
    public void clearCache(){

    }


}
