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

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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
    assertThat(sourceFormats).isNotNull();
    assertThat(sourceFormats.size()).isEqualTo(2);
    
    SourceFormat result1 = sourceFormats.stream()
        .filter(f -> f.getName().equals(sf1.getName()))
        .findFirst()
        .orElse(null);
    assertThat(result1).isNotNull();
    assertThat(result1.getId()).isGreaterThan(0);
    assertThat(result1.getName()).isEqualTo(sf1.getName());

    SourceFormat result2 = sourceFormats.stream()
        .filter(f -> f.getName().equals(sf2.getName()))
        .findFirst()
        .orElse(null);
    assertThat(result2).isNotNull();
    assertThat(result2.getId()).isGreaterThan(0);
    assertThat(result2.getName()).isEqualTo(sf2.getName());
  }
  
  @Test
  public void shouldReturnEmptyList_whenTheDatabaseIsEmpty() {
    
    // Act
    List<SourceFormat> result = (List<SourceFormat>) repository.findAll();
    
    // Assert
    assertThat(result.size()).isEqualTo(0);
  }

}
