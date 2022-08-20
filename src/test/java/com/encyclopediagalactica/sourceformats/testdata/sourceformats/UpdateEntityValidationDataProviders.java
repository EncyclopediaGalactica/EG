package com.encyclopediagalactica.sourceformats.testdata.sourceformats;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class UpdateEntityValidationDataProviders {

  protected static Stream<Arguments> service_dto_inputValidationProvider() {
    return Stream.of(
        Arguments.of(0, "asd", true),
        Arguments.of(1, null, true),
        Arguments.of(1, "as ", true),
        Arguments.of(1, "as", true),
        Arguments.of(1, "   ", true),
        Arguments.of(1, "", true),
        Arguments.of(1,
                     "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdv" +
                         "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdv" +
                         "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdas",
                     true)
    );
  }

  protected static Stream<Arguments> service_dto_updateVariousVersions() {
    return Stream.of(
        Arguments.of("asd", true),
        Arguments.of("asdasdasd", true),
        Arguments.of("asdas dasd", true)
    );
  }
}
