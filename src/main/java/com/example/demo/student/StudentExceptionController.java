package com.example.demo.student;

import com.example.demo.custom_exceptions.DataNotUpdatedException;
import com.example.demo.custom_exceptions.StudentNotfoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice annotation handles spring boot exceptions globally

/***
 * This class is our custom Exception Handler class where we can define @ExceptionHandler
 * annotations that give controller specific exceptions
 */
@ControllerAdvice
public class StudentExceptionController {

    // This annotation is used to handle the specific exceptions and
    // send the custom responses to the client
    //@ExceptionHandler is good for handling exceptions that happen within some controller
    @ExceptionHandler(value = StudentNotfoundException.class)
    public ResponseEntity<Object> exception(StudentNotfoundException exception) {
        return new ResponseEntity<>("Student not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = DataNotUpdatedException.class)
    public ResponseEntity<Object> runtimeExe(DataNotUpdatedException dnue) {
        return new ResponseEntity<>("Data was not able to be added/updated/deleted",HttpStatus.BAD_REQUEST);

    }

}
