package com.benz.web.exception;

import com.benz.web.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DataNotFoundExceptionHandler{

    public ResponseEntity<ErrorMessage> toResponse(DataNotFoundException dx)
    {
        ErrorMessage errorMessage=new ErrorMessage(404,dx.getMessage(),"www.benz.com");
        return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
