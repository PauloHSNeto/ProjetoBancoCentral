package ibm.itau.projetobancocentral.IntegrationTests;

import ibm.itau.projetobancocentral.controllers.DateFilterController;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.DateFilterServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class DataFilterIntegrationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    private DateFilterServices services;
    @Autowired
    private DateFilterController dateFilterController;

    @BeforeEach
    public void setUp() {
        if (dadosRepository.count() == 0) {
        dadosRepository.save(new Dados(LocalDate.of(2000,1,1),1.5));
        dadosRepository.save(new Dados(LocalDate.of(2000,2,2),2.5));
        dadosRepository.save(new Dados(LocalDate.of(2000,3,3),3.5));
        dadosRepository.save(new Dados(LocalDate.of(2001,1,4),4.5));
        dadosRepository.save(new Dados(LocalDate.of(2001,2,5),5.5));
        dadosRepository.save(new Dados(LocalDate.of(2001,3,6),6.5));
        dadosRepository.save(new Dados(LocalDate.of(2002,1,7),7.5));
        dadosRepository.save(new Dados(LocalDate.of(2002,2,8),8.5));
        dadosRepository.save(new Dados(LocalDate.of(2002,3,9),9.5));
        dadosRepository.save(new Dados(LocalDate.of(2003,1,10),10.5));
        dadosRepository.save(new Dados(LocalDate.of(2003,2,11),11.5));
        dadosRepository.save(new Dados(LocalDate.of(2003,3,12),12.5));
        }
    }
    @Test
    public void consultaDeDadosPorDia() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dateFilters/day/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void consultaDeDadosPorMes() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dateFilters/month/march"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void consultaDeDadosPorAno() throws Exception {
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/dateFilters/year/2000"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
}
