package cda.greta94.planexam.controller;

import cda.greta94.planexam.dto.EtablissementDto;
import cda.greta94.planexam.service.EtablissementService;
import jakarta.validation.Valid;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;

@RequestMapping("/admin")
@Controller
public class AdminController {

  private Logger logger = LoggerFactory.getLogger(AdminController.class);

  private EtablissementService etablissementService;

  public AdminController(EtablissementService etablissementService) {
    this.etablissementService = etablissementService;
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
    // traitement du fichier CSV

    try {
      Reader in = new InputStreamReader(file.getInputStream());
      // "id","Nom","Ville","RNE","Codification","CCF","CCF 2016"
      Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader("id", "Nom", "Ville", "RNE", "Codification", "CCF", "CCF 2016").parse(in);
//      Iterable<CSVRecord> records = CSVFormat.RFC4180.withFirstRecordAsHeader().parse(in);
//      Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().withSkipHeaderRecord().parse();
      int nbLigne = 0;
      for (CSVRecord record : records) {
        nbLigne++;
        // saute la première ligne d'entête si elle existe
        if (nbLigne == 1 && record.get("Ville").equals("Ville") && record.get("Nom").equals("Nom")) continue;

        // une idée pour gérer la ville ?


        EtablissementDto etabDto = new EtablissementDto(null, record.get("Nom"), record.get("RNE"), record.get("Codification"), record.get("CCF"), null);
        // TODO appliquer la validation par injection du Validator
        logger.info("Établissement à importer : " + etabDto);

        etablissementService.saveEtablissementFromEtablissementDto(etabDto);

      }
    } catch (Exception e) {
      redirAttrs.addFlashAttribute("errorMessage",  /*e.getMessage()*/ "Un problème est intervenu, est-ce le bon fichier ?");
      return "redirect:/admin/etablissement/import";
    }
    // ok
    redirAttrs.addFlashAttribute("errorMessage", "Importation en cours d'implémentation !");
    return "redirect:/admin/etablissements";
  }

}
