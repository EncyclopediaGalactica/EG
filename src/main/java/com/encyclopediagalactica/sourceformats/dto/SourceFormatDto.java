package com.encyclopediagalactica.sourceformats.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * SourceFormatDto class.
 * <p>
 * This DTO class is used to transfer a selected SourceFormat object properties
 * to external services like UI.
 * </p>
 */
@Getter
@Setter
@Builder
public class SourceFormatDto {

  private Long id;

  @NotNull
  @NotEmpty
  @NotBlank
  @Size(
      min = 3,
      max = 255)
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
