package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.repositories.DadosRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudServicesTest {
    @Mock
    private Dados dado;
    @Mock
    private DadosRepository mockRepository;
    @InjectMocks
    private CrudServices testServices;
    @BeforeEach
    void setUp() {
        testServices = new CrudServices(mockRepository);
    }

    @Test
    void findAllTest() {
        //given
        testServices.getAllDados();
        //then
        verify(mockRepository).findAll();
    }
    @Test
    void findByIdTest() {
        Dados d1 =new Dados();
        //when
        when(mockRepository.findById(1L)).thenReturn(Optional.of(d1));
        Dados dResult = testServices.findById(1L);
        //then
        verify(mockRepository).findById(1L);
        assertEquals(d1,dResult);
    }
    @Test
    void saveTest() {
        //given
        Dados dado = new Dados();
        //when
        testServices.save(dado);
        //then
        ArgumentCaptor<Dados> dadosArgumentCaptor =
                ArgumentCaptor.forClass(Dados.class);

        verify(mockRepository)
                .save(dadosArgumentCaptor.capture());

        Dados capturedDados = dadosArgumentCaptor.getValue();

        assertEquals(capturedDados,dado);
    }
    @Test
    void deleteByIdTest() {
        //given
        testServices.deleteById(1L);
        //then
        verify(mockRepository, times(1)).deleteById(1L);
    }
    @Test
    void updateTest() {
        //given
        Dados d1 = new Dados(LocalDate.now(), 1);
        Dados d2 = new Dados(LocalDate.now(), 1);

        //when
        when(mockRepository.findById(1L)).thenReturn(Optional.of(d1));
       Dados dResult = testServices.update(1L,d2);
        //then
        ArgumentCaptor<Dados> dadosArgumentCaptor =
                ArgumentCaptor.forClass(Dados.class);

        verify(mockRepository)
                .save(dadosArgumentCaptor.capture());

        Dados capturedDados = dadosArgumentCaptor.getValue();

        assertEquals(capturedDados,d2);}
}
