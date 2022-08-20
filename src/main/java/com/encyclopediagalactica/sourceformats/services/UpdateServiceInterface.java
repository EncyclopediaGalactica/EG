package com.encyclopediagalactica.sourceformats.services;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;
import com.encyclopediagalactica.sourceformats.dto.SourceFormatDto;

public interface UpdateServiceInterface {
  /**
   * Updates the given SourceFormat entity.
   *
   * <p>
   * The provider DTO's Id value designates which entity should be updated.
   * The DTO further properties designate what is the new value of the properties.
   * </p>
   *
   * @param dto the DTO which contains the new values for properties
   * @return SourceFormatDto which contains the new properties
   * @throws ConstraintViolationException in case of validation error
   * @throws NullPointerException when null input is provided
   * @throws NoSuchElementException in case of no such entity in the database
   */
  SourceFormatDto updateById(SourceFormatDto dto);
}
