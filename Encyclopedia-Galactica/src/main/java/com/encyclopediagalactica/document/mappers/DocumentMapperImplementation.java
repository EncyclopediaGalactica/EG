package com.encyclopediagalactica.document.mappers;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.entities.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class DocumentMapperImplementation implements  DocumentMapperInterface{
    @Override
    public List<DocumentDto> mapDocumentsToDocumentDtos(List<Document> documents) {
        if(documents.isEmpty()) {
            return Collections.emptyList();
        }
        
        List<DocumentDto> result = new ArrayList<>();
        documents.forEach(item -> {
            result.add(this.mapDocumentToDocumentDto(item));
        });
        return result;
    }

    @Override
    public DocumentDto mapDocumentToDocumentDto(Document item) {
        DocumentDto result = new DocumentDto();
        
        result.setId(item.getId());
        
        if(item.getName() != null) {
            result.setName(item.getName());
        }
        
        if(item.getDesc() != null) {
            result.setDesc(item.getDesc());
        }
        
        return result;
    }

    @Override
    public Document mapDocumentDtoToDocument(DocumentDto documentDto) {
        Document result = new Document();
        
        if(documentDto.getName() != null) {
            result.setName(documentDto.getName());
        }
        
        if(documentDto.getDesc() != null) {
            result.setDesc(documentDto.getDesc());
        }
        
        return result;
    }
}
