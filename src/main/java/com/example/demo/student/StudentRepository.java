package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface StudentRepository extends JpaRepository<Student,Long> {

    //SELECT Start FROM STUDENT WHERE EMAIL ---->
    Optional<Student> findStudentByEmail(String email);
}
