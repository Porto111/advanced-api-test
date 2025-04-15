package requests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class InstituicoesPage {

    private static final String BASE_URL = "https://acsf-srv-prtb-gerenc-portabilidade-acsf-prtb.apps.arodvinvguapi11.arocorpp.bradesco.com.br/instituicoes";

    public Response listarInstituicoes(int page, int pageSize) {
        return RestAssured.given()
                .param("page", page)
                .param("pageSize", pageSize)
                .get(BASE_URL);
    }

    public Response buscarInstituicoes(String termoBusca, int page, int pageSize) {
        return RestAssured.given()
                .param("search", termoBusca)
                .param("page", page)
                .param("pageSize", pageSize)
                .get(BASE_URL);
    }
}
