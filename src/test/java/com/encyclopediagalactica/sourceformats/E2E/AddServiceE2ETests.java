package com.encyclopediagalactica.sourceformats.E2E;

import static org.assertj.core.api.Assertions.assertThat;

import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.errors.ErrorMessages;
import com.encyclopediagalactica.sourceformats.testdata.TestDataProviders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddServiceE2ETests extends TestDataProviders {
  
  @Autowired
  private WebTestClient webTestClient;
  
  @ParameterizedTest
  @MethodSource("sourceFormat_new_entity_dto_inputValidationProvider")
  public void shouldReturn_400_whenInputIsInvalid(String name) throws JsonProcessingException {
    
    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name(name).build();
    
    // Act && Assert
    this.webTestClient
        .post()
        .uri("/sourceformat")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .exchange()
        .expectStatus()
        .isBadRequest()
        .expectBody(String.class).isEqualTo(ErrorMessages.VALIDATION_ERROR);
    
  }
  
  @Test
  public void shouldReturn_201_whenANewEntityIsCreated() {
    
    // Arrange
    SourceFormatDto dto = SourceFormatDto.builder().name("name").build();
    
    // Act && Assert
    SourceFormatDto result = this.webTestClient
        .post()
        .uri("/sourceformat")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(dto)
        .exchange()
        .expectStatus()
        .isCreated()
        .expectBody(SourceFormatDto.class)
        .returnResult()
        .getResponseBody();

    assertThat(result.getName()).isNotNull();
    assertThat(result.getName()).isEqualTo(dto.getName());
    assertThat(result.getId()).isGreaterThan(0);
  }
}
