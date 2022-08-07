package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.GetAllServiceInterface;
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
public class GetAllTests {

  @Autowired
  private GetAllServiceInterface getAllService;

  @Autowired
  private AddServiceInterface addService;

  @Test
  void shouldReturn_ListOfObjects() {

    // Arrange
    SourceFormatDto dto1 = SourceFormatDto.builder().name("asd").build();
    SourceFormatDto dto2 = SourceFormatDto.builder().name("asdd").build();
    SourceFormatDto dtoResult1 = this.addService.add(dto1);
    SourceFormatDto dtoResult2 = this.addService.add(dto2);

    // Act
    List<SourceFormatDto> result = this.getAllService.getAll();

    // Assert
    assertThat((long) result.size()).isEqualTo(2);
    SourceFormatDto result1 = result.stream()
        .filter(f -> f.getName().equals(dto1.getName()))
        .findFirst()
        .orElse(null);
    assertThat(result1).isNotNull();
    assertThat(result1.getId()).isGreaterThan(0);
    assertThat(result1.getName()).isEqualTo(dto1.getName());

    SourceFormatDto result2 = result.stream()
        .filter(f -> f.getName().equals(dto2.getName()))
        .findFirst()
        .orElse(null);
    assertThat(result2).isNotNull();
    assertThat(result2.getId()).isGreaterThan(0);
    assertThat(result2.getName()).isEqualTo(dto2.getName());
  }

  @Test
  void shouldReturn_emptyList_whenNoEntitiesInTheDb() {
    // Act
    List<SourceFormatDto> result = this.getAllService.getAll();

    // Assert
    assertThat(result).isNotNull();
    assertThat(result.size()).isEqualTo(0);
  }
}
