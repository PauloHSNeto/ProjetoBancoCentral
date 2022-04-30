package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class DateFilterServices {

    @Autowired
    private DadosRepository dadosRepository;

    public List<Dados> findByDay(int day) {
        return dadosRepository.findByDay(day);
    }
    public List<Dados> findByYear(int year) {
        return dadosRepository.findByYear(year);
    }

    public List<Dados> findByMonth(String month) {
        return dadosRepository.findByMonth(month);
    }

    public List<Dados> findDadosBetweenDates(LocalDate localDate1, LocalDate localDate2) {
        List<Dados> dados = dadosRepository.findAll();
        List<Dados> dadosFiltrados = new ArrayList<>();
        for (Dados d : dados) {
            if (d.getData().isAfter(localDate1.minusDays(1)) && d.getData().isBefore(localDate2.minusDays(1))) {
                dadosFiltrados.add(d);
            }
        }
        return dadosFiltrados;
    }
}
