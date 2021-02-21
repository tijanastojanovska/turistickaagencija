package com.example.turistickaagencija.web;


import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.service.AdresaService;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.LinijaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class DestinacijaController {

    private final DestinacijaService destinacijaService;
    private final AdresaService adresaService;
    private final LinijaService linijaService;


    public DestinacijaController(DestinacijaService destinacijaService, AdresaService adresaService, LinijaService linijaService) {
        this.destinacijaService = destinacijaService;
        this.adresaService = adresaService;
        this.linijaService = linijaService;
    }


    @GetMapping("/destinacii")
    public String showDestinacii(@RequestParam(required = false) String name, Model model) {
        List<Destinacija> destinacii;
        List<Adresa> adresi=this.adresaService.listAll();List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        model.addAttribute("adresi",adresi);
       if (name == null) {
            destinacii= this.destinacijaService.listAll();
       } else {
            destinacii= this.destinacijaService.listAllByDrzhava(name);
        }

        model.addAttribute("destinacii",destinacii);
        model.addAttribute("bodyContent","listDestinacii");
        return "master-template";
    }
    @GetMapping("/destinacii/add")
    public String showAdd(Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        model.addAttribute("adresi",adresi);
        model.addAttribute("bodyContent","formDestinacii");
        return "master-template";

    }
    @GetMapping("/destinacii/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        Destinacija destinacija = this.destinacijaService.findById(id);
        model.addAttribute("destinacija",destinacija);
        model.addAttribute("bodyContent","formDestinacii");
        return "master-template";

    }

    @PostMapping("/destinacii")
    public String create(@RequestParam String ime_destinacija, @RequestParam String drzhava
            ,@RequestParam  Double latitude,@RequestParam Double longitude){
        this.destinacijaService.create(ime_destinacija,drzhava,latitude,longitude);
        return "redirect:/destinacii";
    }
    @PostMapping("/destinacii/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String ime_destinacija, @RequestParam String drzhava
            ,@RequestParam  Double latitude,@RequestParam Double longitude) {
        this.destinacijaService.update(id,ime_destinacija,drzhava,latitude,longitude);
        return "redirect:/destinacii";
    }
    @PostMapping("/destinacii/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.destinacijaService.delete(id);
        return "redirect:/destinacii";
    }
}
