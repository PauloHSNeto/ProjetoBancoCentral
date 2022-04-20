package ibm.itau.projetobancocentral.configs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ProfileIntegrationTest {
    @Autowired
    EnviromentConfig enviromentConfig;

    @Test
    void test(){
        enviromentConfig.getfrase();
    }
}
