package ibm.itau.projetobancocentral.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.itau.projetobancocentral.controllers.CrudController;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OnboardingIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    private CrudServices crudServices;
    @Autowired
    private CrudController crudController;
    @Autowired
    public TestRestTemplate restTemplate;

    @Test
    public void testOnboarding() throws Exception {
        //GIVEN
        String url ="link da api";

        Dados d1 = new Dados(LocalDate.now(),1.4);
        Dados d2 = new Dados(LocalDate.now(),1.5);
        Dados d3 = new Dados(LocalDate.now(),1.6);
        Dados[] dados = new Dados[]{d1,d2,d3};
        //WHEN

        //THEN
        mockMvc.perform(MockMvcRequestBuilders.post("/onboarding").content(url).contentType(String.valueOf(String.class)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        assert dadosRepository.findAll().size() == 3;
    }

}
