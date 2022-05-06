package ibm.itau.projetobancocentral.services;

import ibm.itau.projetobancocentral.entities.Dados;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PagedServices {

    @Autowired
    private final DadosRepository dadosRepository;
    @Autowired
    private ValueServices valueServices;
    @Autowired
    DateFilterServices dateFilterServices;
    @Autowired
    CrudServices crudServices;

    public Page<Dados> findAllPagedService(int page, int size, String sort) {
        return dadosRepository.findAll(PageRequest.of(page, size, Sort.by(sort).ascending()));
    }
}