package com.project.testApp.exception;

import com.project.testApp.model.dto.response.generalResponse.GeneralResponse;
import com.project.testApp.model.dto.response.generalResponse.Status;
import com.project.testApp.exception.userException.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GeneralResponse> handleBadCredentials(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new GeneralResponse(
                        new Status(HttpStatus.UNAUTHORIZED, "Invalid username or password")));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<GeneralResponse> handleAuthExceptions(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new GeneralResponse(
                        new Status(HttpStatus.UNAUTHORIZED, "Authentication failed")));
    }

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    public ResponseEntity<GeneralResponse> handleNotFound(RefreshTokenNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new GeneralResponse(new Status(HttpStatus.UNAUTHORIZED, ex.getMessage())));
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    public ResponseEntity<GeneralResponse> handleInvalid(InvalidRefreshTokenException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new GeneralResponse(new Status(HttpStatus.UNAUTHORIZED, ex.getMessage())));
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<GeneralResponse> handleExpired(RefreshTokenExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new GeneralResponse(new Status(HttpStatus.UNAUTHORIZED, ex.getMessage())));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GeneralResponse(new Status(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage())));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralResponse> handleUserNotFound(UserNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new GeneralResponse(new Status(HttpStatus.NOT_FOUND, ex.getMessage())));
    }

}
