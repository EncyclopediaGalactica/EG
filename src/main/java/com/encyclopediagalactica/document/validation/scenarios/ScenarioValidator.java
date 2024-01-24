package com.encyclopediagalactica.document.validation.scenarios;

import com.encyclopediagalactica.document.validation.ValidationExcecption;

public interface ScenarioValidator<T> {

    /**
     * Executes validation and throws {@link ValidationExcecption} if the input is not fit for the rules
     *
     * @param t              the provided input for validation
     * @param validationMode validation mode
     */
    void validateAndThrow(T t, ScenarioValidatorAbstract.ValidationMode validationMode);

    /**
     * Executes validation and throws {@link ValidationExcecption} if the input is not fit for the rules. The validation
     * is executed using the FULL rule.
     *
     * @param t the provided input for validation
     */
    void validateAndThrow(T t);
}
