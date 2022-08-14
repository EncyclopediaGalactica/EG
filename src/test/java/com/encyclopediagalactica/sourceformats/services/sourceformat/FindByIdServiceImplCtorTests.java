package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.mappers.SourceFormatMapperImpl;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.FindByIdServiceImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SuppressWarnings({"ConstantConditions", "SpringJavaInjectionPointsAutowiringInspection"})
@SpringBootTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@Tag("unit")
class FindByIdServiceImplCtorTests {

  @Autowired
  private SourceFormatRepository sourceFormatRepository;

  @Test
  void shouldThrow_whenInjectedRepositoryIsNull() {

    // Act && Assert
    assertThatThrownBy(() -> new FindByIdServiceImpl(new SourceFormatMapperImpl(), null))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenInjectedMapperIsNull() {

    // Act && Assert
    assertThatThrownBy(() -> new FindByIdServiceImpl(null, sourceFormatRepository))
        .isInstanceOf(NullPointerException.class);
  }
}
