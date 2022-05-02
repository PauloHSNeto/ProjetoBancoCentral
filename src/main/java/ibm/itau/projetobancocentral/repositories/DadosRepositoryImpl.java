package ibm.itau.projetobancocentral.repositories;

import ibm.itau.projetobancocentral.entities.Dados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DadosRepositoryImpl {

    @Autowired
    @Lazy
    private DadosRepository dadosRepository;

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


    public List<Dados> findAboveValue(double value) {
        List<Dados> list = dadosRepository.findAll();
        List<Dados> listaFiltrada =new ArrayList<Dados>();
        for (Dados d: list) {
            if (d.getValor()>=value) {
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }


    public List<Dados> findBelowValue(double value) {
        List<Dados> list = dadosRepository.findAll();
        List<Dados> listaFiltrada =new ArrayList<Dados>();
        for (Dados d: list) {
            if (d.getValor()<= value) {
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }


    public List<Dados> findByYearAboveValue(int year, double value) {
        List<Dados> list = dadosRepository.findByYear(year);
        List<Dados> listaFiltrada = new ArrayList<>();
        for (Dados d : list){
            if (d.getValor()>value){
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }


    public List<Dados> findByYearBelowValue(int year, double value) {
        List<Dados> list = dadosRepository.findByYear(year);
        List<Dados> listaFiltrada = new ArrayList<>();
        for (Dados d : list){
            if (d.getValor()<value){
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }

    List<Dados> findDadosBetweenDates(LocalDate localDate1, LocalDate localDate2){
        List<Dados> list = dadosRepository.findAll();
        List<Dados> listaFiltrada = new ArrayList<>();
        for (Dados d : list){
            if (d.getData().isAfter(localDate1.minusDays(1)) && d.getData().isBefore(localDate2.plusDays(1))){
                listaFiltrada.add(d);
            }
        }
        return listaFiltrada;
    }



}
