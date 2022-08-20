package com.encyclopediagalactica.sourceformats.entities;

//<editor-fold desc="Imports">

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.encyclopediagalactica.sourceformats.entities.validation.SourceFormatAddValidationGroup;
import com.encyclopediagalactica.sourceformats.entities.validation.SourceFormatUpdateValidationGroup;
import lombok.Getter;
import lombok.Setter;
//</editor-fold>

/**
 * SourceFormat entity class.
 * <p>
 * The SourceFormat entity describes a document structure as a tree (like HTML, TXT or XML).
 * It also provides infrastructure to add additional property objects to the tree
 * storing additional information about the document structure. For example:
 * <li>separator element and patter</li>
 * <li>presentation related properties</li>
 * <li>validation rules</li>
 * </p>
 */
@Entity
@Table(name = "source_format")
@Getter
@Setter
public class SourceFormat {

  /**
   * Unique identifier
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Min(
      value = 1,
      groups = SourceFormatUpdateValidationGroup.class
  )
  private Long id;

  /**
   * Name of the SourceFormat
   */
  @Column(name = "name")
  @NotNull(
      groups = {
          SourceFormatAddValidationGroup.class,
          SourceFormatUpdateValidationGroup.class
      }
  )
  @NotEmpty(
      groups = {
          SourceFormatAddValidationGroup.class,
          SourceFormatUpdateValidationGroup.class
      }
  )
  @NotBlank(
      groups = {
          SourceFormatAddValidationGroup.class,
          SourceFormatUpdateValidationGroup.class
      }
  )
  @Size(
      min = 3,
      max = 255,
      groups = {
          SourceFormatAddValidationGroup.class,
          SourceFormatUpdateValidationGroup.class
      })
  private String name;

  //<editor-fold desc="ctor">
  public SourceFormat() {
  }

  public SourceFormat(Long id) {
    this.id = id;
  }

  public SourceFormat(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public SourceFormat(String name) {
    this.name = name;
  }

  //</editor-fold>
}
