package com.ms.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ms.userservice.constants.APIResponse;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse> handlerNotFoundException(ResourceNotFoundException e) {
        String message = e.getMessage();
        APIResponse response = APIResponse.builder().message(message).success(true).status(HttpStatus.NOT_FOUND)
                .build();
        return new ResponseEntity<APIResponse>(response, HttpStatus.NOT_FOUND);
    }
}
