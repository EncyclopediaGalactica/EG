package com.encyclopediagalactica.sourceformats.repositories.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.testdata.sourceformats.FindByIdInputValidationDataProviders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@DataJpaTest
@ContextConfiguration(
    classes = com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication.class)
@TestPropertySource(
    properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
    })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FindByIdTests extends FindByIdInputValidationDataProviders {

  @Autowired
  private SourceFormatRepository sourceFormatRepository;

  @Test
  void shouldReturn_optionalWithEntity_whenEntityExists() {

    // Arrange
    SourceFormat sf = new SourceFormat("name");
    SourceFormat sfResult = sourceFormatRepository.save(sf);

    // Act
    SourceFormat result = sourceFormatRepository.findById(sfResult.getId()).orElse(null);

    // Assert
    assertThat(result).isNotNull();
    assertThat(result).isInstanceOf(SourceFormat.class);
    assertThat(result.getId()).isGreaterThan(0);
    assertThat(result.getName()).isEqualTo(sf.getName());
  }

  @Test
  void shouldReturn_optionalEmpty_whenEntityDoesNotExist() {

    // Act
    SourceFormat result = sourceFormatRepository.findById(100L).orElse(null);

    // Assert
    assertThat(result).isNull();
  }

  @ParameterizedTest
  @MethodSource("input_validation_repository_level")
  void shouldThrow_whenInputIsInvalid(Long id) {

    // Act && Assert
    assertThatThrownBy(() -> sourceFormatRepository.findById(id)).isInstanceOf(Exception.class);
  }
}
