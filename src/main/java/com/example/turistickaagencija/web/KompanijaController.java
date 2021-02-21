package com.example.turistickaagencija.web;

import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Kompanija;
import com.example.turistickaagencija.service.AdresaService;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.KompanijaService;
import com.example.turistickaagencija.service.LinijaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class KompanijaController {

    private final KompanijaService kompanijaService;
    private final AdresaService adresaService;
private final DestinacijaService destinacijaService;
private final LinijaService linijaService;
    public KompanijaController(KompanijaService kompanijaService, AdresaService adresaService, DestinacijaService destinacijaService, LinijaService linijaService) {
        this.kompanijaService = kompanijaService;
        this.adresaService = adresaService;
        this.destinacijaService = destinacijaService;
        this.linijaService = linijaService;
    }


    @GetMapping("/kompanii")
    public String showKompanii(Model model) {
        List<Kompanija> kompanii;
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Destinacija> destinacii=this.destinacijaService.listAll();
        model.addAttribute("destinacii",destinacii);
            kompanii = this.kompanijaService.listAll();
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);


        model.addAttribute("kompanii", kompanii);
        model.addAttribute("bodyContent", "listKompanii");
        return "master-template";
    }

    @GetMapping("/kompanii/add")
    public String showAdd(Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Destinacija> destinacii=this.destinacijaService.listAll();
        model.addAttribute("destinacii",destinacii);
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        model.addAttribute("bodyContent", "formKompanii");
        return "master-template";

    }

    @GetMapping("/kompanii/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Destinacija> destinacii=this.destinacijaService.listAll();
        model.addAttribute("destinacii",destinacii);
        Kompanija kompanija = this.kompanijaService.findById(id);
        model.addAttribute("kompanija", kompanija);
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        model.addAttribute("bodyContent", "formKompanii");
        return "master-template";

    }

    @PostMapping("/kompanii")
    public String create(@RequestParam String ime_kompanija) {
        this.kompanijaService.create(ime_kompanija);
        return "redirect:/kompanii";
    }

    @PostMapping("/kompanii/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam String ime_kompanija) {
        this.kompanijaService.update(id, ime_kompanija);
        return "redirect:/kompanii";
    }

    @PostMapping("/kompanii/{id}/delete")
    public String delete(@PathVariable Long id) {
        this.kompanijaService.delete(id);
        return "redirect:/kompanii";
    }
}