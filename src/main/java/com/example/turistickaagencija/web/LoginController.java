package com.example.turistickaagencija.web;

import com.example.turistickaagencija.exceptions.InvalidUserCredentialsException;
import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.User;
import com.example.turistickaagencija.service.AdresaService;
import com.example.turistickaagencija.service.AuthService;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.LinijaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final AuthService authService;
    private final AdresaService adresaService;
private final DestinacijaService destinacijaService;
private final LinijaService linijaService;

    public LoginController(AuthService authService, AdresaService adresaService, DestinacijaService destinacijaService, LinijaService linijaService) {
        this.authService = authService;
        this.adresaService = adresaService;
        this.destinacijaService = destinacijaService;
        this.linijaService = linijaService;
    }

    @GetMapping
    public String getLoginPage(Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        List<Destinacija> destinacii=this.destinacijaService.listAll((long)0);
        model.addAttribute("destinacii",destinacii);
        model.addAttribute("bodyContent","login");
        return "master-template";
    }

    @PostMapping
    public String login(HttpServletRequest request, Model model) {
        User user = null;
        try{
            user = this.authService.login(request.getParameter("username"),
                    request.getParameter("password"));
            request.getSession().setAttribute("user", user);
            return "redirect:/home";
        }
        catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }
}
