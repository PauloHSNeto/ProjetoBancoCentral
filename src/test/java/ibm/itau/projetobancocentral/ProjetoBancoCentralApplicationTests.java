package ibm.itau.projetobancocentral;

import ibm.itau.projetobancocentral.entities.Dados;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProjetoBancoCentralApplicationTests {

    @Test
    void contextLoads() {
        //given
        String name = "Paulo Henrique da Silva Neto";
        int id = 123456789;
        //when
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("id", id);
        //then
        assertEquals(name, map.get("name"));
        assertEquals(id, map.get("id"));



    }

}
