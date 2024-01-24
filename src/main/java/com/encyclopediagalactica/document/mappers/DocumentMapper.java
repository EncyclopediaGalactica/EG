package com.encyclopediagalactica.document.mappers;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.entities.Document;

import java.util.List;

public interface DocumentMapper {

    /**
     * Maps {@link List<Document>} to {@link List<DocumentDto>}.
     * 
     * @param documents provided {@link List<Document>}
     * @return list of {@link List<DocumentDto>}
     */
    List<DocumentDto> mapDocumentsToDocumentDtos(List<Document> documents);

    /**
     * Maps {@link Document} to {@link DocumentDto} object
     * @param item the provided {@link Document}
     * @return the mapped {@link DocumentDto}
     */
    DocumentDto mapDocumentToDocumentDto(Document item);

    /**
     * Maps {@link DocumentDto} to a {@link Document} object.
     * 
     * @param documentDto the {@link DocumentDto} to be mapped
     * @return the mapped {@link Document}
     */
    Document mapDocumentDtoToDocument(DocumentDto documentDto);
}
