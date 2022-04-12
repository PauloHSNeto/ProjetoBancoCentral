package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.service.DadosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dados")

public class DadosController {
    @Autowired
    private DadosRepository dadosRepository;

    @Autowired
    private DadosService dadosService;

    @GetMapping
    public String todosOsDados() {
        String lista  = dadosService.getDados();
        return lista;
    }
}
