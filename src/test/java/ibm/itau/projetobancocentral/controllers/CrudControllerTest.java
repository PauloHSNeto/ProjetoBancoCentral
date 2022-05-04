package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.ValueServices;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudControllerTest {

    @Mock
    private CrudServices mockService;
    @Mock
    private ValueServices mockValueService;

    @InjectMocks
    private CrudController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void getDadosTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.now(),1.5));
        dados.add(new Dados(LocalDate.now(),1.5));
        dados.add(new Dados(LocalDate.now(),1.5));
        //when
        when(mockService.getAllDados()).thenReturn(dados);
        controller.getDados("data");
        //then
        verify(mockService).getAllDados();
        verify(mockValueService,times(1)).sortByValorOrDate(dados,"data");
        verify(mockValueService,times(1)).updateDifference();

    }
    @Test
    void getDadosByIdTest() {
        //when
        controller.getDadosById(1L);
        //then
        verify(mockService).findById(1L);
    }
    @Test
    void postDadosTest() {
        //given
        Map<String,Object> map = new HashMap<>();
        map.put("data", "01/01/2020");
        map.put("valor", 1.5);
        Dados d = new Dados(LocalDate.of(2020,01,01),1.5);
        ResponseEntity<Dados> expected = ResponseEntity.created(null).body(d);
        //when
        when(mockService.save(eq(d))).thenReturn(d);
        ResponseEntity<Dados> actual = controller.postDados(map);
        //then
        assertEquals(expected,actual);
    }
    @Test
    void deleteDadosTest() {
        //when
        controller.deleteDados(1L);
        //then
        verify(mockService).deleteById(1L);
    }
    @Test
    void putDadosTest() {
        //given
        Map<String,Object> map = new HashMap<>();
        map.put("data", "01/01/2020");
        map.put("valor", 1.5);
        Dados d = new Dados(LocalDate.of(2020,01,01),1.5);
        d.setId(1L);
        ResponseEntity<Dados> expected = ResponseEntity.accepted().body(d);
        //when
        when(mockService.update(eq(1L),eq(d))).thenReturn(d);
        ResponseEntity<Dados> actual = controller.putDados(1L,map);
        //then
        assertEquals(expected,actual);
    }
}