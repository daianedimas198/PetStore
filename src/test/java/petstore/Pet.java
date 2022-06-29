package petstore;

import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.contains;

public class Pet {
   //Atributos
    String uri = "https://petstore.swagger.io/v2/pet"; //endere�o da entidade pet

    //M�todos e Fun��es
    public String  lerJson(String caminhoJson) throws IOException {
    return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }

    //Put
    @Test (priority = 0) //Identifica o m�todo ou fun��o como um teste para o TestNG
    public void incluirPet() throws IOException{
        String jsonBody = lerJson("dados/pet1.json");

        //Sintaxe Gherkin
        // Dado - Quando- Ent�o
        //Given - When - Then

        given()
                .contentType("application/json") // comum em API REST
                .log().all()
                .body(jsonBody)
        .when()
            .post(uri)
        .then()
            .log().all()
            .statusCode(200) //verifica o c�digo  retornado se � o passado, abaixo valida os asserts
            .body("name", is("Flor"))
            .body("status", is("available"))
            .body("category.name", is("tokenDAI29"))
            .body("tags.name", contains("data"));
    }
 @Test (priority = 1)
    public void consultaPet(){
        String petID = "9222968140497182000";
        String token =

        given()
                .contentType("application/json")
                .log().all()
        .when()
                .get(uri + "/"+petID)
        .then()
                .log().all()
                .statusCode(200)
                .body("name", is("Flor"))
                .body("category.name", is("tokenDAI29"))
                .body("status", is("available"))

                .extract()
                .path("category.name")
                ;
     System.out.println("O  token �: "+ token);

 }
}
