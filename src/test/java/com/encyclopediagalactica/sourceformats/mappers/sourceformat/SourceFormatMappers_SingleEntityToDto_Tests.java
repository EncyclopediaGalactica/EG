package com.encyclopediagalactica.sourceformats.mappers.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.implementations.SourceFormatMapper;
import com.encyclopediagalactica.sourceformats.mappers.interfaces.SourceFormatMapperInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SourceFormatMappers_SingleEntityToDto_Tests {

  private SourceFormatMapperInterface sut;

  @BeforeAll
  public void init() {
    sut = new SourceFormatMapper();
  }

  @Test
  public void shouldThrowWhen_InputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> {
      sut.mapSourceFormatToSourceFormatDto(null);
    }).isInstanceOf(NullPointerException.class);
  }

  @Test
  public void shouldMap() {
    // Arrange
    long id = 100L;
    String name = "asd";
    SourceFormat sourceFormat = new SourceFormat(id, name);

    // Act
    SourceFormatDto dto = sut.mapSourceFormatToSourceFormatDto(sourceFormat);

    // Assert
    assertThat(dto.getId()).isEqualTo(id);
    assertThat(dto.getName()).isEqualTo(name);
  }
}
