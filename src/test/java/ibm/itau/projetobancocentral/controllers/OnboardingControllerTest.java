package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.OnboardingServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OnboardingControllerTest {

    @Mock
    private OnboardingServices mockService;

    @InjectMocks
    private OnboardingController onboardingController;

    @BeforeEach
    void setUp() {
        onboardingController = new OnboardingController(mockService);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void onboardingTest() {
        //given
        String url = "url";
        //when
        onboardingController.onboarding(url);
        //then
        verify(mockService,times(1)).onboarding(url);
    }

    @Test
    void deleteOnboardingTest() {
        //when
        onboardingController.deleteOnboarding();
        //then
        verify(mockService,times(1)).deleteOnboarding();
    }
}