package com.shm.core.exception;

public class UserPackageNotFoundException extends Exception {
    public UserPackageNotFoundException() {
        super("user package not found.");
    }
}
