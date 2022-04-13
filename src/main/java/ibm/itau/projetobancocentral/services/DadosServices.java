package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DadosServices {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Autowired
    private DadosRepository dadosRepository;

    public List<Dados> findAll() {
        return dadosRepository.findAll();
    }
    public Dados findById(Long id) {
        return dadosRepository.findById(id).get();
    }
    public List<Dados> findByMonth(String month) {
        List<Dados> list = dadosRepository.findAll();
        List<Dados> listaFiltrada =new ArrayList<Dados>();
        for (Dados d: list) {
            if (d.getData().getMonth().toString().equals(month.toUpperCase())) {
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }
    public List<Dados> findByDay(int day) {
        List<Dados> list = dadosRepository.findAll();
        List<Dados> listaFiltrada =new ArrayList<Dados>();
        for (Dados d: list) {
            if (d.getData().getDayOfMonth() == day) {
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }
    public List<Dados> findByYear(int year) {
        List<Dados> list = dadosRepository.findAll();
        List<Dados> listaFiltrada =new ArrayList<Dados>();
        for (Dados d: list) {
            if (d.getData().getYear() == year) {
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }
    public Dados save(Map<String,String> map) {

        LocalDate data = LocalDate.parse(map.get("data"), formatter);
        BigDecimal valor = new BigDecimal(map.get("valor"));

        Dados dadosSalvo = new Dados(data,valor);
        dadosRepository.save(dadosSalvo);
        return dadosSalvo;
    }
    public void deleteById(Long id) {
        dadosRepository.deleteById(id);
    }

}


