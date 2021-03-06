package com.encyclopediagalactica.sourceformats.mappers.sourceformat.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.sourceformat.implementations.SourceFormatMapper;
import com.encyclopediagalactica.sourceformats.mappers.sourceformat.interfaces.SourceFormatMapperInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SourceFormatMappers_EntityListToDtoList_Tests {

  private SourceFormatMapperInterface sut;

  @BeforeAll
  public void init() {
    sut = new SourceFormatMapper();
  }

  @Test
  public void shouldThrowWhen_InputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> {
      sut.mapSourceFormatsToSourceFormatDtos(null);
    }).isInstanceOf(NullPointerException.class);
  }

  @Test
  public void shouldMap() {

    // Arrange
    long firstId = 100L;
    long secondId = 101L;
    String firstName = "asd";
    String secondName = "asd2";
    SourceFormat sourceFormat1 = new SourceFormat(firstId, firstName);
    SourceFormat sourceFormat2 = new SourceFormat(secondId, secondName);
    List<SourceFormat> sourceFormats = new ArrayList<>();
    sourceFormats.add(sourceFormat1);
    sourceFormats.add(sourceFormat2);

    // Act
    List<SourceFormatDto> result = sut.mapSourceFormatsToSourceFormatDtos(sourceFormats);

    // Result
    assertThat(result.stream().count()).isEqualTo(2);
    SourceFormatDto first = result.stream().filter(f -> f.getId() == firstId)
        .findAny()
        .orElse(null);
    assertThat(first.getId()).isEqualTo(firstId);
    assertThat(first.getName()).isEqualTo(firstName);
    SourceFormatDto second = result.stream().filter(f -> f.getId() == secondId)
        .findAny()
        .orElse(null);
    assertThat(second.getId()).isEqualTo(secondId);
    assertThat(second.getName()).isEqualTo(secondName);
  }

}
