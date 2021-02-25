package com.example.turistickaagencija.web;

import com.example.turistickaagencija.enumerations.Role;
import com.example.turistickaagencija.exceptions.InvalidArgumentsException;
import com.example.turistickaagencija.exceptions.PasswordsDoNotMatchException;
import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;
    private final AdresaService adresaService;
private  final DestinacijaService destinacijaService;
private final LinijaService linijaService;
    public RegisterController(AuthService authService, UserService userService, AdresaService adresaService, DestinacijaService destinacijaService, LinijaService linijaService) {
        this.authService = authService;
        this.userService = userService;
        this.adresaService = adresaService;
        this.destinacijaService = destinacijaService;
        this.linijaService = linijaService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Destinacija> destinacii=this.destinacijaService.listAll((long)0);
        model.addAttribute("destinacii",destinacii);
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String ime,
                           @RequestParam String prezime,
                           @RequestParam Role role) {
        try{
            this.userService.register(username, password, repeatedPassword, ime, prezime, role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}

