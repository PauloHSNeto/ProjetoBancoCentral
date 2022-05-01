package ibm.itau.projetobancocentral.IntegrationTests;

import ibm.itau.projetobancocentral.controllers.ValueController;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.ValueServices;
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
public class ValorTotalIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    private ValueServices valueServices;
    @Autowired
    private ValueController valueController;


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
    public void retornarValoresAcimaDaMediaTotal() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/values/above-average/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void retornaValoresAbaixoDaMediaTotal() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/values/below-average/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void retornarValoresAcimaDaMediaTotalDoAno() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/values/above-average/year/2000/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void retornarValoresAbaixoDaMediaTotalDoAno() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/values/below-average/year/2000/"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void retornaStringComValorTotalEMedia() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/values/total"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }
    @Test
    public void retornaStringComValorTotalEMediaDoAno() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/values/total/2001"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
    }

}
