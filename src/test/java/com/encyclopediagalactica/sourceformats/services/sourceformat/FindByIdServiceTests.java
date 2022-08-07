package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.FindByIdServiceInterface;
import org.junit.jupiter.api.Test;
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
class FindByIdServiceTests {

  @Autowired
  private FindByIdServiceInterface findByIdService;

  @Autowired
  private AddServiceInterface addService;

  @Test
  void shouldReturn_withTheDesignatedEntity() {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("asd").build();
    SourceFormatDto dtoResult = addService.add(dto);

    // Act
    SourceFormatDto result = findByIdService.findById(dtoResult.getId());
    assertThat(result).isNotNull();
    assertThat(result.getId()).isEqualTo(dtoResult.getId());
    assertThat(result.getName()).isEqualTo(dtoResult.getName());
  }

  @Test
  void shouldThrow_whenNoSuchEntity() {

    // Act && Assert
    assertThatThrownBy(() -> findByIdService.findById(100L)).isInstanceOf(NoSuchElementException.class);
  }
}
