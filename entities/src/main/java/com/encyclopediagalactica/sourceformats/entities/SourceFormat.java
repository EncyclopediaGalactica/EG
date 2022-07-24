package com.encyclopediagalactica.sourceformats.entities;

//<editor-fold desc="Imports">

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
//</editor-fold>

@Entity
@Table(name = "source_format")
@Getter
@Setter
public class SourceFormat {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
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
  //</editor-fold>
}
