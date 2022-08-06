package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.services.interfaces.DeleteByIdServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
public class DeleteByIdServiceInputValidationTests {

  @Autowired
  private DeleteByIdServiceInterface deleteByIdService;

  @Test
  void shouldThrow_whenInputIsInvalid() {

    // Act && Assert
    assertThatThrownBy(() -> {
      this.deleteByIdService.deleteById(0L);
    }).isInstanceOf(InvalidDataAccessApiUsageException.class);
  }

}
