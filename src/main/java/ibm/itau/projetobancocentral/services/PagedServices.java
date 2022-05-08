package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PagedServices {
    @Autowired
   private ValueServices valueService;
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    DateFilterServices dateFilterServices;
    @Autowired
    CrudServices crudServices;

    public Page<Dados> findAllPagedService(int page, int size, String sort, int year) {
        List<Dados> dados = new ArrayList<>();
        if (year != 0) {
           dados = dadosRepository.findByYear(year);
        }else {
            dados = dadosRepository.findAll();
        }
        valueService.sortByValorOrDate(dados, sort);
        Page<Dados> dadosPage = new PageImpl<>(dados, PageRequest.of(page, size), 1);
        return dadosPage;
    }
    public Page<Dados> findPagedService(int page, int size, Long id, String date) {
        List<Dados> dados = dadosRepository.findAll();
        Dados result = new Dados();
        if (id == 1L) {
            result = dateFilterServices.findByDate(date);
        } else if (date == "not-requested") {
            result = crudServices.findById(id);
        }
        Page<Dados> dadosPage = new PageImpl<>(List.of(result), PageRequest.of(page, size), 1);
        return dadosPage;
    }
    public Page<Dados> findPagedValueService(int page, int size, String maxMin, int year) {
        Dados result = new Dados();
        if (maxMin == "max" && year == 0) result = valueService.findByMaxValue();
        else if (maxMin == "min" && year == 0) result = valueService.findByMinValue();
        if (maxMin == "max" && year != 0) result = valueService.findByMaxValueOfYear(year);
        else if (maxMin == "min" && year != 0) result = valueService.findByMinValueofYear(year);
        Page<Dados> dadosPage = new PageImpl<Dados>(List.of(result), PageRequest.of(page, size), 1);
        return dadosPage;
    }
    public Page<Dados> findBetweenPagedService(int page, int size, String sort, String startDate, String endDate) {
           List<Dados> dados = dateFilterServices.findBetweenDates(startDate, endDate);
           valueService.sortByValorOrDate(dados, sort);
           Page<Dados> dadosPage = new PageImpl<>(dados, PageRequest.of(page, size), 1);
        return dadosPage;
    }
}