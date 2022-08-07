package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.implementations.DeleteByIdService;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.DeleteByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.GetAllServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
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
class DeleteByIdServiceTests {

  @Autowired
  private AddServiceInterface addService;

  @Autowired
  private DeleteByIdServiceInterface deleteByIdService;

  @Autowired
  private GetAllServiceInterface getAllService;

  @Test
  void shouldThrow_whenCtorInjectIsNull() {

    // Act && Assert
    assertThatThrownBy(() -> new DeleteByIdService(null)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldDelete_theDesignatedEntity_andNoReturnValue() {

    // Arrange
    SourceFormatDto dto1 = SourceFormatDto.builder().name("name1").build();
    SourceFormatDto dto2 = SourceFormatDto.builder().name("name2").build();
    SourceFormatDto dto1Result = this.addService.add(dto1);
    SourceFormatDto dto2Result = this.addService.add(dto2);

    // Act
    this.deleteByIdService.deleteById(dto1Result.getId());

    // Assert
    List<SourceFormatDto> list = this.getAllService.getAll();
    assertThat(list).isNotNull();
    assertThat(list).isNotEmpty();
    assertThat(list.size()).isEqualTo(1);
    SourceFormatDto dto = list.stream().filter(f -> f.getName().equals(dto2.getName()))
        .findFirst()
        .orElse(null);
    assertThat(dto).isNotNull();
    assertThat(dto.getName()).isEqualTo(dto2.getName());
    assertThat(dto.getId()).isGreaterThan(0);
  }

  @Test
  void shouldThrow_whenNoSuchEntity() {

    // Act & Assert
    assertThatThrownBy(() -> this.deleteByIdService.deleteById(100L)).isInstanceOf(EmptyResultDataAccessException.class);

  }

}