package ibm.itau.projetobancocentral.controllers;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Basic CRUD", description = "Basic Crud Functions")
public class CrudController {

    @Autowired
    private CrudServices crudServices;
    @Autowired
    private ValueServices valueServices;

    @Operation(summary = "Show All Dados", description = "Get All Dados and Filters them by date or value", tags = {"Basic CRUD"})
    @GetMapping(value ="/")
    public ResponseEntity<List<Dados>> getDados(@RequestParam(defaultValue = "data") String sortBy) {
        List<Dados> dadosList = crudServices.getAllDados();
       valueServices.sortByValorOrDate(dadosList, sortBy);
       if(dadosList.isEmpty()) {
           throw  new ResponseStatusException(HttpStatus.NO_CONTENT,"Nenhum Valor Encontrado Na Database");
       } else return ResponseEntity.ok(dadosList);

    }
    @Operation(summary = "Show Dados by Id", description = "Get a Dados by its id", tags = {"Basic CRUD"})
    @GetMapping(value = "/{id}")
    public ResponseEntity<Dados> getDadosById(@PathVariable Long id) {
        Dados dados = null;
        try {
            dados = crudServices.findById(id);
            return ResponseEntity.ok(dados);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dado com id: "+ id +" não encontrado", e);
        }
    }
    @Operation(summary = "Post a new Dados", description = "Create a new Dados with an unused date", tags = {"Basic CRUD"})
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
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Dado com a data "+ dado.getData() + " já existe");
        }
    }
    @Operation(summary = "Delete Dados by Id", description = "Delete a Dados by its id", tags = {"Basic CRUD"})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteDados(@PathVariable Long id) {
        try {
            crudServices.deleteById(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dado com id: "+ id +" não encontrado", e);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Dado de id: " + id + " deletado com sucesso");
    }
    @Operation(summary = "Update Dados by Id", description = "Update a Dados by its id", tags = {"Basic CRUD"})
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não foi possível converter os valores", e);
        }
        try {
            crudServices.update(id, dado);
        } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dado com id: "+ id +" não encontrado", e);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dado);
    }
}