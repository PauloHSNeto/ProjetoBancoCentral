package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.DataFilterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/datafilters")
@RestController
public class DataFilterController {

    @Autowired
    public DataFilterServices dataFilterServices;

    @GetMapping(value = "/day/{day}")
    public ResponseEntity<List<Dados>> getDadosByDia(@PathVariable int day) {
        List<Dados> dados = dataFilterServices.findByDay(day);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/month/{month}")
    public ResponseEntity<List<Dados>> getDadosByMes(@PathVariable String month) {
        List<Dados> dados = dataFilterServices.findByMonth(month);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/year/{year}")
    public ResponseEntity<List<Dados>> getDadosByAno(@PathVariable int year) {
        List<Dados> dados = dataFilterServices.findByYear(year);
        return ResponseEntity.ok(dados);
    }



}