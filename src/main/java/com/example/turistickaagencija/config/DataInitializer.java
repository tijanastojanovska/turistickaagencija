package com.example.turistickaagencija.config;

import com.example.turistickaagencija.enumerations.Role;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.KompanijaService;
import com.example.turistickaagencija.service.LinijaService;
import com.example.turistickaagencija.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Component
public class DataInitializer {

    private final UserService userService;
    private final KompanijaService kompanijaService;
    private final DestinacijaService destinacijaService;
    private final LinijaService linijaService;

    public DataInitializer(UserService userService, KompanijaService kompanijaService, DestinacijaService destinacijaService, LinijaService linijaService) {
        this.userService = userService;
        this.kompanijaService = kompanijaService;
        this.destinacijaService = destinacijaService;
        this.linijaService = linijaService;
    }

    @PostConstruct
    public void initData() {
//        this.userService.register("admin", "admin", "admin", "admin", "admin", Role.ROLE_ADMIN);
//        this.userService.register("user", "user", "user", "user", "user", Role.ROLE_USER);
//        this.kompanijaService.create("Kompanija 1");
//        this.kompanijaService.create("Kompanija 2");
//        this.destinacijaService.create("Skopje", "Drzava1", 41.21, 11.41);
//        this.destinacijaService.create("Belgrade", "Drzava1", 31.21, 21.41);
//        this.destinacijaService.create("Zagreb", "Drzava1", 11.21, 31.41);
//        this.destinacijaService.create("Ljubljana", "Drzava1", 51.21, 41.41);
//        this.destinacijaService.create("Podgorica", "Drzava1", 61.21, 51.41);
//        this.destinacijaService.create("Sarajevo", "Drzava1", 71.21, 61.41);
//        this.linijaService.create(LocalDate.now(), 99,
//                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
//                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
//                kompanijaService.listAll(Long.valueOf(1)).stream()
//                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
//        linijaService.create(LocalDate.now(), 99,
//                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
//                destinacijaService.listAll(Long.valueOf(1)).get(3).getId_destinacija(),
//                kompanijaService.listAll(Long.valueOf(0)).stream()
//                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
//
    }
}
