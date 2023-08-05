package com.encyclopediagalactica.document.validation;

import lombok.Getter;

@Getter
public class Error {
    private final String message;
    private final String field;

    public Error(String message, String field) {
        this.message = message;
        this.field = field;
    }
}
