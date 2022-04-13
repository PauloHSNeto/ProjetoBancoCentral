package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.service.DadosConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/dados")
public class DadosController {
    @Autowired
    private DadosRepository dadosRepository;

    @GetMapping
    public ResponseEntity<List<Dados>> getDados() {
        List<Dados> dados = dadosRepository.findAll();
        return ResponseEntity.ok(dados);
    }
    //Get dados by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Dados> getDadosById(@PathVariable Long id) {
        Dados dados = dadosRepository.findById(id).get();
        return ResponseEntity.ok(dados);
    }

}
