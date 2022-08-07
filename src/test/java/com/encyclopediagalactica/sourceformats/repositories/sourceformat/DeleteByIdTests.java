package com.encyclopediagalactica.sourceformats.repositories.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
class DeleteByIdTests {

  @Autowired
  private SourceFormatRepository repository;

  @Test
  void shouldDelete() {

    // Arrange
    SourceFormat sf = new SourceFormat("asd");
    SourceFormat sfResult = repository.save(sf);

    // Act
    repository.deleteById(sfResult.getId());

    // Assert
    List<SourceFormat> getAll = (List<SourceFormat>) repository.findAll();
    assertThat(getAll.size()).isEqualTo(0);
  }

  @Test
  void shouldThrow_whenThereIsNoEntityToBeDeleted() {
    // Act && Assert
    assertThatThrownBy(() -> repository.deleteById(100L)).isInstanceOf(EmptyResultDataAccessException.class);
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  void shouldThrow_whenInputIsInvalid() {
    // Act && Assert
    assertThatThrownBy(() -> repository.deleteById(null)).isInstanceOf(InvalidDataAccessApiUsageException.class);
  }
}
