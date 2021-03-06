package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValueServicesTest {

    @Mock
    private DadosRepository mockRepository;
    @InjectMocks
    private ValueServices valueServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByValorAboveTest() {
        //given
        Dados d = new Dados(LocalDate.of(2000,1,1), 2d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d);
        //when
        when(mockRepository.findAboveValue(1)).thenReturn(dados);
        List<Dados> result = valueServices.findByValorAbove(1);
        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        assertEquals(d, result.get(0));
    }
    @Test
    void findByValorBelowTest() {
        //given
        Dados d = new Dados(LocalDate.of(2000,1,1), 2d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d);
        //when
        when(mockRepository.findBelowValue(3)).thenReturn(dados);
        List<Dados> result = valueServices.findByValorBelow(3);
        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        assertEquals(d, result.get(0));
    }

    @Test
    void findAboveYearAverageTest() {
        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,1), 2d);
        List<Dados> dados = new ArrayList<>();
        List<Dados> dadosFiltrado = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        dadosFiltrado.add(d2);
        //when
        when(mockRepository.findByYear(eq(2000))).thenReturn(dados);
        when(mockRepository.findByYearAboveValue(eq(2000),eq(1.5d))).thenReturn(dadosFiltrado);
        List <Dados> result = valueServices.findAboveYearAverage(2000);
        //then
        verify(mockRepository,times(1)).findByYearAboveValue(eq(2000),eq(1.5d));
        verify(mockRepository,times(2)).findByYear(eq(2000));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        assertEquals(2,result.get(0).getValor());
    }

    @Test
    void findBelowYearAverageTest() {

        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 4d);
        List<Dados> dados = new ArrayList<>();
        List<Dados> dadosFiltrados = new ArrayList<>();
        List<Dados> dadosFiltrado2 = new  ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        dados.add(d3);
        dadosFiltrados.add(d1);
        dadosFiltrados.add(d2);
        dadosFiltrado2.add(d1);
        //when
        when(mockRepository.findByYear(eq(2000))).thenReturn(dadosFiltrados);
        when(mockRepository.findByYearBelowValue(eq(2000),eq(1.5d))).thenReturn(dadosFiltrado2);
        List <Dados> result = valueServices.findBelowYearAverage(2000);
        //then
        verify(mockRepository,times(1)).findByYearBelowValue(eq(2000),eq(1.5d));
        verify(mockRepository,times(2)).findByYear(eq(2000));
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1,result.size());
        assertEquals(1,result.get(0).getValor());
    }

    @Test
    void findBelowTotalAverageTest() {
        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 6d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        dados.add(d3);
        List<Dados> dados2 = new ArrayList<>();
        dados2.add(d1);
        //when
        when(mockRepository.findAll()).thenReturn(dados);
        when(mockRepository.findBelowValue(3)).thenReturn(dados2);
        List <Dados> result = valueServices.findBelowTotalAverage();
        //then
        verify(mockRepository,times(2)).findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(d1,result.get(0));
    }

    @Test
    void findAboveTotalAverageTest() {
        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 6d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        dados.add(d3);
        List<Dados> dados2 = new ArrayList<>();
        dados2.add(d3);

        //when
        when(mockRepository.findAll()).thenReturn(dados);
        when(mockRepository.findAboveValue(3)).thenReturn(dados2);
        List <Dados> result = valueServices.findAboveTotalAverage();
        //then
        verify(mockRepository,times(2)).findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(d3,result.get(0));
    }

    @Test
    void totalTest() {
        //given
        Dados d1 = new Dados(LocalDate.now(), 1d);
        Dados d2 = new Dados(LocalDate.now(), 2d);
        Dados d3 = new Dados(LocalDate.now(), 3d);
        List<Dados> dados = new ArrayList<>();

        dados.add(d1);
        dados.add(d2);
        dados.add(d3);

        when(mockRepository.findAll()).thenReturn(dados);
        //when
        double result = valueServices.total();
        //then
        assertEquals(6, result);

    }

    @Test
    void totalDoAnoTest() {

        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 4d);
        List<Dados> dados = new ArrayList<>();

        dados.add(d1);
        dados.add(d2);

        when(mockRepository.findByYear(2000)).thenReturn(dados);
        //when
        double result = valueServices.totalDoAno(2000);
        //then
        assertEquals(3, result);

    }

    @Test
    void mediaTest() {
        //given

        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 6d);
        List<Dados> dados = new ArrayList<>();

        dados.add(d1);
        dados.add(d2);
        dados.add(d3);

        when(mockRepository.findAll()).thenReturn(dados);
        //when
        double result = valueServices.media();
        //then
        assertEquals(3, result);


    }

    @Test
    void mediaByYearTest() {

        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 6d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        when(mockRepository.findByYear(2000)).thenReturn(dados);
        //when
        double result = valueServices.mediaByYear(2000);
        //then
        assertEquals(1.5, result);


    }

    @Test
    void findByMaxValueTest() {
        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d2 = new Dados(LocalDate.of(2000,1,2), 2.5d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        //when
        when(mockRepository.findAll()).thenReturn(dados);
        Dados result = valueServices.findByMaxValue();
        //then
        assertEquals(d2, result);
    }

    @Test
    void findByMinValueTest() {
        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 2d);
        Dados d2 = new Dados(LocalDate.of(2000,1,2), 2.5d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        //when
        when(mockRepository.findAll()).thenReturn(dados);
        Dados result = valueServices.findByMinValue();
        //then
        assertEquals(d1, result);
    }

    @Test
    void findByMaxValueOfYearTest() {
        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,2), 1.5d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 2d);
        Dados d4 = new Dados(LocalDate.of(2001,1,2), 2.5d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        //when
        when(mockRepository.findByYear(2000)).thenReturn(dados);
        Dados result = valueServices.findByMaxValueOfYear(2000);
        //then
        assertEquals(d2, result);
    }

    @Test
    void findByMinValueofYearTest() {
        //given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,2), 1.5d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 2d);
        Dados d4 = new Dados(LocalDate.of(2001,1,2), 2.5d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        //when
        when(mockRepository.findByYear(2000)).thenReturn(dados);
        Dados result = valueServices.findByMinValueofYear(2000);
        //then
        assertEquals(d1, result);
    }

    @Test
    @Ignore
    void sortByValorTest() {//given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,2), 1.5d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 2d);
        Dados d4 = new Dados(LocalDate.of(2001,1,2), 2.5d);
        List<Dados> dados = new ArrayList<>();
        dados.add(d1);
        dados.add(d2);
        //when
        when(mockRepository.findByYear(2000)).thenReturn(dados);
        Dados result = valueServices.findByMinValueofYear(2000);
        //then
        assertEquals(d1, result);
    }

    @Test
    @Ignore
    void sortByDateTest() {//given
        Dados d1 = new Dados(LocalDate.of(2000,1,1), 1d);
        Dados d2 = new Dados(LocalDate.of(2000,1,2), 1.5d);
        Dados d3 = new Dados(LocalDate.of(2001,1,1), 2d);
        Dados d4 = new Dados(LocalDate.of(2001,1,2), 2.5d);
        List<Dados> dados = new ArrayList<>(Arrays.asList(d2,d1,d4,d3));
        List<Dados> dadosOrdenados = new ArrayList<>(Arrays.asList(d1,d2,d3,d4));
        //when
        when(mockRepository.findByYear(2000)).thenReturn(dados);
        Dados result = valueServices.findByMinValueofYear(2000);
        //then
        assertEquals(d1, result);
    }
}