package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.AddServiceInterface;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
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
@Tag("integration")
class AddServiceTests {

  @Autowired
  private AddServiceInterface addService;

  @Test
  void shouldAdd() {

    // Arrange
    SourceFormatDto dto = new SourceFormatDto(100L, "asd");

    // Act
    SourceFormatDto result = this.addService.add(dto);

    // Assert
    assertThat(result.getId()).isGreaterThan(0);
    assertThat(result.getName()).isEqualTo("asd");
  }

  @Test
  void shouldThrow_whenUniqueNameConstraitIsViolated() {

    // Arrange
    SourceFormatDto dto1 = SourceFormatDto.builder().name("name").build();
    SourceFormatDto dto2 = SourceFormatDto.builder().name("name").build();
    SourceFormatDto dtoResult = addService.add(dto1);

    // Act
    assertThatThrownBy(() -> addService.add(dto2)).isInstanceOf(DataIntegrityViolationException.class);
  }
}
