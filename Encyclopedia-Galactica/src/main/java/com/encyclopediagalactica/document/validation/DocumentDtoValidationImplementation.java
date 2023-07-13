package com.encyclopediagalactica.document.validation;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorAbstract;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class DocumentDtoValidationImplementation implements DocumentDtoValidationInterface {

    private final ScenarioValidatorInterface createNewDocumentScenarioValidator;

    public DocumentDtoValidationImplementation(
        @NonNull ScenarioValidatorInterface createNewDocumentScenarioValidator) {
        this.createNewDocumentScenarioValidator = createNewDocumentScenarioValidator;
    }

    @Override
    public void validateCreateNewDocumentScenario(
        DocumentDto dto) {
        createNewDocumentScenarioValidator.validateAndThrow(
            dto,
            ScenarioValidatorAbstract.ValidationMode.FULL);
    }

    @Override
    public void validateCreateNewDocumentScenario(
        DocumentDto dto,
        ScenarioValidatorAbstract.ValidationMode validationMode) {
        createNewDocumentScenarioValidator.validateAndThrow(
            dto,
            validationMode);
    }
}
