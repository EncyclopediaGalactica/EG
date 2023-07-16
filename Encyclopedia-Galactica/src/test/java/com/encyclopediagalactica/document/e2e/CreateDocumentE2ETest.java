package com.encyclopediagalactica.document.e2e;

import com.encyclopediagalactica.document.dto.DocumentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureHttpGraphQlTester
public class CreateDocumentE2ETest {

    @Autowired
    private HttpGraphQlTester httpGraphQlTester;

    @Test
    public void shouldCreateDocument_andReturnIt() {

        // Arrange

        // Act
        DocumentDto result = httpGraphQlTester.document("""
                   mutation createDocument($documentInput: DocumentInput!) {
                     createDocument(documentInput: $documentInput) {
                       id
                       name
                       desc
                     }
                   }
                """)
            .variable("id", 0L)
            .variable("name", "named")
            .variable("desc", "descd")
            .execute()
            .path("data")
            .entity(DocumentDto.class)
            .get();

        System.out.println(result);
    }

}
