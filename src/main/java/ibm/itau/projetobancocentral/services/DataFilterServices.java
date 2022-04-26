package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class DataFilterServices {

    @Autowired
    private DadosRepository dadosRepository;
    public List<Dados> findByDay(int day) {return dadosRepository.findByDay(day);}
    public List<Dados> findByYear(int year) {
        return dadosRepository.findByYear(year);
    }
    public List<Dados> findByMonth(String month) {
        return dadosRepository.findByMonth(month);
    }
}
