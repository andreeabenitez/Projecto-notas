package com.notes.notes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorResponse {
    private int status;
    private String message;

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


//OMITO GETTER Y SETTER


    @ControllerAdvice
    public class GlobalExceptionHandler {

        // Maneja la excepción cuando una tarea no se encuentra
        @ExceptionHandler(TareaNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleTareaNotFound(TareaNotFoundException ex) {
            // Código 404 (Not Found)
            HttpStatus status = HttpStatus.NOT_FOUND;

            // 1. Crea el objeto ErrorResponse
            ErrorResponse error = new ErrorResponse(status.value(), ex.getMessage());

            // 2. Devuelve ResponseEntity, que convierte 'error' a JSON y establece el 404
            return new ResponseEntity<>(error, status);
        }
    }

    public class TareaNotFoundException extends RuntimeException {

        // Constructor
        public TareaNotFoundException(Long id) {
            super("No se pudo encontrar la tarea con ID: " + id);
        }
    }