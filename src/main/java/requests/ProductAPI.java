package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ProductAPI {

    // Base URL
    static {
        RestAssured.baseURI = "https://www.advantageonlineshopping.com";
    }

    // Método para buscar produtos
    public Response searchProduct(String searchQuery) {
        return given()
                .queryParam("search", searchQuery)
                .when()
                .get("/catalog/api/v1/products/search")
                .then()
                .extract().response();
    }

    // Método para atualizar imagem do produto
    public Response updateProductImage(String userId, String source, String color, String token, String imagePath) {
        return given()
                .header("Authorization", "Bearer " + token)
                .multiPart("file", imagePath)
                .when()
                .post("/catalog/api/v1/product/image/" + userId + "/" + source + "/" + color)
                .then()
                .extract().response();
    }
}
