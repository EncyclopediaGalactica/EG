package com.encyclopediagalactica.sourceformats.mappers.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.implementations.SourceFormatMapper;
import com.encyclopediagalactica.sourceformats.mappers.interfaces.SourceFormatMapperInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@SuppressWarnings("NewClassNamingConvention")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
class SingleEntityToDtoTests {

  private SourceFormatMapperInterface sut;

  @BeforeAll
  public void init() {
    sut = new SourceFormatMapper();
  }

  @Test
  void shouldThrowWhen_InputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> sut.mapSourceFormatToSourceFormatDto(null)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldMap() {
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