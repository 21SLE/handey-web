package com.handey.web.common.exception;

public class FwNoDataFoundException extends RuntimeException{
    public FwNoDataFoundException() {
        super();
    }
    public FwNoDataFoundException(String message) {
        super(message);
    }
}
