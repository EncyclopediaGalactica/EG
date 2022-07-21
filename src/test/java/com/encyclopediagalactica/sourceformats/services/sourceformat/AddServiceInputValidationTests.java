package com.encyclopediagalactica.sourceformats.services.sourceformat;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import javax.validation.ConstraintViolationException;
import java.util.stream.Stream;
import com.encyclopediagalactica.sourceformats.SourceFormatServiceApplication;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ContextConfiguration(classes = SourceFormatServiceApplication.class)
@TestPropertySource(
    properties = {
        "spring.jpa.hibernate.ddl-auto=create-drop"
    })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AddServiceInputValidationTests {

  @Autowired
  private AddServiceInterface addService;

  @ParameterizedTest
  @MethodSource("provideInvalidInputParameters")
  public void shouldThrow_whenInputIsInvalid(String name) {
    // Act && Assert
    assertThatThrownBy(() -> {
      addService.add(
          SourceFormatDto.builder().name(name).build()
      );
    }).isInstanceOf(ConstraintViolationException.class);
  }
  
  private static Stream<Arguments> provideInvalidInputParameters(){
    return Stream.of(
        Arguments.of(null, true),
        Arguments.of("", true),
        Arguments.of(" ", true),
        Arguments.of("as", true),
        Arguments.of("as ", true),
        Arguments.of(
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdv" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdv" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdas", true)
    );
  }
}
