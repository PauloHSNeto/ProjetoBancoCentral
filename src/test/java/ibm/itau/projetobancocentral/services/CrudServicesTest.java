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
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

class CrudServicesTest {


    @Mock
    private DadosRepository mockRepository;

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
        verify(mockRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        //when
        testServices.findById(1L);
        //then
        verify(mockRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
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
    void deleteById() {
        //given
        testServices.deleteById(1L);
        //then
        verify(mockRepository, times(1)).deleteById(1L);
    }

    @Test
    void update() {
        //given
        Dados dado = new Dados(1L, LocalDate.now(), 100);
        mockRepository.save(dado);
        //when
        Dados dado2 = new Dados(1L,LocalDate.now(),100.09);
        testServices.update(1L,dado2);
        //then
        verify(mockRepository, times(1)).save(dado2);
    }
}