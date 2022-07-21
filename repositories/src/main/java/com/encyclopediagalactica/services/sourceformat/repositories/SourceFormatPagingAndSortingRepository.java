package com.encyclopediagalactica.services.sourceformat.repositories;

import com.encyclopediagalactica.services.sourceformat.entities.SourceFormat;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SourceFormatPagingAndSortingRepository extends PagingAndSortingRepository<SourceFormat, Long> {
}
