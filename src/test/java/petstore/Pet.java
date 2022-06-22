package petstore;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class Pet {
   //Atributos
    String url = "https://petstore.swagger.io/v2/pet"; //endereço da entidade pet

    //Métodos e Funções
    public String  lerJson(String caminhoJson) throws IOException {
    return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Put
    @Test //Identifica o método ou função como um teste para o TestNG
    public void incluirPet() throws IOException{
        String jsonBody  =lerJson("dados/pet1.json");

        //Sintaxe Gherkin
        // Dado - Quando- Então
        //Given - When - Then

        given()
                .contentType("application/json") // comum em API REST
                .log().all()
                .body(jsonBody)
        .when()
            .post(url)
        .then()
            .log().all()
            .statusCode(200);
    }

}
