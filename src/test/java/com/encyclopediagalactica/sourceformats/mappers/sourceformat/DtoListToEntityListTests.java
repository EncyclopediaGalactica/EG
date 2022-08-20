package com.encyclopediagalactica.sourceformats.mappers.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.SourceFormatMapperImpl;
import com.encyclopediagalactica.sourceformats.mappers.SourceFormatMapperInterface;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@SuppressWarnings("NewClassNamingConvention")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Tag("unit")
class DtoListToEntityListTests {

  private SourceFormatMapperInterface sut;

  @BeforeAll
  public void init() {
    sut = new SourceFormatMapperImpl();
  }

  @Test
  void shouldThrowWhen_InputIsNull() {
    // Act & Assert
    assertThatThrownBy(() -> sut.mapSourceFormatDtosToSourceFormats(null)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldMap() {

    // Arrange
    long firstId = 100L;
    long secondId = 101L;
    String firstName = "asd";
    String secondName = "asd2";
    SourceFormatDto sourceFormatDto1 = new SourceFormatDto(firstId, firstName);
    SourceFormatDto sourceFormatDto2 = new SourceFormatDto(secondId, secondName);
    List<SourceFormatDto> sourceFormatDtos = new ArrayList<>();
    sourceFormatDtos.add(sourceFormatDto1);
    sourceFormatDtos.add(sourceFormatDto2);

    // Act
    List<SourceFormat> result = sut.mapSourceFormatDtosToSourceFormats(sourceFormatDtos);

    // Result
    assertThat((long) result.size()).isEqualTo(2);
    SourceFormat first = result.stream().filter(f -> f.getId() == firstId)
        .findAny()
        .orElse(null);
    assertThat(first).isNotNull();
    assertThat(first.getId()).isEqualTo(firstId);
    assertThat(first.getName()).isEqualTo(firstName);
    SourceFormat second = result.stream().filter(f -> f.getId() == secondId)
        .findAny()
        .orElse(null);
    assertThat(second).isNotNull();
    assertThat(second.getId()).isEqualTo(secondId);
    assertThat(second.getName()).isEqualTo(secondName);
  }

}
