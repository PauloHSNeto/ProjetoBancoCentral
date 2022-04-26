package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataFilterServicesTest {

    @Mock
    private DadosRepository mockRepository;

    private DataFilterServices dataFilterServices;


    @BeforeEach
    void setUp() {
        dataFilterServices = new DataFilterServices(mockRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByDayTest() {
        //when
        dataFilterServices.findByDay(1);
        //then
        verify(mockRepository).findByDay(1);
    }

    @Test
    void findByYearTest() {
        //when
        dataFilterServices.findByYear(1);
        //then
        verify(mockRepository).findByYear(1);
    }

    @Test
    void findByMonthTest() {
        //when
        dataFilterServices.findByMonth("March");
        //then
        verify(mockRepository).findByMonth("March");
    }
}