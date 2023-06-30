package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.service.EtablissementService;
import cda.greta94.planexam.service.VilleService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/admin")
@Controller
public class AdminEtablissementController {

  private Logger logger = LoggerFactory.getLogger(AdminEtablissementController.class);

  private EtablissementService etablissementService;
  private VilleService villeService;

  public AdminEtablissementController(EtablissementService etablissementService, VilleService villeService) {
    this.etablissementService = etablissementService;
    this.villeService = villeService;
  }

  @GetMapping(value = "/etablissements")
  public String index(Model model) {
    model.addAttribute("etablissements", etablissementService.getAll());
    return "etablissement/list";
  }


  @GetMapping(value = "/etablissement")
  public String pushFormEtab(@ModelAttribute EtablissementDto etablissementDto) {
    return "etablissement/form";
  }

  @PostMapping(value = "/etablissement")
  public String addUpdateEtab(@Valid @ModelAttribute EtablissementDto etablissementDto, BindingResult bindingResult) {
    logger.info("addUpdateEtab : (" + etablissementDto + ")");
    logger.info("hasErrors = " + bindingResult.hasErrors());

    if (bindingResult.hasErrors()) {
      return "etablissement/form";
    }

    etablissementService.saveEtablissementFromEtablissementDto(etablissementDto);
    return "redirect:/admin/etablissements";
  }

  @GetMapping(value = "/etablissement/edit/{id}")
  public String pushFormEtab(@PathVariable("id") long id, Model model) {
    EtablissementDto etablissementDto = etablissementService.findEtablissementDtoById(id);
    model.addAttribute("etablissementDto", etablissementDto);
    return "etablissement/form";
  }

  @GetMapping(value = "/etablissement/import")
  public String formImportCSV() {
    return "/etablissement/formImportCSV";
  }

  @PostMapping(value = "/etablissement/import")
  public String importCSV(@RequestParam("file") MultipartFile file, RedirectAttributes redirAttrs) {

    if (file.isEmpty()) {
      // PATTERN PRG
      redirAttrs.addFlashAttribute("errorMessage", "Please select a file to upload");
      return "redirect:/admin/etablissement/import";
    }
    try {
      etablissementService.importEtablissementFromCSVFile(file);
    } catch (Exception e) {
      redirAttrs.addFlashAttribute("errorMessage",  e.getMessage() /*"Un problème est intervenu, est-ce le bon fichier ?"*/);
      return "redirect:/admin/etablissement/import";
    }
    // ok
    redirAttrs.addFlashAttribute("errorMessage", "Importation en cours d'implémentation !");
    return "redirect:/admin/etablissements";
  }

}
