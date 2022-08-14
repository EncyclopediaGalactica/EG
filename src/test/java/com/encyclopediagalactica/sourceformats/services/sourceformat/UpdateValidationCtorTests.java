package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.mappers.implementations.SourceFormatMapper;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.UpdateServiceImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SuppressWarnings({"ConstantConditions", "SpringJavaInjectionPointsAutowiringInspection"})
@SpringBootTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@Tag("unit")
public class UpdateValidationCtorTests {

  @Autowired
  private SourceFormatRepository repository;

  @Test
  public void shouldThrow_whenInjectedMapperIsNull() {

    // Act && Assert
    assertThatThrownBy(() -> new UpdateServiceImpl(null, repository))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  public void shouldThrow_whenInjectedRepositoryIsNull() {

    // Act && Assert
    assertThatThrownBy(() -> new UpdateServiceImpl(new SourceFormatMapper(), null))
        .isInstanceOf(NullPointerException.class);
  }
}