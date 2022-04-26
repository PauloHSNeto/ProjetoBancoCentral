package ibm.itau.projetobancocentral.services;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@AllArgsConstructor
@Service
public class ValueServices {

    @Autowired
    private DadosRepository dadosRepository;

    public List<Dados> findByValorAbove(double valor) {
        return dadosRepository.findAboveValue(valor);
    }
    public List<Dados> findByValorBellow(double valor) {
        return dadosRepository.findBelowValue(valor);
    }

    public List<Dados> findAboveYearAverage(int year) {
        return dadosRepository.findByYearAboveValue(year,mediaByYear(year));
    }
    public List<Dados> findBelowYearAverage(int year) {
        return dadosRepository.findByYearBelowValue(year,mediaByYear(year));
    }
    public List<Dados> findBelowTotalAverage() {
        return dadosRepository.findBelowValue(media());
    }
    public List<Dados> findAboveTotalAverage() {
        return dadosRepository.findAboveValue(media());
    }


    public double total() {
        double total = 0;
        for (Dados d:  dadosRepository.findAll()) {
            total += d.getValor();
        }
        return total;
    }



    public double totalDoAno(int year) {
        double total = 0;
        for (Dados d: dadosRepository.findByYear(year)) {
                total += d.getValor();
            }
        return total;
    }
    public double media() {
        List<Dados> list = dadosRepository.findAll();
        return total()/list.size();
    }
    public double mediaByYear(int year) {
        List<Dados> list = dadosRepository.findByYear(year);
        return totalDoAno(year)/list.size();
    }
}
