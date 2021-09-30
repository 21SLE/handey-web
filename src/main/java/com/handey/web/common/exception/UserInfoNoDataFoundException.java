package com.handey.web.common.exception;

public class UserInfoNoDataFoundException extends RuntimeException {
    public UserInfoNoDataFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserInfoNoDataFoundException(String msg) {
        super(msg);
    }

    public UserInfoNoDataFoundException() {
        super();
    }
}
