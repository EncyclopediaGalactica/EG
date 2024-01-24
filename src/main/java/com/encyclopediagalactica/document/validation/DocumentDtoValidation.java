package com.encyclopediagalactica.document.validation;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorAbstract;

public interface DocumentDtoValidation {

    /**
     * Validates a {@link DocumentDto} object if it fits for the creating new document scenario. If the object is
     * invalid it throws exception
     *
     * @param dto the object to be validated
     * @throws ValidationExcecption
     */
    void validateCreateNewDocumentScenario(DocumentDto dto);

    /**
     * Validates a {@link DocumentDto} object if it fits for the creating new document scenario. If the object is
     * invalid it throws exception
     *
     * @param dto            the object to be validated
     * @param validationMode method of validation - only for testing purposes
     * @throws ValidationExcecption
     */
    void validateCreateNewDocumentScenario(
        DocumentDto dto,
        ScenarioValidatorAbstract.ValidationMode validationMode);

    /**
     * Validates a {@link DocumentDto} object if it fits for the modifing a document scenario. If the provided object is
     * invalid the validator throws {@link ValidationExcecption}
     *
     * @param documentDto the provided input
     * @throws ValidationExcecption
     */
    void validateModifyDocumentScenario(DocumentDto documentDto);

    /**
     * Validates a {@link DocumentDto} object if it fits for the modifing a document scenario. If the provided object is
     * invalid the validator throws {@link ValidationExcecption}. The validation mode defines if the validator throws at
     * first error or collects all possible errors.
     *
     * @param documentDto    the provided input
     * @param validationMode method of validation - only for testing purposes
     * @throws ValidationExcecption
     */
    void validateModifyDocumentScenario(
        DocumentDto documentDto,
        ScenarioValidatorAbstract.ValidationMode validationMode);
}
