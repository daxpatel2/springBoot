package com.example.demo.student;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// This annotation handles spring boot exceptions globally
@ControllerAdvice
public class StudentExceptionController {

    // This annotation is used to handle the specific exceptions and
    // send the custom responses to the client
    @ExceptionHandler(value = StudentNotfoundException.class)
    public ResponseEntity<Object> exception(StudentNotfoundException exception) {
        throw new StudentNotfoundException();
    }

}
