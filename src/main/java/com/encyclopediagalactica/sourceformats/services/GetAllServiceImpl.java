package com.encyclopediagalactica.sourceformats.services;

import java.util.List;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class GetAllServiceImpl implements GetAllServiceInterface {

  private final SourceFormatRepository repository;
  private final SourceFormatMapperInterface sourceFormatMapper;

  public GetAllServiceImpl(
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
