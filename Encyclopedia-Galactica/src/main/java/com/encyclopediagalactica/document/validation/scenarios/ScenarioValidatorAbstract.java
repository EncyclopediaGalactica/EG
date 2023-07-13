package com.encyclopediagalactica.document.validation.scenarios;

import com.encyclopediagalactica.document.validation.Error;
import com.encyclopediagalactica.document.validation.ValidationExcecption;
import com.encyclopediagalactica.document.validation.validators.LongValidatorInterface;
import com.encyclopediagalactica.document.validation.validators.StringValidatorInterface;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ScenarioValidatorAbstract {
    protected List<Error> errors = new ArrayList<>();
    protected ValidationMode validationMode;

    private final StringValidatorInterface stringValidator;
    private final LongValidatorInterface longValidator;

    public enum ValidationMode {
        FULL,
        THROW_AT_FIRST_ERROR
    }

    public ScenarioValidatorAbstract(
        @NonNull StringValidatorInterface stringValidator,
        @NonNull LongValidatorInterface longValidator) {
        this.stringValidator = stringValidator;
        this.longValidator = longValidator;
    }

    protected void evaluateValidationResult() {
        if (!errors.isEmpty()) {
            StringBuilder builder = new StringBuilder();
            builder.append("Errors happened. Amount of issues: " + errors.size());
            errors.forEach(error -> {
                builder.append("Field name: " + error.getField() + " ");
                builder.append("Error message: " + error.getMessage());
            });

            throw new ValidationExcecption(builder.toString());
        }
    }

    protected void checkWorkingMode() {
        if (validationMode == ValidationMode.THROW_AT_FIRST_ERROR
            && !errors.isEmpty()) {
            evaluateValidationResult();
        }
    }

    protected boolean isStringLongerOrEqualThan(String str, int length) {
        return stringValidator.isLongerOrEqualThan(str, length);
    }

    protected boolean isStringNull(String str) {
        return stringValidator.isNull(str);
    }

    protected boolean isStringEmpty(String str) {
        return stringValidator.isEmpty(str);
    }

    protected boolean isLongEqualTo(Long id, Long to) {
        return longValidator.equalsTo(id, to);
    }
}
