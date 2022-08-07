package com.encyclopediagalactica.sourceformats.E2E.sourceformats;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class FindByIdEndpointE2ETests {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldReturn_404_whenNoSuchEntity() {

    // Act && Assert
    webTestClient.get()
        .uri("/sourceformats/100")
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  void shouldReturn_200_andTheDesignatedEntity_whenThereIsSuchAnEntity() {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("name").build();
    SourceFormatDto dtoResult = webTestClient.post()
        .uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult().getResponseBody();

    // Act
    assertThat(dtoResult).isNotNull();
    assertThat(dtoResult.getId()).isGreaterThan(0);
    SourceFormatDto result = webTestClient.get()
        .uri("/sourceformats/" + dtoResult.getId())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(SourceFormatDto.class)
        .returnResult().getResponseBody();

    assertThat(result).isNotNull();
    assertThat(result.getId()).isGreaterThan(0);
    assertThat(result.getName()).isEqualTo(dto.getName());
  }

  @ParameterizedTest
  @MethodSource("invalid_input")
  void shouldReturn_400_whenInputIsInvalid(Long id) {
    // Act && Assert
    webTestClient.get().uri("/sourceformats/" + id)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  protected static Stream<Arguments> invalid_input() {
    return Stream.of(
        Arguments.of(0L, true),
        Arguments.of(-1L, true)
    );
  }
}
