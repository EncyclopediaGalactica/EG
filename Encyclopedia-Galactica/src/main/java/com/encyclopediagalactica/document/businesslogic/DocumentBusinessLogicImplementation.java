package com.encyclopediagalactica.document.businesslogic;

import com.encyclopediagalactica.document.dto.DocumentDto;
import com.encyclopediagalactica.document.entities.Document;
import com.encyclopediagalactica.document.mappers.DocumentMapperInterface;
import com.encyclopediagalactica.document.repositories.DocumentRepository;
import com.encyclopediagalactica.document.validation.DocumentDtoValidationInterface;
import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class DocumentBusinessLogicImplementation implements DocumentBusinessLogicInterface {
    private final DocumentRepository documentRepository;
    private final DocumentMapperInterface documentMapper;
    private final DocumentDtoValidationInterface documentDtoValidation;

    public DocumentBusinessLogicImplementation(
        @NonNull DocumentRepository documentRepository,
        @NonNull DocumentMapperInterface documentMapper,
        @NonNull DocumentDtoValidationInterface documentDtoValidation) {
        this.documentRepository = documentRepository;
        this.documentMapper = documentMapper;
        this.documentDtoValidation = documentDtoValidation;
    }

    @Override
    public List<DocumentDto> getDocuments() {
        List<Document> documents = StreamSupport.stream(
                documentRepository.findAll().spliterator(), false)
            .toList();
        List<DocumentDto> documentDtos = documentMapper.mapDocumentsToDocumentDtos(documents);
        return documentDtos;
    }

    @Override
    public DocumentDto getDocument(Long id) {
        Document document = documentRepository.findById(id).get();
        DocumentDto result = documentMapper.mapDocumentToDocumentDto(document);
        return result;
    }

    @Override
    public DocumentDto createDocument(DocumentDto documentDto) {
        documentDtoValidation.validateCreateNewDocumentScenario(documentDto);
        Document document = documentMapper.mapDocumentDtoToDocument(documentDto);
        Document result = documentRepository.save(document);
        DocumentDto resultDto = documentMapper.mapDocumentToDocumentDto(result);
        return resultDto;
    }

    @Override
    public DocumentDto modifyDocument(Long documentId, DocumentDto documentDto) {
        documentDtoValidation.validateModifyDocumentScenario(documentDto);
        Document exampleModifiedDocument = documentMapper.mapDocumentDtoToDocument(documentDto);
        Document toBeModifiedDocument = documentRepository.findById(documentId).orElseThrow();

        modifyDocumentUpdateFields(toBeModifiedDocument, exampleModifiedDocument);

        Document result = documentRepository.save(toBeModifiedDocument);
        DocumentDto resultDto = documentMapper.mapDocumentToDocumentDto(result);
        return resultDto;
    }

    private static void modifyDocumentUpdateFields(Document toBeModifiedDocument, Document exampleModifiedDocument) {
        if (!toBeModifiedDocument.getName().equals(exampleModifiedDocument.getName())) {
            toBeModifiedDocument.setName(exampleModifiedDocument.getName());
        }

        if (!toBeModifiedDocument.getDesc().equals(exampleModifiedDocument.getDesc())) {
            toBeModifiedDocument.setDesc(exampleModifiedDocument.getDesc());
        }
    }
}
