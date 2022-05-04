package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CrudServicesTest {

    @Mock
    private DadosRepository mockRepository;
    @Mock
    private ValueServices valueServices;
    @Mock
    private DateFilterServices dateFilterServices;
    @InjectMocks
    private CrudServices testServices;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);    }

    @Test
    @Ignore
    void findAllTest() {
        //given
        testServices.getAllDados();
        //then
        verify(mockRepository).findAll();
    }
    @Test
    @Ignore
    void findByIdTest() {
        //when
        testServices.findById(1L);
        //then
        verify(mockRepository).findById(1L);
    }
    @Test
    @Ignore
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
    @Ignore
    void deleteByIdTest() {
        //given
        testServices.deleteById(1L);
        //then
        verify(mockRepository, times(1)).deleteById(1L);
    }
    @Test
    @Ignore
    void updateTest() {
        //given
        Dados d1 = new Dados(LocalDate.now(), 1d);
        Dados d2 = new Dados(LocalDate.now(), 1d);

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
