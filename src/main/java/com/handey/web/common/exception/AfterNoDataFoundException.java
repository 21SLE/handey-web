package com.handey.web.common.exception;

public class AfterNoDataFoundException extends RuntimeException{
    public AfterNoDataFoundException() {
        super();
    }
    public AfterNoDataFoundException(String message) {
        super(message);
    }
}
