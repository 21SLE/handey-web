package com.handey.web.common.exception;

public class ToDoNoDataFoundException extends RuntimeException {
    public ToDoNoDataFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public ToDoNoDataFoundException(String msg) {
        super(msg);
    }

    public ToDoNoDataFoundException() {
        super();
    }
}
