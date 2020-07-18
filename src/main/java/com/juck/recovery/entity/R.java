package com.juck.recovery.entity;

public class R<T> {
    private String code;
    private String message;
    private T data;

    public R success(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;

        return this;
    }

    public R success(String code, String message) {
        this.code = code;
        this.message = message;

        return this;
    }

    public R fail(String code, String message) {
        this.code = code;
        this.message = message;

        return this;
    }
}
