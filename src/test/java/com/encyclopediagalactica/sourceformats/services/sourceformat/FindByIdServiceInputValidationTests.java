package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.services.interfaces.FindByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.testdata.sourceformats.FindByIdInputValidationDataProviders;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
public class FindByIdServiceInputValidationTests extends FindByIdInputValidationDataProviders {

  @Autowired
  private FindByIdServiceInterface findByIdService;

  @ParameterizedTest
  @MethodSource("input_validation_service_level")
  void shouldThrow_whenInputIsZero(Long id) {

    // Act && Assert
    assertThatThrownBy(() -> findByIdService.findById(id)).isInstanceOf(InvalidDataAccessApiUsageException.class);
  }

}
