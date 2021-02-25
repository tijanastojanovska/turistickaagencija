package com.example.turistickaagencija.web;

import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Statistika;
import com.example.turistickaagencija.service.AdresaService;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.LinijaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = {"/", "/home"})
public class HomeController {
    private final AdresaService adresaService;
    private final LinijaService linijaService;

    private final DestinacijaService destinacijaService;
    public HomeController(AdresaService adresaService, LinijaService linijaService, DestinacijaService destinacijaService) {
        this.adresaService = adresaService;
        this.linijaService = linijaService;
        this.destinacijaService = destinacijaService;
    }


    @GetMapping
    public String getHomePage(Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        List<Destinacija> destinacii=this.destinacijaService.listAll((long)0);
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        model.addAttribute("destinacii",destinacii);
        model.addAttribute("adresi",adresi);

        model.addAttribute("bodyContent", "home");
        return "master-template";
    }

    @GetMapping("/access_denied")
    public String getAccessDeniedPage(Model model) {
        model.addAttribute("bodyContent", "access_denied");
        return "master-template";
    }

    @GetMapping("/error")
    public String getErrorPage(Model model) {
        return "errorPage";
    }

}
