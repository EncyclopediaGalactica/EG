package com.encyclopediagalactica.services.sourceformat.services.sourceformat;

import java.util.List;
import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import com.encyclopediagalactica.services.sourceformat.mappers.interfaces.SourceFormatMapperInterface;
import com.encyclopediagalactica.services.sourceformat.repositories.SourceFormatPagingAndSortingRepository;
import com.encyclopediagalactica.sourceformat.dto.SourceFormatDto;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class GetAllService implements GetAllServiceInterface {

  private final SourceFormatPagingAndSortingRepository repository;
  private final SourceFormatMapperInterface sourceFormatMapper;

  public GetAllService(
      @NonNull SourceFormatPagingAndSortingRepository repository,
      @NonNull SourceFormatMapperInterface sourceFormatMapper) {

    this.repository = repository;
    this.sourceFormatMapper = sourceFormatMapper;
  }

  @Override
  public List<SourceFormatDto> getAll() {
    List<SourceFormat> sourceFormats = (List<SourceFormat>) repository.findAll();
    List<SourceFormatDto> sourceFormatDtos = sourceFormatMapper.mapSourceFormatsToSourceFormatDtos(sourceFormats);
    return sourceFormatDtos;
  }
}
