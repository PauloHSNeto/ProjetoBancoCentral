package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class PagedServicesTest {

    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    private ValueServices valueServices;
    @Autowired
    DateFilterServices dateFilterServices;
    @Autowired
    CrudServices crudServices;

    @Test
    void findAllPagedService() {
    }
}