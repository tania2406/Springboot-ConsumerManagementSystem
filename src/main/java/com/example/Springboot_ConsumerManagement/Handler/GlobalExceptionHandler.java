package com.example.Springboot_ConsumerManagement.Handler;

import com.example.Springboot_ConsumerManagement.Exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();
        // Parse the exception message or use ConstraintViolation to get detailed information
        exception.getConstraintViolations().forEach(violation -> {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(propertyPath, message);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        String errorMessage = "Database error occurred.";

        // Checking the exact field names in the exception message
        if (exception.getMessage().contains("(phoneno)")) {
            errorMessage = "Phone number already exists.";
        } else if (exception.getMessage().contains("(city)")) {
            errorMessage = "City already exists.";
        }
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NameNotFoundException.class)
    public ResponseEntity<Object> handleException(NameNotFoundException exception)
    {
        ResponseEntity<Object> entity = new ResponseEntity<>("Name Not Found...!!", HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(PhoneNumberNotFoundException.class)
    public ResponseEntity<Object> handleException(PhoneNumberNotFoundException exception)
    {
        ResponseEntity<Object> entity = new ResponseEntity<>("Phone Number Not Found...!!", HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<Object> handleException(CityNotFoundException exception)
    {
        ResponseEntity<Object> entity = new ResponseEntity<>("City Not Found...!!", HttpStatus.NOT_FOUND);
        return entity;
    }

    @ExceptionHandler(value = NoSuchConsumerExistsException.class)
    ResponseEntity<?> handleNoSuchConsumerExistsException(NoSuchConsumerExistsException ee)
    {
        String message =ee.getMessage();
        return new ResponseEntity<>(message ,HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value = ConsumerAlreadyExistsException.class)
    ResponseEntity<?> handleConsumerAlreadyExistsException(ConsumerAlreadyExistsException ee)
    {
        String message =ee.getMessage();
        return new ResponseEntity<>(message ,HttpStatus.INTERNAL_SERVER_ERROR);
    }




}
