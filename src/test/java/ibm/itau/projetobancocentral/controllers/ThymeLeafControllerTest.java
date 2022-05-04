package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import org.junit.Ignore;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThymeLeafControllerTest {

    @Mock
    private CrudServices mockService;
    @Mock
    private ValueServices valueServices;
    @Mock
    private CrudController controller;
    @InjectMocks
    private ThymeLeafController thymeLeafController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @Ignore
    void indexTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.now(),1.5));
        dados.add(new Dados(LocalDate.now().plusDays(1),2.0));
        Model model = mock(Model.class);
        //when
        when(mockService.getAllDados()).thenReturn(new ArrayList<Dados>());
        when(controller.getDados("data").getBody()).thenReturn(dados);
        String result = thymeLeafController.index(model);
        //then
        verify(mockService, times(1)).getAllDados();
        assertEquals("index", result);
    }
}