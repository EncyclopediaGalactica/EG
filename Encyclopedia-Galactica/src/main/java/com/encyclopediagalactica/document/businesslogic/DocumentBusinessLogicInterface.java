package com.encyclopediagalactica.document.businesslogic;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.entities.Document;

import java.util.List;

public interface DocumentBusinessLogicInterface {

    /**
     * Returns the list of {@link Document} entities.
     * 
     * @return list of {@link Document} entities
     */
    List<DocumentDto> getDocuments();

    /**
     * Returns the designated {@link Document} entity.
     * 
     * @param id identifier of the {@link Document} entity
     * @return instance of {@link Document} entity
     */
    DocumentDto getDocument(Long id);

    /**
     * Creates a {@link Document} based on the provided {@link DocumentDto} object.
     * 
     * @param documentDto the provided input object
     * @return {@link DocumentDto} representing the newly created {@link Document} entity
     */
    DocumentDto createDocument(DocumentDto documentDto);
}
