package ibm.itau.projetobancocentral.controllers;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/dados")
@AllArgsConstructor
public class CrudController {

    @Autowired
    private CrudServices crudServices;
    @Autowired
    private ValueServices valueServices;

    @GetMapping(value ="/")
    public ResponseEntity<List<Dados>> getDados(@RequestParam(defaultValue = "data") String sortBy) {
        List<Dados> dadosList = crudServices.getAllDados();
       valueServices.sortByValorOrDate(dadosList, sortBy);
       valueServices.updateDifference();
        return ResponseEntity.ok(dadosList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Dados> getDadosById(@PathVariable Long id) {
        Dados dados = null;
        try {
            dados = crudServices.findById(id);
            return ResponseEntity.ok(dados);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dado não encontrado", e);
        }
    }
    @PostMapping(value ="/")
    public ResponseEntity<Dados> postDados(@RequestBody Map<String,Object> body) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Dados dado = null;
        try {
            dado = new Dados(
                        /*data*/   LocalDate.parse(body.get("data").toString(),formatter),
                        /*valor*/  (double) body.get("valor"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível converter os dados", e);
        }

        if(crudServices.findByData(dado.getData()) == null) {// verifica se já existe um dado com a data informada
            crudServices.save(dado);
            return ResponseEntity.status(HttpStatus.CREATED).body(dado);
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Dado já existe");
        }
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteDados(@PathVariable Long id) {
        try {
            crudServices.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dado não encontrado", e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Dado de id: " + id + " deletado com sucesso");
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Dados> putDados(@PathVariable Long id, @RequestBody Map<String,Object> map) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Dados dado = null;
        try {
            dado = new Dados(
                    id,
                    LocalDate.parse(map.get("data").toString(),formatter),
                    (double) map.get("valor"));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível converter os dados", e);
        }
        try {
            crudServices.update(id, dado);
        } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dado não encontrado", e);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dado);
    }
}