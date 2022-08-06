package com.encyclopediagalactica.sourceformats.controllers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.mappers.implementations.SourceFormatMapper;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.implementations.AddService;
import com.encyclopediagalactica.sourceformats.services.implementations.DeleteByIdService;
import com.encyclopediagalactica.sourceformats.services.implementations.GetAllService;
import com.encyclopediagalactica.sourceformats.services.interfaces.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.DeleteByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.services.interfaces.GetAllServiceInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SourceFormatControllerTests {

  @Autowired
  private SourceFormatRepository sourceFormatRepository;

  @Autowired
  private SourceFormatMapper mapper;

  @Test
  void shouldThrow_whenGetAllService_ctorInjectIsNull() {
    // Arrange
    AddServiceInterface addService = new AddService(sourceFormatRepository, mapper);
    DeleteByIdServiceInterface deleteByIdService = new DeleteByIdService(sourceFormatRepository);
    GetAllServiceInterface getAllService = null;

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(getAllService, addService, deleteByIdService)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenAddService_ctorInjectIsNull() {
    // Arrange
    AddServiceInterface addService = null;
    DeleteByIdServiceInterface deleteByIdService = new DeleteByIdService(sourceFormatRepository);
    GetAllServiceInterface getAllService = new GetAllService(sourceFormatRepository, mapper);

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(getAllService, addService, deleteByIdService)).isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenDeleteByIdService_ctorInjectIsNull() {
    // Arrange
    AddServiceInterface addService = new AddService(sourceFormatRepository, mapper);
    DeleteByIdServiceInterface deleteByIdService = null;
    GetAllServiceInterface getAllService = new GetAllService(sourceFormatRepository, mapper);

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(getAllService, addService, deleteByIdService)).isInstanceOf(NullPointerException.class);
  }
}
