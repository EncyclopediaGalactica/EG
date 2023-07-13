package com.encyclopediagalactica.document.e2e;

import com.encyclopediagalactica.document.dto.DocumentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.test.tester.HttpGraphQlTester;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureHttpGraphQlTester
public class CreateDocumentE2ETest {

    @Autowired
    private HttpGraphQlTester httpGraphQlTester;

    @Test
    public void shouldCreateDocument_andReturnIt() {

        // Act
        List<DocumentDto> result = httpGraphQlTester.document("{\n" +
                "  getDocuments {\n" +
                "    id\n" +
                "    name\n" +
                "    desc\n" +
                "  }\n" +
                "}")
            .execute()
            .path("data.getDocuments")
            .entityList(DocumentDto.class)
            .get();

        System.out.println(result);
    }

}
