package ibm.itau.projetobancocentral.controllers;
import ibm.itau.projetobancocentral.entities.Dados;
//import ibm.itau.projetobancocentral.repositories.DadosPagedRepository;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
//import ibm.itau.projetobancocentral.services.PagedServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/paged")
public class PagedCrudController {

    @Autowired
    private CrudServices crudServices;

    @GetMapping
    public Page<Dados> getAllPagedController(
            @RequestParam(value = "size", required=false) int size,
            @RequestParam(value = "sort", defaultValue ="data") String sort
          ){
         return crudServices.findAllPagedService(size, sort);
    }
}
