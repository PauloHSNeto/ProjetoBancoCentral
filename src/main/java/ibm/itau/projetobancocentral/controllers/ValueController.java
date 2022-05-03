package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.DateFilterServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
@AllArgsConstructor
@RequestMapping("/values")
@RestController
@Tag(name = "Values", description = "Filtros para Valores da Divida e Consultas de medias e totais")
public class ValueController {
    @Autowired
    private CrudServices crudServices;
    @Autowired
    public ValueServices valueServices;
    @Autowired
    public DateFilterServices dateFilterServices;

    @Operation(summary = "Find Dados below total average", description = "Get a list of Dados with values below total average", tags = {"Value"})
    @GetMapping(value = "/below-average/")
    public ResponseEntity<List<Dados>> getBelowAverageTotal() {
        List<Dados> dados = valueServices.findBelowTotalAverage();
        return ResponseEntity.ok(dados);
    }
    @Operation(summary = "Find Dados above total average", description = "Get a list of Dados with values above total average", tags = {"Value"})
    @GetMapping(value = "/above-average/")
    public ResponseEntity<List<Dados>> getAboveAverageTotal() {
        List<Dados> dados = valueServices.findAboveTotalAverage();
        return ResponseEntity.ok(dados);
    }
    @Operation(summary = "Find Dados below year average", description = "Get a list of Dados with values below a year`s average", tags = {"Value"})
    @GetMapping(value = "/below-average/year/{year}/")
    public ResponseEntity<List<Dados>> getBelowAverageYear(@PathVariable("year") int year) {
        List<Dados> dados = valueServices.findBelowYearAverage(year);
        return ResponseEntity.ok(dados);
    }
    @Operation(summary = "Find Dados above year average", description = "Get a list of Dados with values above a year`s average", tags = {"Value"})
    @GetMapping(value = "/above-average/year/{year}/")
    public ResponseEntity<List<Dados>> getAboveAverageYear(@PathVariable("year") int year) {
        List<Dados> dados = valueServices.findAboveYearAverage(year);
        valueServices.updateDifference();
        return ResponseEntity.ok(dados);
    }
    @Operation(summary = "Values of Year", description = "Get Total values, number of Dados and Average of an year", tags = {"Value"})
    @GetMapping(value = "/total/{year}")
    public ResponseEntity<String> getAverageByYear(@PathVariable int year) {
        List<Dados> dados = dateFilterServices.findByYear(year);
        double media = valueServices.mediaByYear(year);
        double totalDoAno = valueServices.totalDoAno(year);
        String body ="Total de Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",totalDoAno) +"\n"
                + "Total de dados do ano " + year + ": " + dados.size() +"\n"
            + "Media de Dívida Líquida do Setor Público (% PIB) do ano " + year + ": " + String.format("%.2f",media)+"\n";
        valueServices.updateDifference();
        return ResponseEntity.ok(body);
    }
    @GetMapping(value = "/total")
    @Operation(summary = "Total Values", description = "Get Total values, number of Dados and Average of of the entire database", tags = {"Value"})
    public ResponseEntity<String> getTotal() {
        List<Dados> dados = crudServices.getAllDados();
        double media = valueServices.media();
        double total = valueServices.total();
        String body = "Total de Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",total) +"\n"
                + "Total de dados : " + dados.size()+"\n"
                + "Media de Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",media) +"\n";
        valueServices.updateDifference();
        return ResponseEntity.ok(body);
    }
    @GetMapping(value = "/max")
    @Operation(summary = "Max Value", description = "Get Dados with highest Value", tags = {"Value"})
    public ResponseEntity<String> getMax() {
        double max = valueServices.findByMaxValue().getValor();
        LocalDate data = valueServices.findByMaxValue().getData();
        String body = "Maior Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",max) +"\n"
                                + "Data : " + data +"\n";
        valueServices.updateDifference();
        return ResponseEntity.ok(body);
    }
    @GetMapping(value = "/min")
    @Operation(summary = "Min Value", description = "Get Dados with lowest Value", tags = {"Value"})
    public ResponseEntity<String> getMin() {
        double min = valueServices.findByMinValue().getValor();
        LocalDate data = valueServices.findByMinValue().getData();
        String body = "Menor Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",min) +"\n"
                                + "Data : " + data +"\n";
        valueServices.updateDifference();
        return ResponseEntity.ok(body);
    }
    @GetMapping(value ="/max/{year}")
    @Operation(summary = "Max Value Year", description = "Get Dados with highest Value of an Year", tags = {"Value"})
    public ResponseEntity<String> getMaxYear(@PathVariable int year) {
        double max = valueServices.findByMaxValueOfYear(year).getValor();
        LocalDate data = valueServices.findByMaxValueOfYear(year).getData();
        String body = "Maior Dívida Líquida do Setor Público (% PIB) do ano "+ year +" : " + String.format("%.2f",max) +"\n"
                        + "Data : " + data +"\n";
        valueServices.updateDifference();
        return ResponseEntity.ok(body);
    }
    @GetMapping(value ="/min/{year}")
    @Operation(summary = "Min Value Year", description = "Get Dados with lowest Value of an Year", tags = {"Value"})
    public ResponseEntity<String> getMinYear(@PathVariable int year) {
        double min = valueServices.findByMinValueofYear(year).getValor();
        LocalDate data = valueServices.findByMinValueofYear(year).getData();
        String body = "Menor Dívida Líquida do Setor Público (% PIB) do ano "+ year +" : " + String.format("%.2f",min) +"\n"
                        + "Data : " + data +"\n";
        valueServices.updateDifference();
        return ResponseEntity.ok(body);
    }
}
