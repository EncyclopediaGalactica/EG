package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.validation.ConstraintViolationException;
import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.testdata.sourceformats.CreateNewEntityValidationDataProviders;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@TestPropertySource(
    properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
    })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AddServiceInputValidationTests extends CreateNewEntityValidationDataProviders {

  @Autowired
  private AddServiceInterface addService;

  @ParameterizedTest
  @MethodSource("sourceFormat_new_entity_dto_inputValidationProvider")
  void shouldThrow_whenInputIsInvalid(String name) {
    // Act && Assert
    assertThatThrownBy(() -> addService.add(SourceFormatDto.builder().name(name).build()))
        .isInstanceOf(ConstraintViolationException.class);
  }
}
