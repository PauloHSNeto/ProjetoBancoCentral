package ibm.itau.projetobancocentral.controllers;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class CrudController {

    @Autowired
    private CrudServices crudServices;


    @GetMapping(value ="/dados")
    public ResponseEntity<List<Dados>> getDados() {
        List<Dados> dados = crudServices.getAllDados();
        return ResponseEntity.ok(dados);
    }
    //Get dados by id
    @GetMapping(value = "/{id}")
    public ResponseEntity<Dados> getDadosById(@PathVariable Long id) {
        Dados dados = crudServices.findById(id);
        return ResponseEntity.ok(dados);
    }
    @PostMapping
    public ResponseEntity<Dados> postDados(@RequestBody Map<String,Object> body) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(body.get("data").toString(),formatter);
        double valor = (double) body.get("valor");
        Dados dado = new Dados(data,valor);
        crudServices.save(dado);
        return ResponseEntity.ok(dado);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Dados> deleteDados(@PathVariable Long id) {
        crudServices.deleteById(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Dados> putDados(@PathVariable Long id, @RequestBody Map<String,Object> map) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data = LocalDate.parse(map.get("data").toString(),formatter);
        double valor = (double) map.get("valor");
        Dados dado = new Dados(id,data,valor);
        crudServices.update(id, dado);
        return ResponseEntity.ok(dado);
    }
}