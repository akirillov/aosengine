package ru.agiledev.ysearch.exception;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */
public class AuthenticationException extends Exception {
    public AuthenticationException() {
        super("Authentication error. Invalid username or password.");
    }
}
