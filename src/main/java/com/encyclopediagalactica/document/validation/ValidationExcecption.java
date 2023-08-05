package com.encyclopediagalactica.document.validation;

public class ValidationExcecption extends RuntimeException{
    public ValidationExcecption() {
    }

    public ValidationExcecption(String message) {
        super(message);
    }

    public ValidationExcecption(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationExcecption(Throwable cause) {
        super(cause);
    }

    public ValidationExcecption(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
