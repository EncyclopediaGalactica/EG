package com.encyclopediagalactica.sourceformats.mappers.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import com.encyclopediagalactica.services.sourceformat.mappers.implementations.SourceFormatMapper;
import com.encyclopediagalactica.services.sourceformat.mappers.interfaces.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SourceFormatMappers_SingleDtoToEntity_Tests {

  private SourceFormatMapperInterface sut;

  @BeforeAll
  public void init() {
    sut = new SourceFormatMapper();
  }

  @Test
  public void shouldThrowWhen_InputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> {
      sut.mapSourceFormatDtoToSourceFormat(null);
    }).isInstanceOf(NullPointerException.class);
  }

  @Test
  public void shouldMap() {

    // Arrange
    long id = 100L;
    String name = "asd";
    SourceFormatDto dto = new SourceFormatDto(id, name);

    // Act
    SourceFormat sourceFormat = sut.mapSourceFormatDtoToSourceFormat(dto);

    // Assert
    assertThat(sourceFormat.getId()).isEqualTo(id);
    assertThat(sourceFormat.getName()).isEqualTo(name);
  }

}
