package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.DateFilterServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DateFilterControllerTest {

    @Mock
    private DateFilterServices mockService;
    @Mock
    private ValueServices mockValueService;
    @InjectMocks
    private DateFilterController dateFilterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDownTest() {

    }

    @Test
    void getDadosByDiaTest() {
        //when
        dateFilterController.getDadosByDia(1);
        //then
        verify(mockService, times(1)).findByDay(1);
    }

    @Test
    void getDadosByMesTest() {
        //when
        dateFilterController.getDadosByMes("March");
        //then
        verify(mockService, times(1)).findByMonth("March");
    }

    @Test
    void getDadosByAnoTest() {
        //when
        dateFilterController.getDadosByAno("data",2020);
        List<Dados> dados = new ArrayList<>();
        //then
        verify(mockService, times(1)).findByYear(2020);
        verify(mockValueService, times(1)).sortByValorOrDate(dados, "data");

    }

    @Test
    void getDadosBetweenTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.now(),1.5));
        List<Dados> dadosFiltrados = new ArrayList<>();
        dados.add(new Dados(LocalDate.now(),1.5));
        dadosFiltrados.add(new Dados(LocalDate.now(),1.5));
        dadosFiltrados.add(new Dados(LocalDate.now(),1.5));
        ResponseEntity<List<Dados>> expectedEntity = ResponseEntity.ok(dadosFiltrados);
        //when
        when(mockService.findBetweenDates("2001-01-01","2002-01-01")).thenReturn(dadosFiltrados);
        ResponseEntity<List<Dados>> result = dateFilterController.getDadosBetween("2001-01-01","2002-01-01");
        //then
        assertEquals(expectedEntity, result);
    }

    @Test
    void getDadosByDateTest() {
        //given
        Dados d1 = new Dados(LocalDate.now(),1.5);
        ResponseEntity<Dados> expectedEntity = ResponseEntity.ok(d1);
        //when
        when(mockService.findByDate("2001-01-01")).thenReturn(d1);
        ResponseEntity<Dados> result = dateFilterController.getDadosByDate("2001-01-01");
        //then
        assertEquals(expectedEntity, result);

    }
}