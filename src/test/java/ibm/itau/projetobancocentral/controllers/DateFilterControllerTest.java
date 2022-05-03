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

import java.util.ArrayList;
import java.util.List;

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
}