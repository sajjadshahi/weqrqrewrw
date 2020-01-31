package com.shm.core.exception;

public class PageAlreadyExistsException extends Exception {
    public PageAlreadyExistsException() {
        super("page already exists.");
    }
}
