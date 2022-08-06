package com.encyclopediagalactica.sourceformats.E2E;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeleteByIdEndpointE2ETests {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  void shouldReturn_400_whenZeroValueSentToTheEndpoint() {
    // Act && Assert
    this.webTestClient
        .delete()
        .uri("/sourceformats/0")
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldReturn_204_whenTheEntityIsDeletedSuccessfully() {
    // Arrange
    SourceFormatDto dto1 = SourceFormatDto.builder().name("name").build();
    SourceFormatDto dto2 = SourceFormatDto.builder().name("name2").build();
    SourceFormatDto dto1Result = this.webTestClient
        .post()
        .uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto1)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();
    SourceFormatDto dto2Result = this.webTestClient
        .post()
        .uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto2)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult().getResponseBody();

    // Act
    assertThat(dto1Result).isNotNull();
    this.webTestClient
        .delete()
        .uri("/sourceformats/" + dto1Result.getId())
        .exchange()
        .expectStatus()
        .isNoContent();

    // Assert
    List<SourceFormatDto> result = this.webTestClient
        .get()
        .uri("/sourceformats")
        .exchange()
        .expectBodyList(SourceFormatDto.class)
        .returnResult().getResponseBody();
    assertThat(result).isNotNull();
    assertThat(result).isNotEmpty();
    assertThat(result.size()).isEqualTo(1);
    assertThat(dto2Result).isNotNull();
    SourceFormatDto res = result.stream().filter(f -> f.getName().equals(dto2Result.getName()))
        .findFirst()
        .orElse(null);
    assertThat(res).isNotNull();
    assertThat(res.getId()).isGreaterThan(0);
    assertThat(res.getName()).isEqualTo(dto2Result.getName());

  }

}
