package com.encyclopediagalactica.validation;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.validation.ValidationExcecption;
import com.encyclopediagalactica.document.validation.scenarios.CreateNewDocumentScenarioValidatorImpl;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static com.encyclopediagalactica.document.validation.scenarios.ScenarioValidatorAbstract.ValidationMode.THROW_AT_FIRST_ERROR;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@SpringBootTest
public class CreateNewDocumentScenarioValidationTests {

    @Autowired
    private CreateNewDocumentScenarioValidatorImpl createNewDocumentScenarioValidator;

    private static Stream<Arguments> shouldThrow_whenInputIsInvalidProvider() {
        return Stream.of(
            Arguments.of(new DocumentDto.DocumentDtoBuilder().id(1L).name("asd").build()),
            Arguments.of(new DocumentDto.DocumentDtoBuilder().id(0L).name(null).build()),
            Arguments.of(new DocumentDto.DocumentDtoBuilder().id(0L).name("").build()),
            Arguments.of(new DocumentDto.DocumentDtoBuilder().id(0L).name(" ").build())
        );
    }

    @ParameterizedTest
    @MethodSource("shouldThrow_whenInputIsInvalidProvider")
    public void shouldThrow_whenInputIsInvalid(DocumentDto dto) {

        // Act && Act
        assertThatThrownBy(() -> {
            createNewDocumentScenarioValidator.validateAndThrow(dto, THROW_AT_FIRST_ERROR);
        }).isInstanceOf(ValidationExcecption.class);
    }
}
