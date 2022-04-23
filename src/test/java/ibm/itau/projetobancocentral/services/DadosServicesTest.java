package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.controllers.DadosController;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DadosServicesTest {
    @Autowired
    private DadosServices dadosServices;

    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private DadosController dadosController;


    Dados d = new Dados(LocalDate.of(2000,04,20), BigDecimal.valueOf(99.99));
    Dados d2 = new Dados(LocalDate.of(2022,04,23), BigDecimal.valueOf(96.99));
    Dados d3 = new Dados(LocalDate.of(1989,03,21), BigDecimal.valueOf(33.33));
    List<Dados> dados = new ArrayList<Dados>(List.of(d,d2,d3));


    @Before
    public void setUp() {
        dadosRepository.saveAll(dados);
    }
    @After
    public void tearDown() {
        dadosRepository.deleteAll();
    }

    @Test
    void findAll() {
        dadosRepository.saveAll(dados);
        assertEquals(dados, dadosServices.findAll());
    }

    @Test
    void findById() {
        dadosRepository.saveAll(dados);
        assertEquals(d, dadosServices.findById(1L));
    }

    @Test
    void findByMonth() {
        dadosRepository.saveAll(dados);
        String mes = "March";
        assertEquals(d3, dadosServices.findByMonth(mes).get(0));
    }

    @Test
    void findByDay() {
        dadosRepository.saveAll(dados);
        int day = 21;
        assertEquals(d3, dadosServices.findByDay(day).get(0));

    }

    @Test
    void findByYear() {

        dadosRepository.saveAll(dados);
        int year = 1989;
        assertEquals(d3, dadosServices.findByDay(year).get(0));

    }
    @Test
    void save() {
        dadosRepository.saveAll(dados);
        assertEquals(dados, dadosRepository.findAll());
    }

    @Test
    void deleteById() {
        dadosRepository.saveAll(dados);
        dadosRepository.deleteById(3L);
        assertEquals(2, dadosRepository.findAll().size());
    }

    @Test
    void total() {
        dadosRepository.saveAll(dados);
        assertEquals(BigDecimal.valueOf(96.99), dadosServices.total(2022));
    }

    @Test
    void update() {
        dadosRepository.saveAll(dados);
        Map<String,Object> d4 = Map.of("data", "01/01/2000", "valor", 20.00);
        dadosServices.update(1L, d4);
        assertEquals("20.00", dadosServices.findById(1L).getValor().toString());
        assertEquals(LocalDate.of(2000,01,01), dadosServices.findById(1L).getData());
    }

}