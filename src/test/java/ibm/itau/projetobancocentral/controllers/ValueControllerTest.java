package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.DateFilterServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValueControllerTest {
    @Mock
    private ValueServices mockService;
    @Mock
    private DateFilterServices dateFilterServices;
    @Mock
    private CrudServices crudServices;

    @InjectMocks
    private ValueController valueController;

    @Test
    void getBelowAverageTotal() {
        //when
        valueController.getBelowAverageTotal();
        //then
        verify(mockService).findBelowTotalAverage();
    }
    @Test
    void getAboveAverageTotal() {
        //when
        valueController.getAboveAverageTotal();
        //then
        verify(mockService).findAboveTotalAverage();
    }
    @Test
    void getBelowAverageYear() {
        //when
        valueController.getBelowAverageYear(2000);
        //then
        verify(mockService).findBelowYearAverage(2000);
    }
    @Test
    void getAboveAverageYear() {
        //when
        valueController.getAboveAverageYear(2000);
        //then
        verify(mockService).findAboveYearAverage(2000);
    }
    @Test
    void getAverageByYear() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2000, 1, 1), 1));
        dados.add(new Dados(LocalDate.of(2000, 1, 2), 2));
        String body = "Total de Dívida Líquida do Setor Público (% PIB) : " + "3.00" + "\n"
                + "Total de dados do ano " + 2000 + ": " + 2 + "\n"
                + "Media de Dívida Líquida do Setor Público (% PIB) do ano " + 2000 + ": " + "1.50" + "\n";
        ResponseEntity<String> expected = ResponseEntity.ok(body);
        //when
        when(dateFilterServices.findByYear(2000)).thenReturn(dados);
        when(mockService.mediaByYear(2000)).thenReturn(1.5);
        when(mockService.totalDoAno(2000)).thenReturn(3.0);
        ResponseEntity<String> response = valueController.getAverageByYear(2000);
        //then
        assertEquals(expected, response);
        verify(mockService).mediaByYear(2000);
        verify(mockService).totalDoAno(2000);
        verify(dateFilterServices).findByYear(2000);
    }
    @Test
    void getTotal() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2000, 1, 1), 1));
        dados.add(new Dados(LocalDate.of(2000, 1, 2), 2));
        dados.add(new Dados(LocalDate.of(2001, 1, 3), 3));
        String body = "Total de Dívida Líquida do Setor Público (% PIB) : 6.00" +"\n"
                + "Total de dados : " + dados.size()+"\n"
                + "Media de Dívida Líquida do Setor Público (% PIB) : 2.00" +"\n";
        ResponseEntity<String> expected = ResponseEntity.ok(body);
        when(crudServices.getAllDados()).thenReturn(dados);
        when(mockService.media()).thenReturn(2d);
        when(mockService.total()).thenReturn(6d);
        ResponseEntity<String> response = valueController.getTotal();
        //then
        assertEquals(expected, response);
        verify(mockService).media();
        verify(mockService).total();
        verify(crudServices).getAllDados();
    }
}