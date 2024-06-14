package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        // ideally this should come from database
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()) {
            throw new IllegalStateException("email already exists, hence student probably exists in database");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        boolean studentExists = studentRepository.existsById(id);
        if(!studentExists) {
            throw new IllegalStateException("student doesnt exist "+id);
        }
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Long studentId, String email, String name) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("student with id: "+ studentId + " does not exist in the database"));

        if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)) {

            student.setName(name);
        }

        if(email!= null && !email.isEmpty() && !Objects.equals(student.getEmail(), name)) {
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()) {
                throw new IllegalStateException("email is taken");
            }
            student.setEmail(email);
        }
    }
}

