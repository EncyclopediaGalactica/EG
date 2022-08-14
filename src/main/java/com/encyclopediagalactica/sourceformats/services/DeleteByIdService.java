package com.encyclopediagalactica.sourceformats.services;

import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import lombok.NonNull;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.stereotype.Service;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class DeleteByIdService implements DeleteByIdServiceInterface {

  private final SourceFormatRepository sourceFormatRepository;

  public DeleteByIdService(@NonNull SourceFormatRepository sourceFormatRepository) {
    this.sourceFormatRepository = sourceFormatRepository;
  }

  @Override
  public void deleteById(Long id) {

    if (id == 0) {
      throw new InvalidDataAccessApiUsageException("The provided entity Id cannot be 0");
    }
    this.sourceFormatRepository.deleteById(id);
  }
}
