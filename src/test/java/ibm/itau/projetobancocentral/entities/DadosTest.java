package ibm.itau.projetobancocentral.entities;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DadosTest {
    @Test
    void constructorWithAllArgsTest() {
        //given
        Dados dados = new Dados(1L,null,0.0);
        //when
        Dados dados2 = new Dados(1L,null,0.0);
        //then
        assertEquals(dados, dados2);
    }
    @Test
    void constructorWithDataandValorTest() {
        //given
        Dados dados = new Dados(LocalDate.now(),100);
        //when
        Dados dados2 = new Dados(LocalDate.now(),100);
        //then
        assertEquals(dados, dados2);
    }
    @Test
    void getIdTest() {
        //given
        Dados d = new Dados();
        Long id = 1L;
        //when
        d.setId(id);
        //then
        assertEquals(1L, d.getId());
    }
    @Test
    void getDataTest() {
        //given
        Dados d = new Dados();
        LocalDate date = LocalDate.of(2020, 1, 1);
        //when
        d.setData(date);
        //then
        assertEquals(date, d.getData());
    }
    @Test
    void getValorTest() {
        //given
        Dados d = new Dados();
        double valor = 100;
        //when
        d.setValor(valor);
        //then
        assertEquals(valor, d.getValor());
    }
    @Test
    void setIdTest() {
        //given
        Dados d = new Dados();
        Long id = 1L;
        //when
        d.setId(id);
        //then
        assertEquals(1L, d.getId());
    }
    @Test
    void setDataTest()  {
        //given
        Dados d = new Dados();
        LocalDate date = LocalDate.of(2020, 1, 1);
        //when
        d.setData(date);
        //then
        assertEquals(date, d.getData());
    }

    @Test
    void setValorTest() {
        //given
        Dados d = new Dados();
        double valor = 100;
        //when
        d.setValor(valor);
        //then
        assertEquals(valor, d.getValor());
    }

    @Test
    void testEqualsTest() {
        //given
        Dados d = new Dados(LocalDate.now(),100);
        Dados d2 = new Dados(LocalDate.now(),100);
        //when
        assertTrue(d.equals(d2));
    }

    @Test
    void canEqualTest() {
        //given
        Dados d = new Dados();
        Dados d2 = new Dados();
        //when
        assertTrue(d.canEqual(d2));
    }

    @Test
    void testHashCodeTest() {
        //given
        Dados d = new Dados(LocalDate.now(),100);
        Dados d2 = new Dados(LocalDate.now(),100);
        //then
        assertEquals(d.hashCode(), d2.hashCode());
    }

    @Test
    void testToStringTest() {
        //given
        Dados d = new Dados(null,100);
        //then
        assertEquals("Dados(id=null, data=null, valor=100.0)", d.toString());
    }
}