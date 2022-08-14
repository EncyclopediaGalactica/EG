package com.encyclopediagalactica.sourceformats.dto;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.encyclopediagalactica.sourceformats.dto.validation.SourceFormatDTOAddValidationGroup;
import com.encyclopediagalactica.sourceformats.dto.validation.SourceFormatDTOUpdateValidationGroup;
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

  @Min(
      value = 1,
      groups = {
          SourceFormatDTOUpdateValidationGroup.class
      }
  )
  private long id;

  @NotNull(
      groups = {
          SourceFormatDTOAddValidationGroup.class,
          SourceFormatDTOUpdateValidationGroup.class
      }
  )
  @NotEmpty(
      groups = {
          SourceFormatDTOAddValidationGroup.class,
          SourceFormatDTOUpdateValidationGroup.class
      }
  )
  @NotBlank(
      groups = {
          SourceFormatDTOAddValidationGroup.class,
          SourceFormatDTOUpdateValidationGroup.class
      }
  )
  @Size(
      min = 3,
      max = 255,
      groups = {
          SourceFormatDTOAddValidationGroup.class,
          SourceFormatDTOUpdateValidationGroup.class
      })
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
