package ibm.itau.projetobancocentral.IntegrationTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import ibm.itau.projetobancocentral.controllers.CrudController;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT )
public class CrudIntegrationTests {
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

    @BeforeEach
    public void setup() {
        if (dadosRepository.findAll().isEmpty()) {
            dadosRepository.save(new Dados(LocalDate.now(), 1.01));
            dadosRepository.save(new Dados(LocalDate.now(), 2.02));
            dadosRepository.save(new Dados(LocalDate.now(), 3.03));
            dadosRepository.save(new Dados(LocalDate.now(), 4.04));
            dadosRepository.save(new Dados(LocalDate.now(), 5.05));
        }
    }
    @Test
    public void testeDeConsultadeTodosOsDados() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dados"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void testeDeConsultadeTodosOsDadosPorID() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/3"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

    @Test
    public void testeDPost() throws Exception {
        Dados dados = new Dados(LocalDate.now().minusDays(1), 6.06);
        String json = objectMapper.writeValueAsString(dados);
        mockMvc.perform(MockMvcRequestBuilders.post("/").content(json).contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isCreated()).andReturn();
    }
    @Test
    public void testeDUpdate() throws Exception {
        Dados dados = new Dados(LocalDate.now(), 7.07);
        String json = objectMapper.writeValueAsString(dados);
        mockMvc.perform(MockMvcRequestBuilders.put("/4").content(json).contentType("application/json"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isAccepted()).andReturn();
    }
    @Test
    public void testeDDelete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isNoContent()).andReturn();
    }
}