package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OnboardingServicesTest {

    @Mock
    private DadosRepository mockRepository;
    @Autowired
    private OnboardingServices onboardingServices;

    private TestTemplate testTemplate;

    @BeforeEach
    void setUp() {
    }



    @AfterEach
    void tearDown() {
    }

    @Test
    void onboardingTest() {
        //when

        //then
    }

    @Test
    void deleteOnboardingTest() {
        //when
        onboardingServices.deleteOnboarding();
        //then
        verify(mockRepository, times(1)).deleteAll();
    }
}