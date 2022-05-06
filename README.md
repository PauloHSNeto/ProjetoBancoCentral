Projeto Banco Central
Desafio Itau
estagiário Paulo Henrique da Silva Neto, trilha Java back end Itau
O projeto é uma api que exposta na porta localhost:8080 que consome dados do Banco Central e os armazena em um banco de dados MySQL utilizando as tecnologias: 
      
      JDK Amazon correto 11
      Maven 4.0
      IDE Intelij
      Git
      MySQLServer
      MySQL workBench
      PostMan
      ThymeLeaf
      JUnit
      OpenApi
      Mockito
 
A api pública do Banco Central, na (url): https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json, devolve uma lista JSON com valores da Dívida Líquida do Setor Público (% PIB) entre DEZ/2001 e JAN/2022.


O projeto esta organizado em 3 camadas "Controllers, Services, Repository" e 5 funções principais:
   
     -Crud - Função para realizar os métodos básicos de uma Rest APi POST, DELETE, PUT, GET;  
     -Value - Para ordenar a lista de Dados pelo valor da Divida, encontrar valores máximos/mínimo ou acima/abaixo da media de um certo pedioro de tempo e calculo de divida           total e para o calculo da diferença de valores de um Dado pelo valor do Dado anterior; 
     -DateFilter - Encontra Dados pelo valor do dia/mês/ano, uma data especifica ou Dados entre datas; 
     -Onboarding - O "client" da aplicação, que captura os dados do Banco Central(com o RestTemplate) e os deposita no banco de dados MySQL; 
     -Thymeleaf - Para mostrar os valores no Banco de Dados pelo browser;
 
 
Utilizando o PostMan, realizarei uma Request com método POST(localhost:8080/onboarding), enviado á aplicação a (url) para que capture os dados e, através do RestTemplate, os transforme em objetos do tipo "Dados( Long id, LocalDate date, double valor,double diferenca)" que serão salvos na base de dados local MySQL pelo JPA. Esse é o processo de Onboarding dos dados.

Ao terminar o processo de Onboarding, posso utilizar os outros métodos Rest para manipular os dados salvos no banco (POST, DELETE, PUT, GET) e poderei usar filtrar os dados por data ou valor;

O método postDados (POST localhost:8080/dados/) recebe um Json com "data" e "valor" e cria um novo Dado. O "id" e a "diferenca" serão gerados automaticamente pela aplicação.
-Caso o json tenha sido escrito de forma incorreta, devolve um ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível converter os dados")
-Caso o valor "data" já exista na base de dados, devolve HttpStatus.CONFLICT, "Dado já existe" pois o projeto não permite mais de um Dados para um data.

O método deleteDados (DELETE localhost:8080/dados/{id}) procura o Dados por id e o remove da data-base retornando HttpStatus.NO_CONTENT e "Dado de id: " + id + " deletado com sucesso"
 - Caso o Dado não seja encontrado, retorna HttpStatus.NOT_FOUND, "Dado não encontrado"

O método putDados (PUT Localhost:8080/dados/{id}) atualiza o Dados que foi encontrado pelo id.
  - Caso o Dado não seja encontrado, retorna HttpStatus.NOT_FOUND, "Dado não encontrado"

O método getDadosById (GET localhost:8080/dados/{id}) atualiza o Dados que foi encontrado pelo id.
  - Caso o Dado não seja encontrado, retorna HttpStatus.NOT_FOUND, "Dado não encontrado"

O método getDados (GET  localhost:8080/dados ) recebe todos os Dados da data-base e aplica o método "updateDifference" para garantir que os valores de diferença estão corretos. A forma de sorting padrão para esse método e por data, porem podemos ordenar a resposta por "valor" através do parâmetro "sortBy" (localhost:8080/dados?sortBy=valor)

Com Thymeleaf Criei uma pagina que pode ser acessada pela url http://localhost:8080/thymeleaf/ que mostra os valores da data-base em uma tabela. Essa tebale só será preenchida quando dados forem colocados através do método postDados ou o processo de Onboarding.

Os testes unitários e de integração foram feitos com Mockito, JUnit e TestTemplate para simular a funções básicas da aplicação utilizando o Banco de dados em memoria H2

A documentacao da api foi feita pela plataforma OpenAPI e pode acessada pelo link http://localhost:8080/swagger-ui/index.html#/

Adcionei uma nova camada para mostrar os dados de forma paginada, criei a funcao de pegar todos os dados (na qual podemos selecionar parametros como filtrar por ano e/ou ordenar por ou valor da divida) ou encontrar apenao um por Id ou Data.

