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
    
  
 
A api pública do Banco Central, na url: https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json, devolve uma lista JSON com valores da Dívida Líquida do Setor Público (% PIB) entre DEZ/2001 e JAN/2022.

Utilizando o PostMan, realizarei um Request com metodo POST, enviado á aplicação a url para que a aplição capture os dados e atravéz do RestTemplate, os transforme em objetos do tipo "Dados( Long id, LocalDate date, double valor)" que serão salvos na base de dados local MySQL pelo JPA. Esse é o processo de Onboarding dos dados.

Ao terminar o processo, posso utilizar os outros metodos Rest para manipular os dados salvos no banco (POST, DELETE, PUT, GET) e poderei usar filtrar os dados por data ou valor

