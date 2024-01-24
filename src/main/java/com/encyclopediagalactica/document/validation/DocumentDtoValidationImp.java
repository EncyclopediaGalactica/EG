package com.encyclopediagalactica.document.validation;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorAbstract;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidator;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DocumentDtoValidationImp implements DocumentDtoValidation {

    @Qualifier("createNewDocumentScenarioValidator")
    private final ScenarioValidator createNewDocumentScenarioValidator;

    @Qualifier("modifyDocumentScenarioValidator")
    private final ScenarioValidator modifyDocumentScenarioValidator;

    public DocumentDtoValidationImp(
        @NonNull ScenarioValidator createNewDocumentScenarioValidator,
        @NonNull ScenarioValidator modifyDocumentScenarioValidator) {
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
