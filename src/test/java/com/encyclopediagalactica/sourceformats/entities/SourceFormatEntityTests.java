package com.encyclopediagalactica.sourceformats.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("unit")
class SourceFormatEntityTests {

  @Test
  void shouldNotChangePropertyValues_viaCtor() {

    SourceFormat sf = new SourceFormat("name");
    assertThat(sf.getName()).isEqualTo("name");

    SourceFormat sf2 = new SourceFormat(100L, "name");
    assertThat(sf2.getId()).isEqualTo(100L);
    assertThat(sf2.getName()).isEqualTo("name");

    SourceFormat sf3 = new SourceFormat(100L);
    assertThat(sf3.getId()).isEqualTo(100L);
  }

  @Test
  void shouldNotChangePropertyValues_viaSetters() {

    SourceFormat sf1 = new SourceFormat();
    sf1.setName("name");
    sf1.setId(100L);
    assertThat(sf1.getName()).isEqualTo("name");
    assertThat(sf1.getId()).isEqualTo(100L);

  }

}
