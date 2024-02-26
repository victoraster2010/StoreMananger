package com.victoraster.StoreMananger.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.victoraster.StoreMananger.exceptions.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {

        ErrorResponse errorMessage = new ErrorResponse("Object Not Found", ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidFields.class)
    public ResponseEntity<ErrorResponse> handleInvalidFields(InvalidFields ex) {
        ErrorResponse errorResponse = new ErrorResponse("Invalid fields", ex.getMessage());
        return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
