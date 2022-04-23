package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class DadosServices {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @Autowired
    private DadosRepository dadosRepository;
    @Autowired
    private RestTemplate restTemplate;


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
            if (d.getData().getMonth().toString().equals(month.toUpperCase())) {//filter by month
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
            if (d.getData().getYear() == year) { //filter by year
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }
    public Dados save(Map<String,Object> map) {
        LocalDate data = LocalDate.parse(map.get("data").toString(), formatter);
        BigDecimal valor = new BigDecimal(map.get("valor").toString());

        Dados dadosSalvo = new Dados(data,valor);
        dadosRepository.save(dadosSalvo);
        return dadosSalvo;
    }
    public void deleteById(Long id) {
        dadosRepository.deleteById(id);
    }
    public BigDecimal total(int year) {
        List<Dados> list = dadosRepository.findAll();
        List<Dados> listaFiltrada =new ArrayList<Dados>();
        BigDecimal total = new BigDecimal(0);
        for (Dados d: list) {
            if (d.getData().getYear() == year) {
                total = total.add(d.getValor());
                listaFiltrada.add(d);
            }
        }
        return total;
    }
    public Dados update(Long id, Map<String,Object> dado){
        Dados dadosSalvo = dadosRepository.findById(id).get();
        dadosSalvo.setData(LocalDate.parse(dado.get("data").toString(), formatter));
        dadosSalvo.setValor(new BigDecimal(dado.get("valor").toString()));
        dadosRepository.save(dadosSalvo);
        return dadosSalvo;
    }
    public void onboarding(String url){
        Dados[] arraysDeDados = restTemplate.getForObject(url, Dados[].class);
        dadosRepository.saveAll(Arrays.asList(arraysDeDados));
    }

}




