package com.encyclopediagalactica.sourceformats.services.implementations;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.interfaces.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.interfaces.GetAllServiceInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class GetAllService implements GetAllServiceInterface {

  private final SourceFormatRepository repository;
  private final SourceFormatMapperInterface sourceFormatMapper;

  public GetAllService(
      @NonNull SourceFormatRepository repository,
      @NonNull SourceFormatMapperInterface sourceFormatMapper) {

    this.repository = repository;
    this.sourceFormatMapper = sourceFormatMapper;
  }

  @Override
  public List<SourceFormatDto> getAll() {
    List<SourceFormat> sourceFormats = (List<SourceFormat>) repository.findAll();
    return sourceFormatMapper.mapSourceFormatsToSourceFormatDtos(sourceFormats);
  }
}
