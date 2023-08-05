package com.encyclopediagalactica.document.validation;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorAbstract;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorInterface;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DocumentDtoValidationImplementation implements DocumentDtoValidationInterface {

    @Qualifier("createNewDocumentScenarioValidator")
    private final ScenarioValidatorInterface createNewDocumentScenarioValidator;

    @Qualifier("modifyDocumentScenarioValidator")
    private final ScenarioValidatorInterface modifyDocumentScenarioValidator;

    public DocumentDtoValidationImplementation(
        @NonNull ScenarioValidatorInterface createNewDocumentScenarioValidator,
        @NonNull ScenarioValidatorInterface modifyDocumentScenarioValidator) {
        this.createNewDocumentScenarioValidator = createNewDocumentScenarioValidator;
        this.modifyDocumentScenarioValidator = modifyDocumentScenarioValidator;
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

    @Override
    public void validateModifyDocumentScenario(DocumentDto documentDto) {

    }

    @Override
    public void validateModifyDocumentScenario(DocumentDto documentDto, ScenarioValidatorAbstract.ValidationMode validationMode) {

    }
}
