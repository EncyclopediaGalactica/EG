package com.encyclopediagalactica.sourceformats.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceFormatDto {
  private Long id;
  private String name;

  //<editor-fold desc="ctor">
  public SourceFormatDto() {
  }

  public SourceFormatDto(Long id) {
    this.id = id;
  }

  public SourceFormatDto(Long id, String name) {
    this.id = id;
    this.name = name;
  }
  //</editor-fold>
}
