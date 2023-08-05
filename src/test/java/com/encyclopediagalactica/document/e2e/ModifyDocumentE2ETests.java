package com.encyclopediagalactica.document.e2e;

import com.encyclopediagalactica.document.dto.DocumentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.annotation.DirtiesContext;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureHttpGraphQlTester
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ModifyDocumentE2ETests {

    @Autowired
    private HttpGraphQlTester graphQlTester;

    @Test
    public void updateEntity_andReturnWithUpdatedEntity() {
        // Arrange
        String name = "name";
        String newName = "name2";
        String desc = "desc";
        String newDesc = "desc2";
        Map<String, Object> input = new HashMap<>();
        input.put("id", 0L);
        input.put("name", name);
        input.put("desc", desc);

        DocumentDto result = graphQlTester.document("""
                   mutation mut($input: DocumentInput!) {
                     createDocument(documentInput: $input) {
                       id
                       name
                       desc
                     }
                   }
                """)
            .variable("input", input)
            .execute()
            .path("createDocument")
            .entity(DocumentDto.class)
            .get();

        System.out.println(result);

        Map<String, Object> updateInput = new HashMap<>();
        updateInput.put("id", result.getId());
        updateInput.put("name", newName);
        updateInput.put("desc", newDesc);

        String updateQuery = """
               mutation modify($input: DocumentInput!) {
                 modifyDocument(documentId: %s, document: $input) {
                   id
                   name
                   desc
                 }
               }
            """.formatted(result.getId());

        // Act
        DocumentDto updateResult = graphQlTester.document(updateQuery)
            .variable("input", updateInput)
            .execute()
            .path("modifyDocument")
            .entity(DocumentDto.class)
            .get();

        // Assert
        assertThat(updateResult.getId()).isEqualTo(result.getId());
        assertThat(updateResult.getName()).isEqualTo(newName);
        assertThat(updateResult.getDesc()).isEqualTo(newDesc);
    }
}
