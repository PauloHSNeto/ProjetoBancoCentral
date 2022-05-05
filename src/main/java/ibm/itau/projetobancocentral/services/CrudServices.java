package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class CrudServices {

    @Autowired
    private final DadosRepository dadosRepository;
    @Autowired
    private ValueServices valueServices;

    public List<Dados> getAllDados() {return dadosRepository.findAll();}

    public Dados findById(Long id) {return dadosRepository.findById(id).get();}

    public Dados save(Dados dado) {
        dadosRepository.save(dado);
        valueServices.updateDifference();
        return dado;}


    public void deleteById(Long id) {
        dadosRepository.deleteById(id);
        valueServices.updateDifference();
    }

    public Dados update(Long id, Dados dadoRecebido) {
        Dados dadosSalvo = dadosRepository.findById(id).get();
        dadosSalvo.setData(dadoRecebido.getData());
        dadosSalvo.setValor(dadoRecebido.getValor());
        dadosRepository.save(dadosSalvo);
        valueServices.updateDifference();
        return dadosSalvo;
    }
    public Dados findByData(LocalDate data){
        List<Dados> list = dadosRepository.findAll();
        for (Dados d : list){
            if (d.getData().equals(data)){
                return d;
            }
        }return null;
    }
//Paged service

    public Page<Dados> findAllPagedService(int size, String sort){
        int page = 0;
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                sort);
        valueServices.updateDifference();
        return new PageImpl<>(
                dadosRepository.findAll(),
                pageRequest, size);

    }

}