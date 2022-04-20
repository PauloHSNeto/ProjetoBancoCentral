package ibm.itau.projetobancocentral;

import ibm.itau.projetobancocentral.entities.Dados;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProjetoBancoCentralApplicationTests {
    @Test
    void contextLoads() {
    }
}
