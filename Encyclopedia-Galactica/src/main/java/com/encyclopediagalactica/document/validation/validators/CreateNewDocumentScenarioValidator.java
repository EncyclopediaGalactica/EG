package com.encyclopediagalactica.document.validation.validators;

import br.com.fluentvalidator.AbstractValidator;
import com.encyclopediagalactica.document.dto.DocumentDto;
import org.springframework.stereotype.Component;

import static br.com.fluentvalidator.predicate.ComparablePredicate.equalTo;
import static br.com.fluentvalidator.predicate.LogicalPredicate.not;
import static br.com.fluentvalidator.predicate.StringPredicate.stringEmptyOrNull;
import static br.com.fluentvalidator.predicate.StringPredicate.stringSizeGreaterThanOrEqual;

@Component
public class CreateNewDocumentScenarioValidator extends AbstractValidator<DocumentDto> {

    @Override
    public void rules() {
        ruleFor(DocumentDto::getId)
            .must(equalTo(0L))
            .withMessage("When creating new Document id must be 0")
            .withFieldName("Id");

        ruleFor(DocumentDto::getName)
            .must(stringSizeGreaterThanOrEqual(3))
            .withMessage("Name must be 3 or more characters long.")
            .withFieldName("name");
    }
}
