package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.validation.ConstraintViolationException;
import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.UpdateServiceInterface;
import com.encyclopediagalactica.sourceformats.testdata.sourceformats.UpdateEntityValidationDataProviders;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SuppressWarnings("unused")
@SpringBootTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@TestPropertySource(
    properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
    })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag("integration")
public class UpdateServiceInputValidationTests extends UpdateEntityValidationDataProviders {

  @Autowired
  private UpdateServiceInterface updateService;

  @ParameterizedTest
  @MethodSource("service_dto_inputValidationProvider")
  public void shouldThrow_whenInputIsInvalid(long id, String name) {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().id(id).name(name).build();

    // Act && Assert
    assertThatThrownBy(() -> updateService.updateById(dto))
        .isInstanceOf(ConstraintViolationException.class);
  }

  @Test
  public void shouldThrow_whenNullInputIsProvided() {

    // Act && Assert
    assertThatThrownBy(() -> updateService.updateById(null))
        .isInstanceOf(NullPointerException.class);
  }
}
