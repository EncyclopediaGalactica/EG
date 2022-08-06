package com.encyclopediagalactica.sourceformats.E2E;

import static org.assertj.core.api.Assertions.assertThat;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.errors.ErrorMessages;
import com.encyclopediagalactica.sourceformats.testdata.TestDataProviders;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddServiceE2ETests extends TestDataProviders {

  @Autowired
  private WebTestClient webTestClient;

  @ParameterizedTest
  @MethodSource("sourceFormat_new_entity_dto_inputValidationProvider")
  public void shouldReturn_400_whenInputIsInvalid(String name) {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name(name).build();

    // Act && Assert
    this.webTestClient
        .post()
        .uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody(String.class).isEqualTo(ErrorMessages.VALIDATION_ERROR);
  }

  @Test
  public void shouldReturn_415_whenMediaTypeIsIncorrect() {

    // Act && Assert
    this.webTestClient
        .post()
        .uri("/sourceformats")
        .contentType(MediaType.APPLICATION_XML)
        .bodyValue(
            "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<advice xmlns=\"http://www.springframework.org/schema/cache\"></advice>")
        .exchange()
        .expectStatus()
        .isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @Test
  public void shouldReturn_201_whenANewEntityIsCreated() {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("name").build();

    // Act && Assert
    SourceFormatDto result = this.webTestClient
        .post()
        .uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(result).isNotNull();
    assertThat(result.getName()).isNotNull();
    assertThat(result.getName()).isEqualTo(dto.getName());
    assertThat(result.getId()).isGreaterThan(0);
  }
}
