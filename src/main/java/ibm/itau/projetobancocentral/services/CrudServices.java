package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CrudServices {

    @Autowired
    private final DadosRepository dadosRepository;

    public List<Dados> getAllDados() {return dadosRepository.findAll();}

    public Dados findById(Long id) {return dadosRepository.findById(id).get();}

    public Dados save(Dados dado) {return dadosRepository.save(dado);}
    public void deleteById(Long id) {
        dadosRepository.deleteById(id);
    }
    public Dados update(Long id, Dados dadoRecebido) {
        Dados dadosSalvo = dadosRepository.findById(id).get();
        dadosSalvo.setData(dadoRecebido.getData());
        dadosSalvo.setValor(dadoRecebido.getValor());
        return dadosRepository.save(dadosSalvo);
    }
}