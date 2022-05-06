package ibm.itau.projetobancocentral.controllers;
import ibm.itau.projetobancocentral.entities.Dados;
//import ibm.itau.projetobancocentral.repositories.DadosPagedRepository;
import ibm.itau.projetobancocentral.repositories.DadosRepository;
import ibm.itau.projetobancocentral.services.CrudServices;
//import ibm.itau.projetobancocentral.services.PagedServices;
import ibm.itau.projetobancocentral.services.PagedServices;
import ibm.itau.projetobancocentral.services.ValueServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/paged")
@Tag(name = "Paged", description = "Basic GetALL Pageable")
public class PagedCrudController {

    @Autowired
    private PagedServices pagedServices;

    @Operation(summary = "Show All Paged", description = "Show All Dados with Pagination", tags = {"Paged"})
    @GetMapping
    public ResponseEntity<Page<Dados>> getAllPagedController(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "data") String sort) {
        Page<Dados> dados = pagedServices.findAllPagedService(page, size, sort);
        if (dados.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Nenhum dado encontrado");
        } else {
            return new ResponseEntity<>(dados, HttpStatus.OK);
        }
    }
}