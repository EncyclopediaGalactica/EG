package com.encyclopediagalactica.document.validation.scenarios;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.Error;
import com.encyclopediagalactica.document.validation.validators.LongValidatorInterface;
import com.encyclopediagalactica.document.validation.validators.StringValidatorInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service("modifyDocumentScenarioValidator")
public class ModifyDocumentScenarioValidatorImpl
    extends ScenarioValidatorAbstract<DocumentDto>
    implements ScenarioValidatorInterface<DocumentDto> {

    public ModifyDocumentScenarioValidatorImpl(
        @NonNull StringValidatorInterface stringValidator,
        @NonNull LongValidatorInterface longValidator) {
        super(stringValidator, longValidator);
    }

    @Override
    public void validateAndThrow(
        DocumentDto documentDto,
        ValidationMode validationMode) {
        this.validationMode = validationMode;
        executeValidation(documentDto);
        evaluateValidationResult();
    }

    @Override
    public void validateAndThrow(DocumentDto documentDto) {
        validateAndThrow(documentDto, ValidationMode.FULL);
    }

    private void executeValidation(DocumentDto documentDto) {
        documentIdMustBeGreaterOrEqualTo(3, documentDto.getId());
        nameMustNotBeNull(documentDto);
        nameMustNotBeEmpty(documentDto);
        nameMustBeLongerOrEqualTo(3, documentDto);
    }

    private void nameMustNotBeNull(DocumentDto documentDto) {
        if (isStringNull(documentDto.getName())) {
            errors.add(
                new Error(
                    "Name must not be null",
                    "name"
                ));
        }
        checkWorkingMode();
    }

    private void nameMustBeLongerOrEqualTo(int i, DocumentDto documentDto) {
        if (isStringNull(documentDto.getName()) && !isStringLongerOrEqualThan(documentDto.getName(), i)) {
            errors.add(new Error(
                "Name length must be equal or longer than " + i,
                "name"
            ));
        }
        checkWorkingMode();
    }

    private void nameMustNotBeEmpty(DocumentDto documentDto) {
        if (isStringNull(documentDto.getName()) && isStringEmpty(documentDto.getName())) {
            errors.add(
                new Error(
                    "Name must not be empty",
                    "name"
                ));
        }
        checkWorkingMode();
    }

    private void documentIdMustBeGreaterOrEqualTo(int i, Long l) {
        if (isLongEqualTo(l, 0L)) {
            errors.add(new Error(
                "Document Id must not be zero",
                "id"
            ));
        }
        checkWorkingMode();
    }
}
