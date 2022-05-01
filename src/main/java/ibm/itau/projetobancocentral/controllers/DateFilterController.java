package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.DateFilterServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dateFilters")
@RestController
@AllArgsConstructor
public class DateFilterController {

    @Autowired
    public DateFilterServices dateFilterServices;

    @GetMapping(value = "/day/{day}")
    public ResponseEntity<List<Dados>> getDadosByDia(@PathVariable int day) {
        List<Dados> dados = dateFilterServices.findByDay(day);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/month/{month}")
    public ResponseEntity<List<Dados>> getDadosByMes(@PathVariable String month) {
        List<Dados> dados = dateFilterServices.findByMonth(month);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/year/{year}")
    public ResponseEntity<List<Dados>> getDadosByAno(@PathVariable int year) {
        List<Dados> dados = dateFilterServices.findByYear(year);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value ="/between")
    public ResponseEntity<List<Dados>> getDadosBetween(@RequestParam String startDate, @RequestParam String endDate) {
        List<Dados> dados = dateFilterServices.findBetweenDates(startDate, endDate);
        return ResponseEntity.ok(dados);
    }
}
