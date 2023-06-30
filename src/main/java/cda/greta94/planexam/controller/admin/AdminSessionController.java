package cda.greta94.planexam.controller.admin;

import cda.greta94.planexam.dto.SessionE5Dto;
import cda.greta94.planexam.service.SessionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    return "admin/session/create";
    }

    @PostMapping("/session")
    private String add(@ModelAttribute SessionE5Dto sessionE5Dto){
    sessionService.save(sessionE5Dto);
    return "redirect:/admin/session";
    }
}
