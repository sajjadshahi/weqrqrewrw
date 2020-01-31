package com.shm.core.exception;

public class PostNotFoundException extends Exception {
    public PostNotFoundException() {
        super("post not found!");
    }
}
