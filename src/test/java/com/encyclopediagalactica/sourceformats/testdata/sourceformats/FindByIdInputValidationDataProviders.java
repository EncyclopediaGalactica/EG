package com.encyclopediagalactica.sourceformats.testdata.sourceformats;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class FindByIdInputValidationDataProviders {

  protected static Stream<Arguments> input_validation_repository_level() {
    return Stream.of(
        Arguments.of(null, true)
    );
  }

  protected static Stream<Arguments> input_validation_service_level() {
    return Stream.of(
        Arguments.of(null, true),
        Arguments.of(0L, true),
        Arguments.of(-1L, true)
    );
  }
}
