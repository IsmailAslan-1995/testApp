package com.project.testApp.exception.userException;

public class RefreshTokenExpiredException extends RuntimeException {
    public RefreshTokenExpiredException() { super("Refresh token has expired."); }
}