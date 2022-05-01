package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DateFilterServicesTest {

    @Mock
    private DadosRepository mockRepository;
    @InjectMocks
    private DateFilterServices dateFilterServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findByDayTest() {
        //when
        dateFilterServices.findByDay(1);
        //then
        verify(mockRepository).findByDay(1);
    }

    @Test
    void findByYearTest() {
        //when
        dateFilterServices.findByYear(1);
        //then
        verify(mockRepository).findByYear(1);
    }

    @Test
    void findByMonthTest() {
        //when
        dateFilterServices.findByMonth("March");
        //then
        verify(mockRepository).findByMonth("March");
    }

    @Test
    @DisplayName("Teste de filtro de entre datas")

    void findDadosBetweenDatesTest() {
        //given
        String startDateString = "2002-03-01";
        String endDateString = "2005-03-31";
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.of(2001,01,11),1.1));
        dados.add(new Dados(LocalDate.of(2002,02,12),1.2));
        dados.add(new Dados(LocalDate.of(2003,03,13),1.3));
        dados.add(new Dados(LocalDate.of(2004,04,14),1.4));
        dados.add(new Dados(LocalDate.of(2005,05,15),1.5));
        List<Dados> expected = new ArrayList<>();
        expected.add(new Dados(LocalDate.of(2003,03,13),1.3));
        expected.add(new Dados(LocalDate.of(2004,04,14),1.4));
        //when
        when(mockRepository.findAll()).thenReturn(dados);
        List<Dados> result = dateFilterServices.findBetweenDates(startDateString, endDateString);
        //then
        System.out.println(result);
        System.out.println(expected);
        assertEquals(expected, result);
    }
}