package com.encyclopediagalactica.document.validation.validators;

import org.springframework.stereotype.Service;

@Service
public class LongValidatorImpl implements LongValidatorInterface {
    @Override
    public boolean equalsTo(Long number, Long equalsTo) {
        return number != null && number.equals(equalsTo);
    }
}
