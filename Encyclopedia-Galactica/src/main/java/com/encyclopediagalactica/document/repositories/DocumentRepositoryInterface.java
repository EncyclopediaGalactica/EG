package com.encyclopediagalactica.document.repositories;

import com.encyclopediagalactica.document.entities.Document;
import org.springframework.data.repository.CrudRepository;

public interface DocumentRepositoryInterface extends CrudRepository<Document, Long> {
}
