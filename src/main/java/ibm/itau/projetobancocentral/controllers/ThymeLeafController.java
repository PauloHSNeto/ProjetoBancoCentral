package ibm.itau.projetobancocentral.controllers;

import ibm.itau.projetobancocentral.services.CrudServices;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/thymeleaf")
@AllArgsConstructor
public class ThymeLeafController {
    @Autowired
    private CrudServices crudServices;

    @RequestMapping
    public String index(Model model) {
         {
            model.addAttribute("dados", crudServices.getAllDados());
            return "index";
        }
    }
}