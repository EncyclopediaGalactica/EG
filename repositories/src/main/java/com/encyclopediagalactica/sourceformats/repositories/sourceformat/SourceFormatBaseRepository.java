package com.encyclopediagalactica.sourceformats.repositories.sourceformat;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

@NoRepositoryBean
public interface SourceFormatBaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
  <S extends T> S save(S entity);

  Iterable<T> findAll();
}
