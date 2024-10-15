package stepDefinitions;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import pageObjects.ProductAPI;

public class ProductAPISteps {

    private ProductAPI productAPI = new ProductAPI();
    private Response response;

    @Given("eu busco pelo produto \"laptop\"")
    public void eu_busco_pelo_produto(String searchQuery) {
        response = productAPI.searchProduct(searchQuery);
    }

    @Then("o código de status da resposta deve ser {int}")
    public void o_codigo_de_status_da_resposta_deve_ser(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(), response.getStatusCode());
    }

    @Then("a lista de produtos deve conter {string}")
    public void a_lista_de_produtos_deve_conter(String expectedProduct) {
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains(expectedProduct));
    }

    @When("eu atualizo a imagem do produto com userId {string}, source {string}, color {string}")
    public void eu_atualizo_a_imagem_do_produto_com_userId_source_color(String userId, String source, String color) {
        String token = "token_de_admin"; // obter o token do admin
        String imagePath = "caminho/para/imagem.jpg"; // definir caminho da imagem
        response = productAPI.updateProductImage(userId, source, color, token, imagePath);
    }

    @Then("a imagem do produto foi atualizada com sucesso")
    public void a_imagem_do_produto_foi_atualizada_com_sucesso() {
        Assert.assertEquals(200, response.getStatusCode());
        // Validar a resposta conforme a API
    }
}
