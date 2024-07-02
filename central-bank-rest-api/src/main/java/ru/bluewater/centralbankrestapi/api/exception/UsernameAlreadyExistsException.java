package ru.bluewater.centralbankrestapi.api.exception;

public class UsernameAlreadyExistsException extends Exception {
    public UsernameAlreadyExistsException() { super("Username already exist");}
}
