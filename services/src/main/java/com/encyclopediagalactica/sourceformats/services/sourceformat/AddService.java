package com.encyclopediagalactica.sourceformats.services.sourceformat;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.sourceformat.interfaces.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformats.repositories.sourceformat.SourceFormatRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class AddService implements AddServiceInterface {

  private final SourceFormatRepository repository;
  private final SourceFormatMapperInterface mapper;

  public AddService(
      @NonNull SourceFormatRepository repository,
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
