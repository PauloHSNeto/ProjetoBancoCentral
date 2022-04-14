package ibm.itau.projetobancocentral;

import ibm.itau.projetobancocentral.entities.Dados;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ProjetoBancoCentralApplicationTests {
    @Test
    void contextLoads() {
        assertEquals(1,1);
        BigDecimal valor = new BigDecimal(100);
        LocalDate data = LocalDate.now();
        Dados dado = new Dados(data, valor);
        assertEquals(LocalDate.now(), dado.getData());



    }

}
