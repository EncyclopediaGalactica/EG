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
public class GetAllServiceE2ETests {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void shouldReturn_200_and_listOfDtos_whenThereIsDataInTheDatabase() {

    // Arrange
    SourceFormatDto dto1 = SourceFormatDto.builder().name("name1").build();
    SourceFormatDto dto2 = SourceFormatDto.builder().name("name2").build();

    this.webTestClient.post().uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto1)
        .exchange()
        .expectStatus()
        .isCreated();
    this.webTestClient.post().uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto2)
        .exchange()
        .expectStatus()
        .isCreated();

    // Act && Assert
    List<SourceFormatDto> result = this.webTestClient.get().uri("/sourceformats")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(result).isNotNull();
    assertThat(result.size()).isEqualTo(2);
  }

  @Test
  public void shouldReturn_200_and_emptyList_whenThereAreNoEntitiesInTheDatabase() {
    // Act && Assert
    List<SourceFormatDto> result = this.webTestClient.get().uri("/sourceformats")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(result).isNotNull();
    assertThat(result.size()).isEqualTo(0);
  }
}
