package com.encyclopediagalactica.services.sourceformat.services.sourceformat;

import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import com.encyclopediagalactica.services.sourceformat.mappers.SourceFormatMapperInterface;
import com.encyclopediagalactica.services.sourceformat.repositories.SourceFormatPagingAndSortingRepository;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;
import lombok.NonNull;

public class AddService implements AddServiceInterface {

  private final SourceFormatPagingAndSortingRepository repository;
  private final SourceFormatMapperInterface mapper;

  public AddService(
      @NonNull SourceFormatPagingAndSortingRepository repository,
      @NonNull SourceFormatMapperInterface mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public SourceFormatDto add(
      @NonNull SourceFormatDto dto) {
    SourceFormat sourceFormat = mapper.mapSourceFormatDtoToSourceFormat(dto);
    // validate
    SourceFormat result = repository.save(sourceFormat);
    SourceFormatDto resultDto = mapper.mapSourceFormatToSourceFormatDto(result);
    return resultDto;
  }
}
