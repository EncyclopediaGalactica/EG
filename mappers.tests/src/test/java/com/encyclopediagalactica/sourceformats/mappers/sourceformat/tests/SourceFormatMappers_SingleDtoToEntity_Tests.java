package com.encyclopediagalactica.sourceformats.mappers.sourceformat.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.sourceformat.implementations.SourceFormatMapper;
import com.encyclopediagalactica.sourceformats.mappers.sourceformat.interfaces.SourceFormatMapperInterface;
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
