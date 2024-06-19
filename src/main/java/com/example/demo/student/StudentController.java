package com.example.demo.student;

import com.example.demo.custom_exceptions.DataNotUpdatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * A class with all resources for our API
 */
// this annotation defines RESTful webservices
@RestController
@RequestMapping(path = "api/v1/")
public class StudentController {
//    @Value("${spring.application.name}")
    private final StudentService studentService;

    // this annotation is used for dependency injection
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Maps HTTP GET requests to specific handler methods. To generate a json keep return type to List.
     * Annotation param -> address subdomain
     * @return JSON object from HTTP GET request
     */
    @GetMapping("/students")
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    @PostMapping
    public ResponseEntity<Object> registerNewStudent(@RequestBody Student student) {
        try {
            studentService.addNewStudent(student);
            return new ResponseEntity<>("Student added successfully", HttpStatus.OK);
        } catch (IllegalStateException illegalStateException) {
            throw new DataNotUpdatedException();
        }
    }

    @DeleteMapping(path="{studentId}")
    public ResponseEntity<Object> deleteStudent(@PathVariable("studentId") Long id) {
        try {
            studentService.deleteStudent(id);
            return new ResponseEntity<>("Student deleted successfully", HttpStatus.OK);
        } catch (IllegalStateException illegalStateException) {
            throw new DataNotUpdatedException();
        }
    }

    @PutMapping(path="{studentId}")
    public ResponseEntity<Object> updateStudent(@PathVariable("studentId") Long sId,@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        try {
            studentService.updateStudent(sId,name,email);
            return new ResponseEntity<>("Student updated successfully",HttpStatus.OK);
        } catch(IllegalStateException illegalStateException) {
            throw new DataNotUpdatedException();
        }
    }
}