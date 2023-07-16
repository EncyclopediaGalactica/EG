package com.encyclopediagalactica.controllers;

import com.encyclopediagalactica.document.businesslogic.DocumentBusinessLogicInterface;
import com.encyclopediagalactica.document.dto.DocumentDto;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class DocumentController {

    private final DocumentBusinessLogicInterface documentBusinessLogic;

    public DocumentController(DocumentBusinessLogicInterface documentBusinessLogic) {
        this.documentBusinessLogic = documentBusinessLogic;
    }

    @QueryMapping(name = "getDocuments")
    public List<DocumentDto> getDocuments() {
        return documentBusinessLogic.getDocuments();
    }

    @QueryMapping(name = "getDocument")
    public DocumentDto getDocument(Long id) {
        return documentBusinessLogic.getDocument(id);
    }

    @MutationMapping
    public DocumentDto createDocument(@Argument DocumentDto documentDto) {
        return documentBusinessLogic.createDocument(documentDto);
    }
}
