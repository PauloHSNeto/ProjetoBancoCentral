package ibm.itau.projetobancocentral.services;
import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.datatransfer.Clipboard;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
@AllArgsConstructor
@Service
public class ValueServices {

    @Autowired
    private DadosRepository dadosRepository;

    public List<Dados> findByValorAbove(double valor) {
        return dadosRepository.findAboveValue(valor);
    }
    public List<Dados> findByValorBelow(double valor) {
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

    public void updateDifference() {
        List<Dados> list = dadosRepository.findAll();
        list.sort((o1, o2) -> o1.getData().compareTo(o2.getData()));
        for (int i = 1; i < list.size(); i++) {//atualiza a diferenca entre valores
               double diference = (list.get(i).getValor())-((list.get(i-1).getValor()));
               list.get(i).setDifference(BigDecimal.valueOf(diference));
                dadosRepository.save(list.get(i));
            }
        }
    public Dados findByMaxValue() {
        List<Dados> list = dadosRepository.findAll();
        list.sort((o1, o2) -> o1.getValor().compareTo(o2.getValor()));
        return list.get(list.size()-1);
        }
    public Dados findByMinValue() {
        List<Dados> list = dadosRepository.findAll();
        list.sort((o1, o2) -> o1.getValor().compareTo(o2.getValor()));
        return list.get(0);
    }
    public Dados findByMaxValueOfYear(int year) {
        List<Dados> list = dadosRepository.findByYear(year);
        list.sort((o1, o2) -> o1.getValor().compareTo(o2.getValor()));
        return list.get(list.size()-1);
    }
    public Dados findByMinValueofYear(int year) {
        List<Dados> list = dadosRepository.findByYear(year);
        list.sort((o1, o2) -> o1.getValor().compareTo(o2.getValor()));
        return list.get(0);
    }
    public void sortByValorOrDate(List<Dados> list, String sortBy) {
        if (sortBy.equals("valor")) {
            list.sort(Comparator.comparing(Dados::getValor));
        }
        else {
            list.sort(Comparator.comparing(Dados::getData));
        }
    }
}
