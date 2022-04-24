package ibm.itau.projetobancocentral.repositories;

import ibm.itau.projetobancocentral.entities.Dados;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class DadosRepositoryImplTest {

    @Autowired
    private DadosRepository testRepository;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByYearTest() {
        //given
        Dados dados = new Dados(LocalDate.now(), 1.0);
        testRepository.save(dados);
        //when
        int year = LocalDate.now().getYear();
        Dados dados1 = testRepository.findByYear(year).get(0);
        //then
        assertEquals(dados, dados1);
    }

    @Test
    void findByDayTest() {
        //given
        Dados dados = new Dados(LocalDate.now(), 1.0);
        testRepository.save(dados);
        //when
        int day = LocalDate.now().getDayOfMonth();
        Dados dados1 = testRepository.findByDay(day).get(0);
        //then
        assertEquals(dados, dados1);

    }

    @Test
    void findByMonthTest() {

        //given
        Dados dados = new Dados(LocalDate.now(), 1.0);
        testRepository.save(dados);
        //when
        String month = LocalDate.now().getMonth().toString();
        Dados dados1 = testRepository.findByMonth(month).get(0);
        //then
        assertEquals(dados, dados1);
    }

    @Test
    void findAboveValueTest() {

        //given
        Dados dados = new Dados(LocalDate.now(), 1.0);
        Dados dados1 = new Dados(LocalDate.now(), 2.0);
        testRepository.save(dados);
        testRepository.save(dados1);
        //when
        double value = 1.5;
        Dados dados2 = testRepository.findAboveValue(value).get(0);
        //then
        assertEquals(dados1, dados2);
    }

    @Test
    void findBelowValueTest() {

        //given
        Dados dados = new Dados(LocalDate.now(), 1.0);
        Dados dados1 = new Dados(LocalDate.now(), 2.0);
        testRepository.save(dados);
        testRepository.save(dados1);
        //when
        double value = 1.5;
        Dados dados2 = testRepository.findBelowValue(value).get(0);
        //then
        assertEquals(dados, dados2);
    }


    @Test
    void findByYearAboveValueTest() {

        //given
        Dados dados = new Dados(LocalDate.of(2020,01,01), 1.0);
        Dados dados1 = new Dados(LocalDate.of(2020,01,01), 2.0);
        Dados dados2 = new Dados(LocalDate.of(2020,01,01), 2.0);
        Dados dados3 = new Dados(LocalDate.of(2021,01,01), 1.0);
        Dados dados4 = new Dados(LocalDate.of(2021,01,01), 2.0);
        Dados dados5 = new Dados(LocalDate.of(2021,01,01), 2.0);
        testRepository.save(dados);
        testRepository.save(dados1);
        testRepository.save(dados2);
        testRepository.save(dados3);
        testRepository.save(dados4);
        testRepository.save(dados5);
        //when
        double value = 1.5;
        List<Dados> list = testRepository.findByYearAboveValue(2020,value);
        //then
        assertEquals(2, list.size());
    }

    @Test
    void findByYearBelowValueTest() {


        //given
        Dados dados = new Dados(LocalDate.of(2020,01,01), 1.0);
        Dados dados1 = new Dados(LocalDate.of(2020,01,01), 2.0);
        Dados dados2 = new Dados(LocalDate.of(2020,01,01), 2.0);
        Dados dados3 = new Dados(LocalDate.of(2021,01,01), 1.0);
        Dados dados4 = new Dados(LocalDate.of(2021,01,01), 2.0);
        Dados dados5 = new Dados(LocalDate.of(2021,01,01), 2.0);
        testRepository.save(dados);
        testRepository.save(dados1);
        testRepository.save(dados2);
        testRepository.save(dados3);
        testRepository.save(dados4);
        testRepository.save(dados5);
        //when
        double value = 1.5;
        List<Dados> list = testRepository.findByYearBelowValue(2020,value);
        //then
        assertEquals(1, list.size());
    }
}