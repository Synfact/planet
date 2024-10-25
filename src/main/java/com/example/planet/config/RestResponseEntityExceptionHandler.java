package com.example.planet.config;

import com.example.planet.exception.ExceptionForStarObject;
import com.example.planet.exception.ExceptionForDiscoverySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler  {

    @ExceptionHandler(ExceptionForDiscoverySource.class)
    public ResponseEntity<ErrorResponse> handleSourceException(ExceptionForDiscoverySource ex) {
         ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.NOT_FOUND.toString(), "My exception for discovery source.");
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

   @ExceptionHandler(ExceptionForStarObject.class)
   public ResponseEntity<ErrorResponse> handleObjectException(ExceptionForStarObject ex) {
       ErrorResponse errorResponse =  new ErrorResponse(HttpStatus.NOT_FOUND.toString(), "My exception for starObject.");
       return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
   }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), "An unexpected error occurred.");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static class ErrorResponse{
        private String errorCode;
        private String message;

        public ErrorResponse(String errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public String getErrorCode() {
            return errorCode;
        }
    }
}