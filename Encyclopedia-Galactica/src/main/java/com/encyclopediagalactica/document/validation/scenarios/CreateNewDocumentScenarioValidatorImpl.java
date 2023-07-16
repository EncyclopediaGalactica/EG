package com.encyclopediagalactica.document.validation.scenarios;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.Error;
import com.encyclopediagalactica.document.validation.validators.LongValidatorInterface;
import com.encyclopediagalactica.document.validation.validators.StringValidatorInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class CreateNewDocumentScenarioValidatorImpl
    extends ScenarioValidatorAbstract
    implements ScenarioValidatorInterface<DocumentDto> {

    public CreateNewDocumentScenarioValidatorImpl(
        @NonNull StringValidatorInterface stringValidator,
        @NonNull LongValidatorInterface longValidator
    ) {
        super(stringValidator, longValidator);
    }

    @Override
    public void validateAndThrow(
        DocumentDto documentDto,
        ValidationMode validationMode) {
        this.validationMode = validationMode;
        executeValidationRules(documentDto);
    }

    @Override
    public void validateAndThrow(DocumentDto documentDto) {
        validateAndThrow(documentDto, ValidationMode.FULL);
    }

    private void executeValidationRules(DocumentDto documentDto) {
        idMustBeZero(documentDto);
        System.out.println("errors length 1: " + errors.size());
        nameMustNotBeNullOrEmpty(documentDto);
        System.out.println("errors length 2: " + errors.size());
        nameLengthMustBeGreaterOrEqualTo(3, documentDto);
        System.out.println("errors length 3: " + errors.size());
        evaluateValidationResult();
    }

    private void nameLengthMustBeGreaterOrEqualTo(int i, DocumentDto documentDto) {
        if (!isStringLongerOrEqualThan(documentDto.getName(), i)) {
            errors.add(new Error(
                "Name length must be " + i + " characters or longer",
                "Name"
            ));
        }

        checkWorkingMode();
    }

    private void nameMustNotBeNullOrEmpty(DocumentDto documentDto) {
        if (isStringNull(documentDto.getName())) {
            errors.add(new Error(
                "Name must not be null",
                "Name"
            ));
        }

        if (isStringNull(documentDto.getName()) && isStringEmpty(documentDto.getName())) {
            errors.add(new Error(
                "Name must not be empty",
                "Name"
            ));
        }

        checkWorkingMode();
    }

    private void idMustBeZero(DocumentDto documentDto) {
        if (documentDto == null) {
            throw new IllegalArgumentException("Argument is null");
        }

        if (!isLongEqualTo(documentDto.getId(), 0L)) {
            errors.add(
                new Error(
                    "Id must be zero",
                    "Id"
                ));
        }

        checkWorkingMode();
    }
}
