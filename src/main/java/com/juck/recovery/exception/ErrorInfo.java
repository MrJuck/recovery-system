package com.juck.recovery.exception;

public class ErrorInfo extends RuntimeException {
    private String code;

    public ErrorInfo(String code, String message) {
        super(message);
        this.code = code;
    }

    public ErrorInfo(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ErrorInfo(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
