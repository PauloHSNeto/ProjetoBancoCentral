package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.DateFilterServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/dateFilters")
@RestController
@AllArgsConstructor
@Tag(name = "Date Filters", description = "Basic Crud Functions")
public class DateFilterController {

    @Autowired
    public DateFilterServices dateFilterServices;
    @Autowired
    public ValueServices valueServices;

    @Operation(summary = "Show Dados by day", description = "Get a list of Dados by its day of the month", tags = {"Date Filters"})
    @GetMapping(value = "/day/{day}")
    public ResponseEntity<List<Dados>> getDadosByDia(@PathVariable int day) {
        List<Dados> dados = dateFilterServices.findByDay(day);
        return ResponseEntity.ok(dados);
    }
    @Operation(summary = "Show Dados by month", description = "Get a list of Dados by its month`s name", tags = {"Date Filters"})
    @GetMapping(value = "/month/{month}")
    public ResponseEntity<List<Dados>> getDadosByMes(@PathVariable String month) {
        List<Dados> dados = dateFilterServices.findByMonth(month);
        return ResponseEntity.ok(dados);
    }
    @Operation(summary = "Show Dados by year", description = "Get a list of Dados from an year", tags = {"Date Filters"})
    @GetMapping(value = "/year/{year}")
    public ResponseEntity<List<Dados>> getDadosByAno(@RequestParam(defaultValue = "data") String sortBy, @PathVariable int year) {
        List<Dados> dados = dateFilterServices.findByYear(year);
        valueServices.sortByValorOrDate(dados, sortBy);
        return ResponseEntity.ok(dados);
    }
    @Operation(summary = "Show Dados by Between dates", description = "Get a list of Dados between two dates", tags = {"Date Filters"})
    @GetMapping(value ="/between")
    public ResponseEntity<List<Dados>> getDadosBetween(@RequestParam String startDate, @RequestParam String endDate) {
        List<Dados> dados = dateFilterServices.findBetweenDates(startDate, endDate);
        return ResponseEntity.ok(dados);
    }
    @GetMapping()
    @Operation(summary = "Show Dados by date", description = "Get a list of Dados by its date 'yyyy-MM-dd'", tags = {"Date Filters"})
    public ResponseEntity<Dados> getDadosByDate(@RequestParam String date) {
        Dados dado = null;
        try {
            dado = dateFilterServices.findByDate(date);
        } catch (Exception e) {
            throw new RuntimeException("Data não encontrada", e);
        }
        return ResponseEntity.ok(dado);
    }
}
