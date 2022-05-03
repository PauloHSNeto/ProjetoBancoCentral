# ProjetoBancoCentral
Desafio Itau

estagiario Paulo Henrique da Silva Neto, trilha Java back end Itau

O projeto é uma api que exposta na porta localhost:8080 que consome dados do Banco Central e os armazera em um banco de dados MySQL utilizando as teconologias:

    JDK Amazon correto 11
    Maven 4.0
    SpringBoot (Spring Web, Jpa, MySQL Connector, h2, LomBok, RestTemplate )
    IDE Intelij
    Git
    MySQL workBench
    PostMan
    ThymeLeaf
    Docker
    JUnit
    Mockito
    
  
 
A api pública do Banco Central, na (url): https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json, devolve uma lista JSON com valores da Dívida Líquida do Setor Público (% PIB) entre DEZ/2001 e JAN/2022.

O projeto esta organizado em 3 camadas "Controllers, Services, Repository" e 5 funcoes principais:
    Crud - Funcao para realizar os metodos basicos de uma Rest APi POST, DELETE, PUT, GET;
    Value - Para ordenar a lista de Dados pelo valor da Divida, encontrar valores maximos/minimo ou acima/abaixo da media de um certo pedioro de tempo e calculo de divida                 total e para o calculo da diferenca de valores de um Dado pelo valor do Dado anterior;
    DateFilter - Encontra Dados pelo valor do dia/mes/ano, uma data especifica ou Dados entre datas;
    Onboarding - O "client" da aplicacao, que captura os dados do Banco Central(com o RestTemplate) e os deposita no banco de dados MySQL;
    Thymeleaf - Para mostrar os valors no Banco de Dados pelo browser;
 

Utilizando o PostMan, realizarei uma Request com metodo POST(localhost:8080/onboarding), enviado á aplicação a (url) para que capture os dados e, através do RestTemplate, os transforme em objetos do tipo "Dados( Long id, LocalDate date, double valor,double diferenca)" que serão salvos na base de dados local MySQL pelo JPA. Esse é o processo de Onboarding dos dados.

Ao terminar o processo de Onboarding, posso utilizar os outros métodos Rest para manipular os dados salvos no banco (POST, DELETE, PUT, GET) e poderei usar filtrar os dados por data ou valor
    
    O metodo postDados (POST localhost:8080) recebe um Json com "data" e "valor" e cria um novo Dado. O "id" e a "diferenca" serao gerados automaticamente pela aplicacao.
    -caso o json tenha sido escrito de forma incrorreta, devolve um ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível converter os dados")
    -caso o valor "data" ja exita na base de dados, devolve HttpStatus.CONFLICT, "Dado já existe" pois o projeto nao permite mais de um Dados para um data.
    
    O metodo deleteDados (DELETE localhost:8080/{id}) procura o Dados por id e o remove da database retornando HttpStatus.NO_CONTENT e "Dado de id: " + id + " deletado com sucesso"
     - caso o Dados nao seja encontrado, retorna HttpStatus.NOT_FOUND, "Dado não encontrado"

    o metodo putDados (PUT Localhost:8080/{id}) atualiza o Dados que foi encontrado pelo id.
      - caso o Dado snao seja encontrado, retorna HttpStatus.NOT_FOUND, "Dado não encontrado"
    
    o metodo getDadosById (GET localhost:8080/{id}) atualiza o Dados que foi encontrado pelo id.
      - caso o Dado nao seja encontrado, retorna HttpStatus.NOT_FOUND, "Dado não encontrado"
    
    O metodo getDados (GET  localhost:8080/dados ) recebe todos os Dados da database e aplica o metodo "updateDifference" para garantir que os valores de diferenca estao corretos. A forma de sorting padrao para esse metodo e por data, porem pode mos ordernar a responsta por "valor" atraves do parametro "sortBy" (localhost:8080/dados?sortBy=valor)






