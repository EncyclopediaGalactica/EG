package com.encyclopediagalactica.document.validation;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorAbstract;

public interface DocumentDtoValidationInterface {

    void validateCreateNewDocumentScenario(DocumentDto dto);
    
    void validateCreateNewDocumentScenario(DocumentDto dto, ScenarioValidatorAbstract.ValidationMode validationMode);
}
