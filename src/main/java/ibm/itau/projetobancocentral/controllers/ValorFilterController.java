package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
import ibm.itau.projetobancocentral.services.DataFilterServices;
import ibm.itau.projetobancocentral.services.ValorFilterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/valorfilters")
@RestController
public class ValorFilterController {
    @Autowired
    private CrudServices crudServices;
    @Autowired
    public ValorFilterServices valorFilterServices;
    @Autowired
    public DataFilterServices dataFilterServices;

    @GetMapping(value = "/below-average/")
    public ResponseEntity<List<Dados>> getBelowAverageTotal() {
        List<Dados> dados = valorFilterServices.findBelowTotalAverage();
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/above-average/")
    public ResponseEntity<List<Dados>> getAboveAverageTotal() {
        List<Dados> dados = valorFilterServices.findAboveTotalAverage();
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/below-average/year/{year}/")
    public ResponseEntity<List<Dados>> getBelowAverageMonth(@PathVariable("year") int year) {
        List<Dados> dados = valorFilterServices.findBelowYearAverage(year);
        return ResponseEntity.ok(dados);
    }
    @GetMapping(value = "/above-average/year/{year}/")
    public ResponseEntity<List<Dados>> getAboveAverageMonth(@PathVariable("year") int year) {
        List<Dados> dados = valorFilterServices.findAboveYearAverage(year);
        return ResponseEntity.ok(dados);
    }

    @GetMapping(value = "/total/{year}")
    public ResponseEntity<String> getAverageByYear(@PathVariable int year) {
        List<Dados> dados = dataFilterServices.findByYear(year);
        double media = valorFilterServices.mediaByYear(year);
        double totalDoAno = valorFilterServices.totalDoAno(year);
        String body ="Total de Dívida Líquida do Setor Público (% PIB) : " + totalDoAno +"\n"
                + "Total de dados do ano " + year + ": " + dados.size() +"\n"
            + "Media de Dívida Líquida do Setor Público (% PIB) do ano " + year + ": " + media+"\n";

        return ResponseEntity.ok(body);
    }

    @GetMapping(value = "/total")
    public ResponseEntity<String> getTotal() {
        List<Dados> dados = crudServices.findAll();
        double media = valorFilterServices.media();
        double total = valorFilterServices.total();
        String body = "Total de Dívida Líquida do Setor Público (% PIB) : " + total +"\n"
                + "Total de dados do ano : " + dados.size()+"\n"
                + "Media de Dívida Líquida do Setor Público (% PIB) : " + media +"\n";

        return ResponseEntity.ok(body);
    }






}
