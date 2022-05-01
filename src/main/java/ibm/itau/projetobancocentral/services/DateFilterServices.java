package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class DateFilterServices {

    @Autowired
    private DadosRepository dadosRepository;

    public List<Dados> findByDay(int day) {return dadosRepository.findByDay(day);}
    public List<Dados> findByYear(int year) {
        return dadosRepository.findByYear(year);
    }
    public List<Dados> findByMonth(String month) {
        return dadosRepository.findByMonth(month);
    }

    //encrotrar dados entre datas
    public List<Dados> findBetweenDates(String startDate, String endDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate,formatter);
        LocalDate end = LocalDate.parse(endDate,formatter);

        List<Dados> result = new ArrayList<>();
        for (Dados d : dadosRepository.findAll()) {
            if (d.getData().isAfter(start.minusDays(1)) && d.getData().isBefore(end.plusDays(1))) {
                result.add(d);
            }
        }
        return result;
    }


    //Para a funcao de UpdateValores
    public Dados findFirstEntryBeforeDate(Dados dados) {
        Dados answer =  new Dados();
        for (Dados d : dadosRepository.findAll()) {
            if (d.getData().isBefore(dados.getData())) {
                answer = d;
            }
        }
        return answer;
    }
    public Dados findFirstEntryAfterDate(Dados dados) {
        Dados answer =  new Dados();
        for (Dados d : dadosRepository.findAll()) {
            if (d.getData().isAfter(dados.getData())) {
                answer = d;
            }
        }
        return answer;
    }
}
