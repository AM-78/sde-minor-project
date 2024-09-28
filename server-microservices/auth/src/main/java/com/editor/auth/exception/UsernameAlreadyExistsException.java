package com.editor.auth.exception;

public class UsernameAlreadyExistsException extends Throwable {
    public UsernameAlreadyExistsException(String usernameAlreadyExists) {
        super(usernameAlreadyExists);
    }
}
