package com.encyclopediagalactica.sourceformats.testdata;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class TestDataProviders {

  protected static Stream<Arguments> sourceFormat_new_entity_dto_inputValidationProvider() {
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
