package com.encyclopediagalactica.sourceformats.services.implementations;

import com.encyclopediagalactica.sourceformats.repositories.SourceFormatRepository;
import com.encyclopediagalactica.sourceformats.services.interfaces.DeleteByIdServiceInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class DeleteByIdService implements DeleteByIdServiceInterface {
  
  private final SourceFormatRepository sourceFormatRepository;

  public DeleteByIdService(
      @NonNull SourceFormatRepository sourceFormatRepository) {
    this.sourceFormatRepository = sourceFormatRepository;
  }

  @Override
  public void deleteById(Long id) {
    this.sourceFormatRepository.deleteById(id);
  }
}
