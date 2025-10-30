package com.notes.notes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorResponse {
    private int status;
    private String message;
    private long timestamp;

    public ErrorResponse(int status, String message, long timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }

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


    @ControllerAdvice
    public class GlobalExceptionHandler {

        // Maneja la excepción cuando una tarea no se encuentra
        @ExceptionHandler(NoteNotFoundException.class)
        public ResponseEntity<ErrorResponse> handleNoteNotFound(NoteNotFoundException ex) {
            // Código 404 (Not Found)
            HttpStatus status = HttpStatus.NOT_FOUND;

            // 1. Crea el objeto ErrorResponse
            ErrorResponse error = new ErrorResponse(status.value(), ex.getMessage(), getTimestamp());

            // 2. Devuelve ResponseEntity, que convierte 'error' a JSON y establece el 404
            return new ResponseEntity<>(error, status);
        }
    }


    public class NoteNotFoundException extends RuntimeException {

        // Constructor
        public NoteNotFoundException(Long id) {
            super("No se pudo encontrar la tarea con ID: " + id);
        }
    }
}