package com.encyclopediagalactica.sourceformats.repositories;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface SourceFormatBaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

  /**
   * Saves the provided entity in the database.
   * @param entity the entity with its properties
   * @return returns the newly created entity
   * @param <S> Generic type
   * 
   * @throws InvalidDataAccessApiUsageException when null input is provided
   */
  <S extends T> S save(S entity);

  /**
   * Returns all of the SourceFormat entities from the database.
   * When the database is empty it returns with an empty list.
   * @return List of SourceFormat
   */
  Iterable<T> findAll();

  /**
   * Deletes the marked entity from the database.
   * @param id Unique Identifier of the entity
   * 
   * @throws EmptyResultDataAccessException when no entity found
   * @throws InvalidDataAccessApiUsageException when null input is provided
   */
  void deleteById(ID id);
}
