package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.mappers.implementations.SourceFormatMapper;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.implementations.FindByIdService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SuppressWarnings({"ConstantConditions", "SpringJavaInjectionPointsAutowiringInspection"})
@SpringBootTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@Tag("integration")
class FindByIdServiceCtorTests {

  @Autowired
  private SourceFormatRepository sourceFormatRepository;

  @Test
  void shouldThrow_whenInjectedRepositoryIsNull() {

    // Act && Assert
    assertThatThrownBy(() -> new FindByIdService(new SourceFormatMapper(), null))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenInjectedMapperIsNull() {

    // Act && Assert
    assertThatThrownBy(() -> new FindByIdService(null, sourceFormatRepository))
        .isInstanceOf(NullPointerException.class);
  }
}
