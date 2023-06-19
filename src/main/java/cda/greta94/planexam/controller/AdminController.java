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

  @GetMapping(value = "/etablissements")
  public String index(Model model){
    model.addAttribute("etablissements", etablissementService.getAll());
    return "etablissement/list";
  }


  @GetMapping(value = "/etablissement")
    public String pushFormEtab(@ModelAttribute EtablissementDto etablissementDto){
        return "etablissement/form";
    }


  @GetMapping(value = "/etablissement/edit/{id}")
  public String pushFormEtab(@PathVariable("id") long id, Model model){
    EtablissementDto etablissementDto = etablissementService.findEtablissementDtoById(id);
    model.addAttribute("etablissementDto", etablissementDto);
    return "etablissement/form";
  }


  @PostMapping(value = "/etablissement")
    public String addUpdateEtab(@Valid @ModelAttribute EtablissementDto etablissementDto, BindingResult bindingResult) {
       logger.info("addUpdateEtab (" + etablissementDto + ")");
       logger.info("hasErrors = " + bindingResult.hasErrors());

       if(bindingResult.hasErrors()) {
         return "etablissement/form";
       }

       etablissementService.saveEtablissementFromEtablissementDto(etablissementDto);
       return "redirect:/admin/etablissements";
    }
}
