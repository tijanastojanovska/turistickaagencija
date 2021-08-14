package com.example.turistickaagencija.web;

import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Kompanija;
import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.service.AdresaService;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.KompanijaService;
import com.example.turistickaagencija.service.LinijaService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class LinijaController {
    private final LinijaService service;
    private final DestinacijaService destinacijaService;
    private final KompanijaService kompanijaService;
    private final AdresaService adresaService;

    public LinijaController(LinijaService service, DestinacijaService destinacijaService, KompanijaService kompanijaService, AdresaService adresaService) {
        this.service = service;
        this.destinacijaService = destinacijaService;
        this.kompanijaService = kompanijaService;
        this.adresaService = adresaService;
    }



    @GetMapping("/linii/{page}")
    public String showLinii(@PathVariable(required = false) Long page, @RequestParam(required = false) Long destinacijaId, Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);List<Long> statistiki = this.service.listCompanyStatisics();
        List<String> br = this.service.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);

        List<Linija> linii;
        Long brojNaLinii;
        List<Kompanija> kompanii=this.kompanijaService.listAll((long)0);
        List<Destinacija> destinacii=this.destinacijaService.listAll((long) 0);

            if (destinacijaId == null) {
                linii= this.service.listAllLinii(page);
                brojNaLinii= (long)this.service.listAllLinii((long) 0).size();

            } else {
                linii= this.service.listLiniiByDestinacii(destinacijaId, page);
                brojNaLinii= (long)this.service.listLiniiByDestinacii(destinacijaId, (long) 0).size();
            }

        model.addAttribute("linii",linii);
        model.addAttribute("brojNaLinii",brojNaLinii);
        model.addAttribute("kompanii",kompanii);
        model.addAttribute("destinacii",destinacii);
        model.addAttribute("bodyContent","listLinii");
        return "master-template";
    }
    @GetMapping("/linii/add")
    public String showAdd(Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Long> statistiki = this.service.listCompanyStatisics();
        List<String> br = this.service.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        List<Destinacija> destinacii=this.destinacijaService.listAll((long)0);
        List<Kompanija> kompanii=this.kompanijaService.listAll((long)0);
        model.addAttribute("kompanii",kompanii);
        model.addAttribute("destinacii",destinacii);
        model.addAttribute("bodyContent","addLinii");
        return "master-template";

    }
    @GetMapping("/linii/{id}/edit")
    public String showEdit(@PathVariable Long id, Model model) {
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Long> statistiki = this.service.listCompanyStatisics();
        List<String> br = this.service.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        Linija linija = this.service.findById(id);
        List<Destinacija> destinacii=this.destinacijaService.listAll((long)0);
        List<Kompanija> kompanii=this.kompanijaService.listAll((long)0);
        model.addAttribute("kompanii",kompanii);
        model.addAttribute("destinacii",destinacii);
        model.addAttribute("linija",linija);
        model.addAttribute("bodyContent","editLinii");
        return "master-template";

    }

    @PostMapping("/linii")
    public String create(@RequestParam (required = false) @DateTimeFormat (pattern="yyyy-MM-dd") LocalDate vreme,
                         @RequestParam float cena,
                         @RequestParam Long pocetna,
                         @RequestParam Long krajna,
                         @RequestParam List<Long> kompanii,
                         @PathVariable(required = false) Long page) {
        this.service.create(vreme, cena, pocetna, krajna, kompanii);
        return "redirect:/linii/1";
    }
    @PostMapping("/linii/{id}")
    public String update(@PathVariable Long id,
                         @RequestParam @DateTimeFormat (pattern="yyyy-MM-dd") LocalDate  vreme,
                         @RequestParam float cena, @RequestParam Long pocetna,
                         @RequestParam Long krajna, @RequestParam List<Long> kompanii,
                         @PathVariable(required = false) Long page) {
        this.service.update(id,vreme,cena,pocetna,krajna,kompanii);
        return "redirect:/linii/1";
    }
    @PostMapping("/linii/{id}/delete")
    public String delete(@PathVariable Long id, @PathVariable(required = false) Long page) {
        try{
            this.service.delete(id);
            return "redirect:/linii/1";
        }
        catch(Exception e){
            return "errorPage.html";
        }
    }
}
