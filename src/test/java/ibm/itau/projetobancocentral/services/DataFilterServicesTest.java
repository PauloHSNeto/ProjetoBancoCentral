package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

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
    void findByDay() {
        //todo: test findByDay method
        //when

        //then

    }

    @Test
    void findByYear() {
    }

    @Test
    void findByMonth() {
    }
}