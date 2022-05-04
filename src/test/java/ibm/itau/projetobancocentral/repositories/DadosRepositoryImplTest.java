package ibm.itau.projetobancocentral.repositories;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DadosRepositoryImplTest {

    @InjectMocks
    private DadosRepositoryImpl dadosRepositoryImpl;
    @Mock
    private DadosRepository testRepository;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByYearTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2001,01,11),1.1));
        dados.add(new Dados(LocalDate.of(2002,02,12),1.2));
        dados.add(new Dados(LocalDate.of(2003,03,13),1.3));
        dados.add(new Dados(LocalDate.of(2004,04,14),1.4));
        dados.add(new Dados(LocalDate.of(2005,05,15),1.5));
        //when
        when(testRepository.findAll()).thenReturn(dados);
        Dados d1 = dadosRepositoryImpl.findByYear(2001).get(0);
        //then
        assertEquals(dados.get(0), d1);
    }

    @Test
    void findByDayTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2001,01,11),1.1));
        dados.add(new Dados(LocalDate.of(2002,02,12),1.2));
        dados.add(new Dados(LocalDate.of(2003,03,13),1.3));
        dados.add(new Dados(LocalDate.of(2004,04,14),1.4));
        dados.add(new Dados(LocalDate.of(2005,05,15),1.5));
        //when
        when(testRepository.findAll()).thenReturn(dados);
        Dados d1 = dadosRepositoryImpl.findByDay(11).get(0);
        //then
        assertEquals(dados.get(0), d1);

    }

    @Test
    void findByMonthTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2001,01,11),1.1));
        dados.add(new Dados(LocalDate.of(2002,02,12),1.2));
        dados.add(new Dados(LocalDate.of(2003,03,13),1.3));
        dados.add(new Dados(LocalDate.of(2004,04,14),1.4));
        dados.add(new Dados(LocalDate.of(2005,05,15),1.5));
        String month = "April";
        //when
        when(testRepository.findAll()).thenReturn(dados);
        Dados d1 = dadosRepositoryImpl.findByMonth(month).get(0);
        //then
        assertEquals(dados.get(3), d1);
    }
    @Test
    void findAboveValueTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2001,01,11),1.1));
        dados.add(new Dados(LocalDate.of(2002,02,12),1.2));
        dados.add(new Dados(LocalDate.of(2003,03,13),1.3));
        dados.add(new Dados(LocalDate.of(2004,04,14),1.4));
        dados.add(new Dados(LocalDate.of(2005,05,15),1.5));
        //when
        when(testRepository.findAll()).thenReturn(dados);
        List<Dados> dados2 = dadosRepositoryImpl.findAboveValue(1.22);
        //then
        assertEquals(3, dados2.size());
    }
    @Test
    void findBelowValueTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2001,01,11),1.1));
        dados.add(new Dados(LocalDate.of(2002,02,12),1.2));
        dados.add(new Dados(LocalDate.of(2003,03,13),1.3));
        dados.add(new Dados(LocalDate.of(2004,04,14),1.4));
        dados.add(new Dados(LocalDate.of(2005,05,15),1.5));
        //when
        when(testRepository.findAll()).thenReturn(dados);
        List<Dados> dados2 = dadosRepositoryImpl.findBelowValue(1.45);
        //then
        assertEquals(4, dados2.size());
    }
    @Test
    void findByYearAboveValueTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2020,01,01), 1.0));
        dados.add(new Dados(LocalDate.of(2020,01,01), 2.0));
        dados.add( new Dados(LocalDate.of(2020,01,01), 2.0));
        dados.add( new Dados(LocalDate.of(2021,01,01), 1.0));
        dados.add( new Dados(LocalDate.of(2021,01,01), 2.0));
        dados.add( new Dados(LocalDate.of(2021,01,01), 2.0));
        List<Dados> dados2020 = new ArrayList<>();
        dados2020.add(new Dados(LocalDate.of(2020,01,01), 1.0));
        dados2020.add(new Dados(LocalDate.of(2020,02,01), 2.0));
        dados2020.add(new Dados(LocalDate.of(2020,03,01), 2.0));
        //when
        when(testRepository.findByYear(2020)).thenReturn(dados2020);
        List<Dados> list = dadosRepositoryImpl.findByYearAboveValue(2020,1.5);
        //then
        assertEquals(2, list.size());
    }
    @Test
    void findByYearBelowValueTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2020,01,01), 1.0));
        dados.add(new Dados(LocalDate.of(2020,01,01), 2.0));
        dados.add( new Dados(LocalDate.of(2020,01,01), 2.0));
        dados.add( new Dados(LocalDate.of(2021,01,01), 1.0));
        dados.add( new Dados(LocalDate.of(2021,01,01), 2.0));
        dados.add( new Dados(LocalDate.of(2021,01,01), 2.0));
        List<Dados> dados2020 = new ArrayList<>();
        dados2020.add(new Dados(LocalDate.of(2020,01,01), 1.0));
        dados2020.add(new Dados(LocalDate.of(2020,02,01), 2.0));
        dados2020.add(new Dados(LocalDate.of(2020,03,01), 2.0));
        //when
        when(testRepository.findByYear(2020)).thenReturn(dados2020);
        List<Dados> list = dadosRepositoryImpl.findByYearBelowValue(2020,1.5);
        //then
        assertEquals(1, list.size());
    }
}