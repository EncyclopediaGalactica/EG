package com.encyclopediagalactica.sourceformats.repositories.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@DataJpaTest
@ContextConfiguration(
    classes = com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication.class)
@TestPropertySource(
    properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
    })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
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
  
  @Test
  public void shouldReturnEmptyList_whenTheDatabaseIsEmpty() {
    
    // Act
    List<SourceFormat> result = (List<SourceFormat>) repository.findAll();
    
    // Assert
    assertThat(result.size()).isEqualTo(0);
  }

}
