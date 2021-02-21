package com.example.turistickaagencija.web;

import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Rezervacii;
import com.example.turistickaagencija.model.User;
import com.example.turistickaagencija.service.AdresaService;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.LinijaService;
import com.example.turistickaagencija.service.RezervaciiService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/rezervacii")
public class RezervaciiController {

    private final RezervaciiService rezervaciiService;
    private final AdresaService adresaService;
    private final DestinacijaService destinacijaService;
    private final LinijaService linijaService;

    public RezervaciiController(RezervaciiService rezervaciiService, AdresaService adresaService, DestinacijaService destinacijaService, LinijaService linijaService) {
        this.rezervaciiService = rezervaciiService;
        this.adresaService = adresaService;
        this.destinacijaService = destinacijaService;
        this.linijaService = linijaService;
    }


    @GetMapping
    public String getRezervacii(@RequestParam(required = false) String error,
                                HttpServletRequest req,
                                Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = req.getRemoteUser();
        List<Adresa> adresi=this.adresaService.listAll();
        model.addAttribute("adresi",adresi);
        List<Destinacija> destinacii=this.destinacijaService.listAll();
        model.addAttribute("destinacii",destinacii);
        Rezervacii rezervacii = this.rezervaciiService.getActiveRezervacii(username);
        model.addAttribute("linii", this.rezervaciiService.listAllReservations(rezervacii.getId()));
        List<Long> statistiki = this.linijaService.listCompanyStatisics();
        List<String> br = this.linijaService.listCompanies();
        model.addAttribute("statistiki",statistiki);
        model.addAttribute("br",br);
        model.addAttribute("bodyContent", "rezervacii");
        return "master-template";
    }

    @PostMapping("/add-linija/{id}")
    public String createReservation(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.rezervaciiService.createRezervacija(user.getUsername(), id);
            return "redirect:/rezervacii";
        } catch (RuntimeException exception) {
            return "redirect:/rezervacii?error=" + exception.getMessage();
        }
    }
}

