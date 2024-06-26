package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring application should follow this pipeline:
 * Browser -> Controller -> Service -> Repository -> Database
 */
@Repository
interface StudentRepository extends JpaRepository<Student,Long> {

    //SELECT Start FROM STUDENT WHERE EMAIL ---->
    Optional<Student> findStudentByEmail(String email);
}
