package com.encyclopediagalactica.document.validation.validators;

import org.springframework.stereotype.Service;

@Service
public class StringValidatorImpl implements StringValidator {

    @Override
    public boolean isLongerOrEqualThan(String str, int length) {
        return str != null && str.trim().length() >= length;
    }

    @Override
    public boolean isNull(String str) {
        return str == null;
    }

    @Override
    public boolean isEmpty(String str) {
        return str != null && str.trim().isEmpty();
    }
}
