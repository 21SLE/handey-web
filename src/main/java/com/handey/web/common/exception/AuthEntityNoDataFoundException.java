package com.handey.web.common.exception;

public class AuthEntityNoDataFoundException extends RuntimeException {
    public AuthEntityNoDataFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthEntityNoDataFoundException(String msg) {
        super(msg);
    }

    public AuthEntityNoDataFoundException() {
        super();
    }
}
