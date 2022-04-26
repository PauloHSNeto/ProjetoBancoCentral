package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DateFilterServicesTest {

    @Mock
    private DadosRepository mockRepository;

    private DateFilterServices dateFilterServices;


    @BeforeEach
    void setUp() {
        dateFilterServices = new DateFilterServices(mockRepository);
    }

    @AfterEach
    void tearDown() {
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
}