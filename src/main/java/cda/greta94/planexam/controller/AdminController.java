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

  @PostMapping(value = "/etablissement")
    public String addUpdateEtab(@Valid @ModelAttribute EtablissementDto etablissementDto, BindingResult bindingResult) {
       logger.info("addUpdateEtab : (" + etablissementDto + ")");
       logger.info("hasErrors = "  + bindingResult.hasErrors());

       if(bindingResult.hasErrors()) {
         return "etablissement/form";
       }

       etablissementService.saveEtablissementFromEtablissementDto(etablissementDto);
       return "redirect:/admin/etablissements";
    }

  @GetMapping(value = "/etablissement/edit/{id}")
  public String pushFormEtab(@PathVariable("id") long id, Model model){
    EtablissementDto etablissementDto = etablissementService.findEtablissementDtoById(id);
    model.addAttribute("etablissementDto", etablissementDto);
    return "etablissement/form";
  }

  @GetMapping(value = "/etablissement/import")
  public String formImportCSV(){
    return "/etablissement/formImportCSV";
  }

  @PostMapping(value = "/etablissement/import")
  public String importCSV(@RequestParam("file") MultipartFile file,  RedirectAttributes redirAttrs){

    if (file.isEmpty()) {
      // PATTERN PRG
      redirAttrs.addFlashAttribute("errorMessage", "Please select a file to upload");
      return "redirect:/admin/etablissement/import";
    }
    // traitement du fichier CSV
    Reader in = null;
    try {
//      in = new FileReader();
      Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(new InputStreamReader(file.getInputStream()));
      for (CSVRecord record : records) {
        // pour chaque enregistrement
        // Création d'un objet EtablissementDto // pour la ville voir en second
        // vérifier que l'obbet n'est pas dans la base de données (utiliser une des clés candidates)
        // si non présent faire un save

        // TODO logique de l'impotr à implémenter dans une classe service
        
        String columnOne = record.get(0);
        String columnTwo = record.get(1);
        logger.info("C0 :" + columnOne);
        logger.info("C1 :" + columnTwo);
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    redirAttrs.addFlashAttribute("errorMessage", "Importation à implémenter !");
    return "redirect:/admin/etablissements";
  }

}
