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
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PagedServices {

    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    DateFilterServices dateFilterServices;
    @Autowired
    CrudServices crudServices;

    public Page<Dados> findAllPagedService(int page, int size, String sort, int year) {
        if (year != 0) {
            List<Dados> dados = dadosRepository.findByYear(year);
            Page<Dados> dadosPage = new PageImpl<>(dados, PageRequest.of(page, size), 1);
            return dadosPage;
        }else {
            return dadosRepository.findAll(PageRequest.of(page, size, Sort.by(sort).ascending()));
        }
    }
    public Page<Dados> findPagedService(int page, int size, Long id, String date) {
        List<Dados> dados = dadosRepository.findAll();
        Dados result = new Dados();
        if (id == 0L) {
            LocalDate requestedDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            result = dados.stream().filter(d -> d.getData().equals(requestedDate)).findFirst().get();
        } else if (date == "not-requested") {
            result = dados.stream().filter(d -> d.getId().equals(id)).findFirst().get();
        }
        Page<Dados> dadosPage = new PageImpl<>(List.of(result), PageRequest.of(page, size), 1);
        return dadosPage;
    }
}