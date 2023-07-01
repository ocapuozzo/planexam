package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.SessionE5Dto;
import cda.greta94.planexam.service.SessionService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.time.LocalDate;

@Controller
@RequestMapping("/admin")
public class AdminSessionController {
    private SessionService sessionService;

    public AdminSessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/session")
    private String index(Model model){
        model.addAttribute("lesSessions",sessionService.getAll());
        return "admin/session/index";
    }

    @GetMapping("/session/create")
    private  String create(@ModelAttribute SessionE5Dto sessionE5Dto){
        LocalDate current_date = LocalDate.now();
        int annee = current_date.getYear();
        sessionE5Dto.setLibelle("BTS SIO E5 "+annee);
        return "admin/session/create";
    }

    @PostMapping("/session")
    private String add(@Valid @ModelAttribute SessionE5Dto sessionE5Dto, BindingResult bindingResult, RedirectAttributes redirAttrs){
        if(bindingResult.hasErrors()){
          return "admin/session/create";

        }
    sessionService.save(sessionE5Dto);
    return "redirect:/admin/session";
    }
}
