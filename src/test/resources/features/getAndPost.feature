Feature: Testar APIs de Produtos
  Como um QA
  Quero testar as APIs de produtos da Advantage Online Shopping
  Para garantir que estão funcionando corretamente

  Scenario: Buscar um produto existente
    Given eu busco pelo produto "laptop"
    Then o codigo de status da resposta deve ser 200
    And a lista de produtos deve conter "laptop"

  Scenario: Atualizar imagem de um produto
    When eu atualizo a imagem do produto com userId "123", source "web", color "blue"
    Then a imagem do produto foi atualizada com sucesso
    And o código de status da resposta deve ser 200
