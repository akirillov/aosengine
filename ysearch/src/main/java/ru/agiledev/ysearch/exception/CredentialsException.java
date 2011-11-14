package ru.agiledev.ysearch.exception;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */
public class CredentialsException extends Exception {
    public CredentialsException() {
        super("Credentials not specified properly. Should init to continue.");
    }
}
