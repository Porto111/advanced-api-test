# API Test Project

Este projeto realiza testes automáticos de APIs utilizando Java, Maven, RestAssured, Cucumber, e BDD. 

## Tecnologias Utilizadas
- **Java**: Linguagem principal para o desenvolvimento dos testes.
- **Maven**: Gerenciador de dependências.
- **RestAssured**: Biblioteca para testes de APIs.
- **Cucumber**: Ferramenta BDD para descrever testes de maneira legível.

## Estrutura do Projeto
- **src/main/java**: Contém classes de suporte e PageObjects.
- **src/test/java**: Contém as definições de passos (StepDefinitions) e os testes automatizados.
- **src/test/resources**: Contém os arquivos de feature e recursos adicionais como imagens.

## Executando os Testes
Para rodar os testes, execute o seguinte comando:

```bash
mvn clean test
