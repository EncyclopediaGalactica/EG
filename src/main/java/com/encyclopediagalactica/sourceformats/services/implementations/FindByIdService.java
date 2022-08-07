package com.encyclopediagalactica.sourceformats.services.implementations;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import com.encyclopediagalactica.sourceformats.entities.SourceFormat;
import com.encyclopediagalactica.sourceformats.mappers.interfaces.SourceFormatMapperInterface;
import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.interfaces.FindByIdServiceInterface;
import lombok.NonNull;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class FindByIdService implements FindByIdServiceInterface {

  private final SourceFormatMapperInterface mapper;
  private final SourceFormatRepository repository;

  public FindByIdService(
      @NonNull SourceFormatMapperInterface mapper,
      @NonNull SourceFormatRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public SourceFormatDto findById(Long id) {
    inputValidation(id);
    SourceFormat sf = repository.findById(id).orElseThrow();
    return mapper.mapSourceFormatToSourceFormatDto(sf);
  }

  private void inputValidation(Long id) {
    if (id == null || id <= 0) {
      throw new InvalidDataAccessApiUsageException("Entity Id cannot be null, 0 or negative number");
    }
  }
}
