package com.encyclopediagalactica.sourceformats.tests.mappers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.services.sourceformat.mappers.SourceFormatMapper;
import com.encyclopediagalactica.services.sourceformat.mappers.SourceFormatMapperInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SourceFormatMappersTests {

  private SourceFormatMapperInterface sut;

  @BeforeAll
  public void init() {
    sut = new SourceFormatMapper();
  }

  @Test
  public void shouldThrowWhenMapSingleEntityToDtoInputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> {
      sut.mapSourceFormatToSourceFormatDto(null);
    }).isInstanceOf(NullPointerException.class);
  }

  @Test
  public void shouldThrowWhenMapSingleDtoToEntityInputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> {
      sut.mapSourceFormatDtoToSourceFormat(null);
    }).isInstanceOf(NullPointerException.class);
  }

  @Test
  public void shouldThrowWhenMapListOfEntitiesToDtoInputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> {
      sut.mapSourceFormatsToSourceFormatDtos(null);
    }).isInstanceOf(NullPointerException.class);
  }

}
