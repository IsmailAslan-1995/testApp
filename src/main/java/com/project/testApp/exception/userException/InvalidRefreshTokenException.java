package com.project.testApp.exception.userException;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException() { super("Provided refresh token is invalid."); }
}