# ProjetoBancoCentral
Desafio Itau

O código da aplicação deve conter um arquivo README.md com as instruções 
sobre como configurar o ambiente e executar o aplicativo, assim como a descrição dos 
arquivos importantes para que os avaliadores sejam objetivos em sua análise.

O projeto foi construído com as technologias:
  JDK Amazon correto 11
  Maven 4.0
  SpringBoot (Spring Web, Jpa, MySQL Connector, h2 )
  IDE Intelij
  Git
  MySQL workBench
  PostMan
  
 
A api pública do Banco Central, na url: https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json,
foi consumida, transformada em objetos do tipo "Dados"(Long id, LocalDate data, BigDecimal valor) que foram enviados para a database local MySQL.
