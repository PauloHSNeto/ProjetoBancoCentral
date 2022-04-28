# ProjetoBancoCentral
Desafio Itau

estagiario Paulo Henrique da Silva Neto, trilha Java back end Itau

O projeto foi construído com as technologias:

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
    
  
 
A api pública do Banco Central, na url: https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json,
foi consumida, transformada em objetos do tipo "Dados"(Long id, LocalDate data, double valor) que foram enviados para a database local MySQL.

