package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OnboardingServicesTest {

    @Mock
    private RestTemplate testTemplate;
    @Mock
    private DadosRepository mockRepository;
    @InjectMocks
    private OnboardingServices onboardingServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void onboardingTest() {
        //given
        Dados[] dados = new Dados[1];
        String url = "";
        String result =  "Success!";
        //when
        when(testTemplate.getForObject(url,Dados[].class)).thenReturn(dados);
        String actual = onboardingServices.onboarding(url);
        //then
        assertEquals(result,actual);
        verify(testTemplate,times(1)).getForObject(url,Dados[].class);
        verify(mockRepository,times(1)).saveAll(Arrays.asList(dados));
    }

    @Test
    void deleteAllTest() {
        //when
        onboardingServices.deleteOnboarding();
        //then
        verify(mockRepository,times(1)).deleteAll();
    }
}