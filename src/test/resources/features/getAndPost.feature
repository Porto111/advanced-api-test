Feature: Testar a consulta na lista de instituições financeiras

  Scenario: Listar todas as instituições financeiras
    Given que o usuário consulta a lista de instituições financeiras
    When a resposta é recebida
    Then o status da resposta deve ser 200
    And a resposta deve conter a instituição "BANCO BRADESCO S.A."
    And a resposta deve conter a instituição "BANCO DO BRASIL S.A."
    And o campo "pagination.totalPages" deve ser igual a 1

  Scenario: Buscar por instituições financeiras com o nome "Bra"
    Given que o usuário consulta a lista de instituições financeiras com o termo de busca "Bra"
    When a resposta é recebida
    Then o status da resposta deve ser 200
    And a resposta deve conter a instituição "BANCO BRADESCO S.A."
    And a resposta deve conter a instituição "BANCO DO BRASIL S.A."