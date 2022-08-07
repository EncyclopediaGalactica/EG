package com.encyclopediagalactica.sourceformats.repositories;

import java.util.Optional;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface SourceFormatBaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {

  /**
   * Saves the provided entity in the database.
   *
   * @param entity the entity with its properties
   * @param <S> Generic type
   * @return returns the newly created entity
   * @throws InvalidDataAccessApiUsageException when null input is provided
   */
  <S extends T> S save(S entity);

  /**
   * Returns all of the SourceFormat entities from the database.
   * When the database is empty it returns with an empty list.
   *
   * @return List of SourceFormat
   */
  Iterable<T> findAll();

  /**
   * Deletes the marked entity from the database.
   *
   * @param id Unique Identifier of the entity
   * @throws EmptyResultDataAccessException when no entity found
   * @throws InvalidDataAccessApiUsageException when null input is provided
   */
  void deleteById(ID id);

  /**
   * Returns an Optional which contains the entity if it exists.
   *
   * @param id must not be {@literal null}.
   * @return Optional including entity if exists
   */
  Optional<T> findById(ID id);
}
