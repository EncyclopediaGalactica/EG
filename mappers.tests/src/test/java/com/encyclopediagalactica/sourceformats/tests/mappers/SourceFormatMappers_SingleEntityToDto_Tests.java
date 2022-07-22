package com.encyclopediagalactica.sourceformats.tests.mappers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import com.encyclopediagalactica.services.sourceformat.mappers.SourceFormatMapper;
import com.encyclopediagalactica.services.sourceformat.mappers.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;
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
