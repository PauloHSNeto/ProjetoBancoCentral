package ibm.itau.projetobancocentral.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DadosTest {

    Dados d = new Dados(LocalDate.now(),100);

    @Test
    void getId() {
        Long id = null;
        assertEquals(id, d.getId());
    }

    @Test
    void getData() {
        LocalDate date = LocalDate.now();
        assertEquals(date, d.getData());
    }

    @Test
    void getValor() {
        assertEquals(100, d.getValor());
    }

    @Test
    void setId() {
        Long id = 1L;
        d.setId(id);
        assertEquals(id, d.getId());
    }

    @Test
    void setData()  {
        LocalDate date = LocalDate.of(2020, 1, 1);
        d.setData(date);
        assertEquals(date, d.getData());
    }

    @Test
    void setValor() {

        d.setValor(100);
        assertEquals(100, d.getValor());
    }

    @Test
    void testEquals() {
        Dados d2 = new Dados(LocalDate.now(),100);
        assertEquals(d, d2);
    }

    @Test
    void canEqual() {
        Dados d2 = new Dados(LocalDate.now(),100);
        assertTrue(d.canEqual(d2));
    }

    @Test
    void testHashCode() {
        Dados d2 = new Dados(LocalDate.now(),100);
        assertEquals(d.hashCode(), d2.hashCode());
    }

    @Test
    void testToString() {
        String s = "Dados(" +
                "id=" + d.getId() +
                ", data=" + d.getData() +
                ", valor=" + d.getValor() +
                ')';
        assertEquals(s, d.toString());
    }
}