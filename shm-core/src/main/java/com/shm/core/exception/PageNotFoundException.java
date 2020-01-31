package com.shm.core.exception;

public class PageNotFoundException extends Exception {
    public PageNotFoundException() {
        super("page not found!");
    }
}
