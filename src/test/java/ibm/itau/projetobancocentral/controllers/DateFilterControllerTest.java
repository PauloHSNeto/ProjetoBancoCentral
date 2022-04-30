package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.services.DateFilterServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DateFilterControllerTest {

    @Mock
    private DateFilterServices mockService;
    @InjectMocks
    private DateFilterController dateFilterController;

    @BeforeEach
    void setUp() {
        dateFilterController = new DateFilterController(mockService);
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
        dateFilterController.getDadosByAno(2020);
        //then
        verify(mockService, times(1)).findByYear(2020);
    }
}