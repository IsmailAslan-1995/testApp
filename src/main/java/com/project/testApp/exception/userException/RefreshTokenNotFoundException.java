package com.project.testApp.exception.userException;

public class RefreshTokenNotFoundException extends RuntimeException {
    public RefreshTokenNotFoundException() { super("Refresh token not found for user."); }
}