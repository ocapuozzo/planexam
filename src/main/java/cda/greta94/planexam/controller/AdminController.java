package cda.greta94.planexam.controller;

import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.service.EtablissementService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/admin")
@Controller
public class AdminController {

    Logger logger = LoggerFactory.getLogger(AdminController.class);

    private EtablissementService etablissementService;

  public AdminController(EtablissementService etablissementService) {
    this.etablissementService = etablissementService;
  }

  @GetMapping(value = "/etab")
    public String index(@ModelAttribute EtablissementDto etablissementDto){
        return "etablissement/form";
    }

    @PostMapping(value = "/etab")
    public String addUpdateEtab(@Valid @ModelAttribute EtablissementDto etablissementDto, BindingResult bindingResult) {
       logger.info("addUpdateEtab (" + etablissementDto + ")");
       logger.info("hasErrors = " + bindingResult.hasErrors());
       return "redirect:/";
    }
}
