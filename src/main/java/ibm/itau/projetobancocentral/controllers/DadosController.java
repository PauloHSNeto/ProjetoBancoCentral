package ibm.itau.projetobancocentral.controllers;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.DadosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/dados")
public class DadosController {

    @Autowired
    private DadosServices dadosServices;

    @GetMapping
    public ResponseEntity<List<Dados>> getDados() {
        List<Dados> dados = dadosServices.findAll();
        return ResponseEntity.ok(dados);
    }
    //Get dados by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Dados> getDadosById(@PathVariable Long id) {
        Dados dados = dadosServices.findById(id);
        return ResponseEntity.ok(dados);
    }
    //TODO: Filtrar dados por dia, mes e ano
    @GetMapping(value = "/day/{day}")
    public ResponseEntity<List<Dados>> getDadosByDia(@PathVariable int day) {
        List<Dados> dados = dadosServices.findByDay(day);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/month/{month}")
    public ResponseEntity<List<Dados>> getDadosByMes(@PathVariable String month) {
        List<Dados> dados = dadosServices.findByMonth(month);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/year/{year}")
    public ResponseEntity<List<Dados>> getDadosByAno(@PathVariable int year) {
        List<Dados> dados = dadosServices.findByYear(year);
        return ResponseEntity.ok(dados);
    }
    @PostMapping
    public ResponseEntity<Dados> postDados(@RequestBody Map<String,String> body) {
        Dados dado  = dadosServices.save(body);
        return ResponseEntity.ok(dado);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Dados> deleteDados(@PathVariable Long id) {
        dadosServices.deleteById(id);
        return ResponseEntity.ok().build();
    }
}