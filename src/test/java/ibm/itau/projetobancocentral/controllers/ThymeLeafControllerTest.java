package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ThymeLeafControllerTest {

    @Mock
    private CrudServices mockService;

    @InjectMocks
    private ThymeLeafController thymeLeafController;

    @BeforeEach
    void setUp() {
        thymeLeafController = new ThymeLeafController(mockService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void index() {
        //given
        Model model = mock(Model.class);

        //when
        when(mockService.getAllDados()).thenReturn(new ArrayList<Dados>());
        String result = thymeLeafController.index(model);
        //then
        verify(mockService, times(1)).getAllDados();
        assertEquals("index", result);

    }
}