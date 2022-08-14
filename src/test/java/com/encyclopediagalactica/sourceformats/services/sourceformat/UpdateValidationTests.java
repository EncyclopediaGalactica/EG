package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.NoSuchElementException;
import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.UpdateServiceInterface;
import com.encyclopediagalactica.sourceformats.testdata.sourceformats.UpdateEntityValidationDataProviders;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SuppressWarnings("unused")
@SpringBootTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@TestPropertySource(
    properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
    })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag("integration")
public class UpdateValidationTests extends UpdateEntityValidationDataProviders {

  @Autowired
  private UpdateServiceInterface updateService;

  @Autowired
  private AddServiceInterface addService;

  @ParameterizedTest
  @MethodSource("service_dto_updateVariousVersions")
  public void shouldUpdateEntity(String name) {
    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("aaa").build();
    SourceFormatDto dtoResult = addService.add(dto);

    dtoResult.setName(name);

    // Act
    SourceFormatDto updated = updateService.updateById(dtoResult);

    // Assert
    assertThat(updated).isNotNull();
    assertThat(updated.getId()).isEqualTo(dtoResult.getId());
    assertThat(updated.getName()).isEqualTo(dtoResult.getName());
  }

  @Test
  public void shouldThrow_whenThereIsNoSuchEntity() {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("name").id(100).build();

    // Act && Assert
    assertThatThrownBy(() -> updateService.updateById(dto))
        .isInstanceOf(NoSuchElementException.class);
  }
}