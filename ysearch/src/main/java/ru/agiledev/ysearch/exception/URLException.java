package ru.agiledev.ysearch.exception;

/**
 * Created by Anton Kirillov
 * Date: 8/12/11
 */
public class URLException extends Exception{
    public URLException() {
        super("URL not specified properly. Should init to continue.");
    }
}
