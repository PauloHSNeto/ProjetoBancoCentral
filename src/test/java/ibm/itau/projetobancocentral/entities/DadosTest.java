package ibm.itau.projetobancocentral.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class DadosTest {
    LocalDate date = LocalDate.of(1989,04,21);
    BigDecimal valor = new BigDecimal(-5.33);
    Dados example = new Dados(date,valor);


    @Test
    void getId() {
        assertEquals(null,example.getId());
    }

    @Test
    void getData() {
        assertEquals(date,example.getData());
    }

    @Test
    void getValor() {
        assertEquals(valor,example.getValor());
    }

    @Test
    void setId() {
        example.setId(2L);
        assertEquals(2,example.getId());
    }

    @Test
    void setData() {
       example.setData(LocalDate.of(2021,04,20));
       assertEquals(LocalDate.of(2021,04,20),example.getData());
    }

    @Test
    void setValor() {
        example.setValor(new BigDecimal(15.98));
        assertEquals(new BigDecimal(15.98),example.getValor());
    }

    @Test
    void testEquals() {

    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}