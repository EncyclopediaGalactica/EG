package com.encyclopediagalactica.sourceformats.repositories.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

@DataJpaTest
@ContextConfiguration(
    classes = com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication.class)
public class FindAllTests {

  @Autowired
  private SourceFormatRepository repository;

  public FindAllTests() {
  }

  @Test
  public void shouldReturnAll() {

    // Arrange
    SourceFormat sf1 = new SourceFormat(100L, "asd");
    SourceFormat sf2 = new SourceFormat(200L, "asd2");
    this.repository.save(sf1);
    this.repository.save(sf2);

    // Act 
    List<SourceFormat> sourceFormats = (List<SourceFormat>) this.repository.findAll();

    // Assert
    assertThat(sourceFormats.size()).isEqualTo(2);
  }

}
