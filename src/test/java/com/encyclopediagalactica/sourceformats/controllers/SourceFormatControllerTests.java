package com.encyclopediagalactica.sourceformats.controllers;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.encyclopediagalactica.sourceformats.mappers.SourceFormatMapperImpl;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.AddServiceImpl;
import com.encyclopediagalactica.sourceformats.services.AddServiceInterface;
import com.encyclopediagalactica.sourceformats.services.DeleteByIdServiceImpl;
import com.encyclopediagalactica.sourceformats.services.DeleteByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.services.FindByIdServiceImpl;
import com.encyclopediagalactica.sourceformats.services.FindByIdServiceInterface;
import com.encyclopediagalactica.sourceformats.services.GetAllServiceImpl;
import com.encyclopediagalactica.sourceformats.services.GetAllServiceInterface;
import com.encyclopediagalactica.sourceformats.services.UpdateServiceImpl;
import com.encyclopediagalactica.sourceformats.services.UpdateServiceInterface;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SuppressWarnings({"ConstantConditions", "SpringJavaInjectionPointsAutowiringInspection"})
@Tag("integration")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SourceFormatControllerTests {

  @Autowired
  private SourceFormatRepository sourceFormatRepository;

  @Autowired
  private SourceFormatMapperImpl mapper;

  @Test
  void shouldThrow_whenGetAllService_ctorInjectIsNull() {
    // Arrange
    GetAllServiceInterface getAllService = null;
    AddServiceInterface addService = new AddServiceImpl(sourceFormatRepository, mapper);
    DeleteByIdServiceInterface deleteByIdService = new DeleteByIdServiceImpl(sourceFormatRepository);
    FindByIdServiceInterface findByIdService = new FindByIdServiceImpl(mapper, sourceFormatRepository);
    UpdateServiceInterface updateService = new UpdateServiceImpl(mapper, sourceFormatRepository);

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(
        getAllService,
        addService,
        deleteByIdService,
        findByIdService,
        updateService))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenAddService_ctorInjectIsNull() {
    // Arrange
    GetAllServiceInterface getAllService = new GetAllServiceImpl(sourceFormatRepository, mapper);
    AddServiceInterface addService = null;
    DeleteByIdServiceInterface deleteByIdService = new DeleteByIdServiceImpl(sourceFormatRepository);
    FindByIdServiceInterface findByIdService = new FindByIdServiceImpl(mapper, sourceFormatRepository);
    UpdateServiceInterface updateService = new UpdateServiceImpl(mapper, sourceFormatRepository);

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(
        getAllService,
        addService,
        deleteByIdService,
        findByIdService,
        updateService))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenDeleteByIdService_ctorInjectIsNull() {
    // Arrange
    GetAllServiceInterface getAllService = new GetAllServiceImpl(sourceFormatRepository, mapper);
    AddServiceInterface addService = new AddServiceImpl(sourceFormatRepository, mapper);
    DeleteByIdServiceInterface deleteByIdService = null;
    FindByIdServiceInterface findByIdService = new FindByIdServiceImpl(mapper, sourceFormatRepository);
    UpdateServiceInterface updateService = new UpdateServiceImpl(mapper, sourceFormatRepository);

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(
        getAllService,
        addService,
        deleteByIdService,
        findByIdService,
        updateService))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenFindByIdService_ctorInjectIsNull() {
    // Arrange
    GetAllServiceInterface getAllService = new GetAllServiceImpl(sourceFormatRepository, mapper);
    AddServiceInterface addService = new AddServiceImpl(sourceFormatRepository, mapper);
    DeleteByIdServiceInterface deleteByIdService = new DeleteByIdServiceImpl(sourceFormatRepository);
    FindByIdServiceInterface findByIdService = null;
    UpdateServiceInterface updateService = new UpdateServiceImpl(mapper, sourceFormatRepository);

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(
        getAllService,
        addService,
        deleteByIdService,
        findByIdService,
        updateService))
        .isInstanceOf(NullPointerException.class);
  }

  @Test
  void shouldThrow_whenUpdateService_ctorInjectIsNull() {
    // Arrange
    GetAllServiceInterface getAllService = new GetAllServiceImpl(sourceFormatRepository, mapper);
    AddServiceInterface addService = new AddServiceImpl(sourceFormatRepository, mapper);
    DeleteByIdServiceInterface deleteByIdService = new DeleteByIdServiceImpl(sourceFormatRepository);
    FindByIdServiceInterface findByIdService = new FindByIdServiceImpl(mapper, sourceFormatRepository);
    UpdateServiceInterface updateService = null;

    // Act && Assert
    assertThatThrownBy(() -> new SourceFormatController(
        getAllService,
        addService,
        deleteByIdService,
        findByIdService,
        updateService))
        .isInstanceOf(NullPointerException.class);
  }
}
