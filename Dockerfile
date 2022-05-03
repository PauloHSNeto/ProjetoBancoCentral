FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/ProjetoBancoCentral-0.0.1-SNAPSHOT.jar /projetobancocentral.jar
ENTRYPOINT ["java", "-jar","/projetobancocentral.jar"]
