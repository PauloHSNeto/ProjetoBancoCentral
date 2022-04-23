package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.DadosServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DadosControllerTest {
    @Autowired
    private DadosServices dadosServices;

    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private DadosController dadosController;


    Dados d = new Dados(LocalDate.now(), BigDecimal.valueOf(99.99));
    Dados d2 = new Dados(LocalDate.now(), BigDecimal.valueOf(96.99));

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