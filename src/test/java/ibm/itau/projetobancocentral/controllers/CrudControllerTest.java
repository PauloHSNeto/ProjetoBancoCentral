package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class CrudControllerTest {
    @Autowired
    private CrudServices crudServices;

    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private CrudController crudController;


    Dados d = new Dados(LocalDate.now(), 99.99);
    Dados d2 = new Dados(LocalDate.now(), 96.99);
    Dados d3 = new Dados(LocalDate.now(), 100.99);

    @Test
    void getDados() {
        dadosRepository.save(d);
        dadosRepository.save(d2);

    }
    @Test
    void getDadosById() {
    }

    @Test
    void getDadosByDia() {
    }

    @Test
    void getDadosByMes() {
    }

    @Test
    void getDadosByAno() {
    }

    @Test
    void postDados() {
    }

    @Test
    void deleteDados() {
    }

    @Test
    void getMax() {
    }

    @Test
    void putDados() {
    }

    @Test
    void onboarding() {
    }
}