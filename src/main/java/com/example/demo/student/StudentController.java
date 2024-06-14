package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id) {
        studentService.deleteStudent(id);
    }

    @PutMapping(path="{studentId}")
    public void updateStudent(@PathVariable("studentId") Long sId,@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
        studentService.updateStudent(sId,name,email);
    }
}