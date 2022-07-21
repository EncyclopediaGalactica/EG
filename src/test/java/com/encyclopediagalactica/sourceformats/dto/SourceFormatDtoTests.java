package com.encyclopediagalactica.sourceformats.dto;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SourceFormatDtoTests {
  
  @Test
  public void shouldMapWithoutChangingValues_ViaCtor() {
    
    // Arrange & Act
    SourceFormatDto dto = new SourceFormatDto(100L, "asd");
    
    // Assert
    assertThat(dto.getId()).isEqualTo(100);
    assertThat(dto.getName()).isEqualTo("asd");
  }
  
  @Test
  public void shouldMapWithoutChangingValues_ViaSetters() {
    // Arrange & Act
    SourceFormatDto dto = new SourceFormatDto();
    dto.setId(100L);
    dto.setName("asd");
    
    // Assert
    assertThat(dto.getId()).isEqualTo(100);
    assertThat(dto.getName()).isEqualTo("asd");
  }
}
