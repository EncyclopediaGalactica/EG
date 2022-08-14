package com.encyclopediagalactica.sourceformats.services;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

public interface DeleteByIdServiceInterface {

  /**
   * Deletes the given SourceFormat from the system.
   *
   * @param id the unique identifier of the entity
   * @throws InvalidDataAccessApiUsageException when the input is null
   * @throws EmptyResultDataAccessException when there is no such entity
   */
  void deleteById(Long id);
}
