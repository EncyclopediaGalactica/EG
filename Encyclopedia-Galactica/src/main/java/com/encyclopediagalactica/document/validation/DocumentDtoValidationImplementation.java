package com.encyclopediagalactica.document.validation;

import br.com.fluentvalidator.context.ValidationResult;
import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.validators.CreateNewDocumentScenarioValidator;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class DocumentDtoValidationImplementation implements DocumentDtoValidationInterface {
    private final CreateNewDocumentScenarioValidator createNewDocumentScenarioValidator;

    public DocumentDtoValidationImplementation(
        @NonNull CreateNewDocumentScenarioValidator createNewDocumentScenarioValidator) {
        this.createNewDocumentScenarioValidator = createNewDocumentScenarioValidator;
    }

    @Override
    public void validateCreateNewDocumentScenario(DocumentDto dto) {
        ValidationResult validationResult = createNewDocumentScenarioValidator.validate(dto);
        if(!validationResult.isValid()) {
            prepareAndThrow(validationResult);
        }
    }

    private void prepareAndThrow(ValidationResult validationResult) {
        StringBuilder errors = new StringBuilder();
        validationResult.getErrors().stream().forEach(item -> {
            errors.append("Details of validation failure: ");
            errors.append("Message: " + item.getMessage());
            errors.append("Field: " + item.getField());
        });
        throw new ValidationExcecption(errors.toString());        
    }
}
