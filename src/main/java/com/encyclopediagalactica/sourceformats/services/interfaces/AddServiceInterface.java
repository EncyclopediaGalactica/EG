package com.encyclopediagalactica.sourceformats.services.interfaces;

import javax.validation.ConstraintViolationException;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

public interface AddServiceInterface {

  /**
   * Creates a new SourceFormat entity in the system based on the data
   * provided by the DTO object as parameter.
   * 
   * @param dto the DTO object containing properties of the new entity
   * @return a SourceFormatDto object containing properties of the newly created entity
   * @throws ConstraintViolationException in case of validation error
   * @throws InvalidDataAccessApiUsageException when null input is provided for the repository
   * @throws EmptyResultDataAccessException in case of no such entity in the database
   */
  SourceFormatDto add(SourceFormatDto dto);
}
