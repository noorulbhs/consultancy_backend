package com.altrevo.consultancy.exception;

import com.altrevo.consultancy.dto.ApiResponse;
import com.altrevo.consultancy.dto.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericException(Exception ex, WebRequest request) {
        log.error("Generic exception occurred: ", ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("INTERNAL_SERVER_ERROR")
                .message("An unexpected error occurred")
                .build();
        
        ApiResponse<Object> response = ApiResponse.error(errorDetails);
        response.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Object>> handleRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("Runtime exception occurred: ", ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("RUNTIME_ERROR")
                .message(ex.getMessage())
                .build();
        
        ApiResponse<Object> response = ApiResponse.error(errorDetails);
        response.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("Validation exception occurred: ", ex);
        
        List<ErrorDetails.FieldError> fieldErrors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            fieldErrors.add(ErrorDetails.FieldError.builder()
                    .field(error.getField())
                    .message(error.getDefaultMessage())
                    .build());
        }
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("VALIDATION_ERROR")
                .message("Validation failed")
                .details(fieldErrors)
                .build();
        
        ApiResponse<Object> response = ApiResponse.error(errorDetails);
        response.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        log.error("Bad credentials exception occurred: ", ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("INVALID_CREDENTIALS")
                .message("Invalid email or password")
                .build();
        
        ApiResponse<Object> response = ApiResponse.error(errorDetails);
        response.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.error("Access denied exception occurred: ", ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("FORBIDDEN")
                .message("Insufficient permissions")
                .build();
        
        ApiResponse<Object> response = ApiResponse.error(errorDetails);
        response.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }
    
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleNoHandlerFoundException(NoHandlerFoundException ex, WebRequest request) {
        log.error("No handler found exception occurred: ", ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("NOT_FOUND")
                .message("Resource not found")
                .build();
        
        ApiResponse<Object> response = ApiResponse.error(errorDetails);
        response.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
    
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Object>> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        log.error("Illegal argument exception occurred: ", ex);
        
        ErrorDetails errorDetails = ErrorDetails.builder()
                .code("BAD_REQUEST")
                .message(ex.getMessage())
                .build();
        
        ApiResponse<Object> response = ApiResponse.error(errorDetails);
        response.setPath(request.getDescription(false).replace("uri=", ""));
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
