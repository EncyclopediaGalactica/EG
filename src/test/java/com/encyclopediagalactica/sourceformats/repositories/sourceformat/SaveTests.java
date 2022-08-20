package com.encyclopediagalactica.sourceformats.repositories.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@DataJpaTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@TestPropertySource(
    properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
    })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag("integration")
class SaveTests {

  @Autowired
  private SourceFormatRepository sourceFormatRepository;

  @Test
  void shouldSave_andReturnWithTheSavedOne() {

    // Arrange
    SourceFormat sourceFormat = new SourceFormat("asdd");

    // Act
    SourceFormat result = sourceFormatRepository.save(sourceFormat);

    // Assert
    assertThat(result.getId()).isGreaterThan(0);
    assertThat(result.getName()).isEqualTo(sourceFormat.getName());
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void shouldThrow_whenNullInputIsProvided() {

    // Act
    assertThatThrownBy(() -> sourceFormatRepository.save(null)).isInstanceOf(InvalidDataAccessApiUsageException.class);
  }

  @Test
  void shouldThrow_whenUniquesNameConstraintIsViolated() {
    // Arrange
    SourceFormat sf = new SourceFormat("name");
    SourceFormat sf2 = new SourceFormat("name");
    SourceFormat sfResult = sourceFormatRepository.save(sf);

    // Act
    assertThatThrownBy(() -> sourceFormatRepository.save(sf2)).isInstanceOf(DataIntegrityViolationException.class);
  }
}
