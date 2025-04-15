package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.SSLTrustAllConfig;

public class InstituicoesSteps {

    private RequestSpecification request;
    private Response response;
    private final String BASE_URL = "https://acsf-srv-prtb-gerenc-portabilidade-acsf-prtb.apps.arodvinvguapi11.arocorpp.bradesco.com.br";

    @Before
    public void setup() {
        // Configurar para confiar em todos os certificados SSL
        SSLTrustAllConfig.trustAllCertificates();
        // Ou use o método alternativo se o primeiro não funcionar
        // SSLTrustAllConfig.trustAllCertificatesAlternative();
    }

    @Given("que o usuário consulta a lista de instituições financeiras")
    public void que_o_usuário_consulta_a_lista_de_instituições_financeiras() {
        // Configurar a requisição
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");

        // Fazer a requisição GET para o endpoint de instituições financeiras
        response = request.get("/instituicoes");
    }

    @Given("que o usuário consulta a lista de instituições financeiras com o termo de busca {string}")
    public void que_o_usuário_consulta_a_lista_de_instituições_financeiras_com_o_termo_de_busca(String termo) {
        // Configurar a requisição
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("Content-Type", "application/json");

        // Fazer a requisição GET com parâmetro de busca
        response = request.queryParam("nome", termo).get("/instituicoes");
    }

    @When("a resposta é recebida")
    public void a_resposta_é_recebida() {
        // Verificar se a resposta não é nula
        Assert.assertNotNull("A resposta não deve ser nula", response);
    }

    @Then("o status da resposta deve ser {int}")
    public void o_status_da_resposta_deve_ser(Integer statusCode) {
        // Verificar o status code da resposta
        Assert.assertEquals("O status code da resposta deve ser " + statusCode,
                statusCode.intValue(), response.getStatusCode());
    }

    @Then("a resposta deve conter a instituição {string}")
    public void a_resposta_deve_conter_a_instituição(String nomeInstituicao) {
        // Verificar se a resposta contém a instituição especificada
        String responseBody = response.getBody().asString();
        Assert.assertTrue("A resposta deve conter a instituição " + nomeInstituicao,
                responseBody.contains(nomeInstituicao));

        // Alternativa usando JsonPath para verificar em uma lista de instituições
        // List<String> nomes = response.jsonPath().getList("data.nome");
        // Assert.assertTrue("A lista deve conter a instituição " + nomeInstituicao,
        //                  nomes.contains(nomeInstituicao));
    }

    @Then("o campo {string} deve ser igual a {int}")
    public void o_campo_deve_ser_igual_a(String campo, Integer valor) {
        // Verificar se o valor do campo especificado é igual ao valor esperado
        int valorAtual = response.jsonPath().getInt(campo);
        Assert.assertEquals("O valor do campo " + campo + " deve ser " + valor,
                valor.intValue(), valorAtual);
    }
}


//package stepDefinitions;
//
//
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import static org.hamcrest.Matchers.*;
//import requests.InstituicoesPage;
//
//
//
//public class InstituicoesSteps {
//
//    private InstituicoesPage instituicoesPage;
//
//    private Response response;
//
//    // URL base da API
//    private static final String BASE_URL = "https://acsf-srv-prtb-gerenc-portabilidade-acsf-prtb.apps.arodvinvguapi11.arocorpp.bradesco.com.br/instituicoes";
//
//    @Given("que o usuário consulta a lista de instituições financeiras")
//    public void que_o_usuario_consulta_a_lista_de_instituicoes_financeiras() {
//        // Fazer a requisição GET
//        response = RestAssured.given()
//                .param("page", 0)
//                .param("pageSize", 500)
//                .get(BASE_URL);
//    }
//
//    @Given("que o usuário consulta a lista de instituições financeiras com o termo de busca {string}")
//    public void que_o_usuario_consulta_a_lista_de_instituicoes_financeiras_com_o_termo_de_busca(String termoBusca) {
//        // Fazer a requisição GET com o parâmetro de busca
//        response = RestAssured.given()
//                .param("search", termoBusca)
//                .param("page", 0)
//                .param("pageSize", 500)
//                .get(BASE_URL);
//    }
//
//    @When("a resposta é recebida")
//    public void a_resposta_e_recebida() {
//        // A resposta já foi recebida nas etapas anteriores, nada adicional é necessário aqui
//    }
//
//    @Then("o status da resposta deve ser {int}")
//    public void o_status_da_resposta_deve_ser(Integer status) {
//        response.then().statusCode(status);
//    }
//
//    @Then("a resposta deve conter a instituição {string}")
//    public void a_resposta_deve_conter_a_instituicao(String nomeInstituicao) {
//        response.then().body("data.nome", hasItem(nomeInstituicao));
//    }
//
//    @Then("o campo {string} deve ser igual a {int}")
//    public void o_campo_deve_ser_igual_a(String campo, Integer valor) {
//        response.then().body("pagination." + campo, equalTo(valor));
//    }
//}
