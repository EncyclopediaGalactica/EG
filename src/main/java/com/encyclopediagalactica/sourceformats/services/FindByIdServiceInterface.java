package com.encyclopediagalactica.sourceformats.services;

import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * FindByIdService Interface
 * It provides an interface to retrieve SourceFormat Entity by its Id from the database.
 */
public interface FindByIdServiceInterface {

  /**
   * Returns with the designated entity.
   *
   * @param id the Id value of the entity
   * @return @type {SourceFormat} entity
   * @throws EmptyResultDataAccessException when no such entity
   * @throws java.util.NoSuchElementException when no such entity
   */
  SourceFormatDto findById(Long id);

}
