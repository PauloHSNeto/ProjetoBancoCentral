package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.DateFilterServices;
import ibm.itau.projetobancocentral.services.ValueServices;
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
public class ValueController {
    @Autowired
    private CrudServices crudServices;
    @Autowired
    public ValueServices valueServices;
    @Autowired
    public DateFilterServices dateFilterServices;

    @GetMapping(value = "/below-average/")
    public ResponseEntity<List<Dados>> getBelowAverageTotal() {
        List<Dados> dados = valueServices.findBelowTotalAverage();
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/above-average/")
    public ResponseEntity<List<Dados>> getAboveAverageTotal() {
        List<Dados> dados = valueServices.findAboveTotalAverage();
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/below-average/year/{year}/")
    public ResponseEntity<List<Dados>> getBelowAverageYear(@PathVariable("year") int year) {
        List<Dados> dados = valueServices.findBelowYearAverage(year);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/above-average/year/{year}/")
    public ResponseEntity<List<Dados>> getAboveAverageYear(@PathVariable("year") int year) {
        List<Dados> dados = valueServices.findAboveYearAverage(year);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/total/{year}")
    public ResponseEntity<String> getAverageByYear(@PathVariable int year) {
        List<Dados> dados = dateFilterServices.findByYear(year);
        double media = valueServices.mediaByYear(year);
        double totalDoAno = valueServices.totalDoAno(year);
        String body ="Total de Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",totalDoAno) +"\n"
                + "Total de dados do ano " + year + ": " + dados.size() +"\n"
            + "Media de Dívida Líquida do Setor Público (% PIB) do ano " + year + ": " + String.format("%.2f",media)+"\n";
        return ResponseEntity.ok(body);
    }
    @GetMapping(value = "/total")
    public ResponseEntity<String> getTotal() {
        List<Dados> dados = crudServices.getAllDados();
        double media = valueServices.media();
        double total = valueServices.total();
        String body = "Total de Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",total) +"\n"
                + "Total de dados : " + dados.size()+"\n"
                + "Media de Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",media) +"\n";
        return ResponseEntity.ok(body);
    }
    @GetMapping(value = "/max")
    public ResponseEntity<String> getMax() {
        double max = valueServices.findByMaxValue().getValor();
        LocalDate data = valueServices.findByMaxValue().getData();
        String body = "Maior Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",max) +"\n"
                                + "Data : " + data +"\n";
        return ResponseEntity.ok(body);
    }
    @GetMapping(value = "/min")
    public ResponseEntity<String> getMin() {
        double min = valueServices.findByMinValue().getValor();
        LocalDate data = valueServices.findByMinValue().getData();
        String body = "Menor Dívida Líquida do Setor Público (% PIB) : " + String.format("%.2f",min) +"\n"
                                + "Data : " + data +"\n";
        return ResponseEntity.ok(body);
    }
    @GetMapping(value ="/max/{year}")
    public ResponseEntity<String> getMaxYear(@PathVariable int year) {
        double max = valueServices.findByMaxValueOfYear(year).getValor();
        LocalDate data = valueServices.findByMaxValueOfYear(year).getData();
        String body = "Maior Dívida Líquida do Setor Público (% PIB) do ano "+ year +" : " + String.format("%.2f",max) +"\n"
                        + "Data : " + data +"\n";
        return ResponseEntity.ok(body);
    }
    @GetMapping(value ="/min/{year}")
    public ResponseEntity<String> getMinYear(@PathVariable int year) {
        double min = valueServices.findByMinValueofYear(year).getValor();
        LocalDate data = valueServices.findByMinValueofYear(year).getData();
        String body = "Menor Dívida Líquida do Setor Público (% PIB) do ano "+ year +" : " + String.format("%.2f",min) +"\n"
                        + "Data : " + data +"\n";
        return ResponseEntity.ok(body);
    }
}
