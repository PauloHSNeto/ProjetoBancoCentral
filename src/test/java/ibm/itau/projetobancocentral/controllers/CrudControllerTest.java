package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudControllerTest {

    @Mock
    private CrudServices mockService;

    @InjectMocks
    private CrudController controller;

    @BeforeEach
    void setUp() {
        controller = new CrudController(mockService);
    }
    @Test
    void getDadosTest() {
        //when
        controller.getDados();
        //then
        verify(mockService).getAllDados();
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
        ResponseEntity<Dados> expected = ResponseEntity.ok(d);
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
        ResponseEntity<Dados> expected = ResponseEntity.ok(d);
        //when
        when(mockService.update(eq(1L),eq(d))).thenReturn(d);
        ResponseEntity<Dados> actual = controller.putDados(1L,map);
        //then
        assertEquals(expected,actual);
    }
}