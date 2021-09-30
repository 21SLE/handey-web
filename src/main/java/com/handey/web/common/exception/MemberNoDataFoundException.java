package com.handey.web.common.exception;

public class MemberNoDataFoundException extends RuntimeException {
    public MemberNoDataFoundException(String msg, Throwable t) {
        super(msg, t);
    }

    public MemberNoDataFoundException(String msg) {
        super(msg);
    }

    public MemberNoDataFoundException() {
        super();
    }
}
