package com.encyclopediagalactica.document.validation.scenarios;

public interface ScenarioValidatorInterface<T> {
    void validateAndThrow(T t, ScenarioValidatorAbstract.ValidationMode validationMode);
}
