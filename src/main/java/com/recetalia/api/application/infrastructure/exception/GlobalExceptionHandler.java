package com.recetalia.api.application.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

/**
 * Global exception handler class that handles exceptions across the entire application.
 * It uses Spring's {@code @ControllerAdvice} to manage global exception handling.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

  /**
   * Handles {@link ResourceNotFoundException} thrown when a resource is not found in the system.
   *
   * @param ex the thrown {@link ResourceNotFoundException}
   * @param request the current {@link WebRequest} during which the exception was thrown
   * @return a {@link ResponseEntity} containing the error message and HTTP status code 404 (NOT FOUND)
   */
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
  }

  /**
   * Handles any other {@link Exception} thrown in the system.
   *
   * @param ex the thrown {@link Exception}
   * @param request the current {@link WebRequest} during which the exception was thrown
   * @return a {@link ResponseEntity} containing the error message and HTTP status code 500 (INTERNAL SERVER ERROR)
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("An error occurred: " + ex.getMessage());
  }
}
