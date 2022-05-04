package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.OnboardingServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OnboardingControllerTest {

    @Mock
    private OnboardingServices mockService;
    @Mock
    private DadosRepository dadosRepository;
    @Mock
    private ValueServices valueServices;

    @InjectMocks
    private OnboardingController onboardingController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void onboardingTest() {
        //given
        List<Dados> dados = new ArrayList<>();
        dados.add(new Dados(LocalDate.now(),1.5));
        dados.add(new Dados(LocalDate.now().plusDays(1),2.0));
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