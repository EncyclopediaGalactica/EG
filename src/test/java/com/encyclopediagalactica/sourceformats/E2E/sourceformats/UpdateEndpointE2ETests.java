package com.encyclopediagalactica.sourceformats.E2E.sourceformats;

import static org.assertj.core.api.Assertions.assertThat;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.testdata.sourceformats.UpdateEntityValidationDataProviders;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@Tag("e2e")
public class UpdateEndpointE2ETests extends UpdateEntityValidationDataProviders {

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void shouldReturn_200_whenEntityIsUpdatedSuccessfully() {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("name").build();
    SourceFormatDto dtoResult = webTestClient.post().uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();

    // Act
    assertThat(dtoResult).isNotNull();
    SourceFormatDto modifier = SourceFormatDto.builder().id(dtoResult.getId()).name("asdasd").build();
    SourceFormatDto modifiedResult = webTestClient.put().uri("/sourceformats/" + dtoResult.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(modifier)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(SourceFormatDto.class)
        .returnResult().getResponseBody();

    // Assert
    assertThat(modifiedResult).isNotNull();
    assertThat(modifiedResult.getId()).isEqualTo(dtoResult.getId());
    assertThat(modifiedResult.getName()).isEqualTo(modifier.getName());

  }

  @Test
  public void shouldReturn_404_whenEntityDoesNotExists() {

    // Act && Assert
    SourceFormatDto modifier = SourceFormatDto.builder().id(1000).name("asdasd").build();
    webTestClient.put().uri("/sourceformats/1000")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(modifier)
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @ParameterizedTest
  @MethodSource("service_dto_inputValidationProvider")
  public void shouldReturn_400_whenProvidedDataIsInvalid(long id, String name) {

    // Act && Assert
    SourceFormatDto modifier = SourceFormatDto.builder().id(id).name(name).build();
    webTestClient.put().uri("/sourceformats/" + id)
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(modifier)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

  @Test
  void shouldReturn_400_whenUniqueNameConstraintIsViolated() {

    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("name").build();
    SourceFormatDto dtoResult = webTestClient.post().uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();

    SourceFormatDto dto2 = SourceFormatDto.builder().name("namem").build();
    SourceFormatDto dto2Result = webTestClient.post().uri("/sourceformats")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto2)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();

    // Act && Assert
    assertThat(dto2Result).isNotNull();
    SourceFormatDto updateDto = SourceFormatDto.builder().name("name").id(dto2Result.getId()).build();
    webTestClient.put().uri("/sourceformats/" + dto2Result.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(updateDto)
        .exchange()
        .expectStatus()
        .isBadRequest();
  }

}
