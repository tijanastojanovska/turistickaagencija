package com.example.turistickaagencija.junit;

import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.repository.LinijaRepository;
import com.example.turistickaagencija.service.DestinacijaService;
import com.example.turistickaagencija.service.KompanijaService;
import com.example.turistickaagencija.service.LinijaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LinijaServiceTest {

    @Autowired
    DestinacijaService destinacijaService;

    @Autowired
    KompanijaService kompanijaService;

    @Autowired
    LinijaService linijaService;

    @Autowired
    LinijaRepository linijaRepository;

    @BeforeAll
    public void init() {
        kompanijaService.create("Kompanija1");
        kompanijaService.create("Kompanija2");
        destinacijaService.create("Skopje", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Belgrade", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Zagreb", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Ljubljana", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Podgorica", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Sarajevo", "Drzava1", 41.21, 21.41);
    }

    @BeforeEach
    public void deleteDatabase() {
        linijaRepository.deleteAll();
    }

    @Test
    public void testCreate() {
        Linija linija = linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        Assert.assertNotNull(linija);
        Assert.assertEquals(1, linijaService.listAllLinii(Long.valueOf(1)).size());
    }

    @Test
    public void testUpdate() {
        Linija linija = linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.update(linija.getId_linija(), LocalDate.of(2022, 11, 11), 999,
                linija.getPocetna().getId_destinacija(), linija.getKrajna().getId_destinacija(),
                linija.getKompanii().stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        Assert.assertEquals(999, linijaService.findById(linija.getId_linija()).getCena(), 0);
    }

    @Test
    public void testDelete() {
        Linija linija = linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.delete(linija.getId_linija());
        Assert.assertEquals(0, linijaService.listAllLinii(Long.valueOf(1)).size());
    }

    @Test
    public void testListAllLinii() {
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(3).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(4).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        Linija linija = linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(4).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        Assert.assertEquals(5, linijaService.listAllLinii(Long.valueOf(1)).size());
        Assert.assertEquals(2, linijaService.listAllLinii(Long.valueOf(2)).size());
    }

    @Test
    public void testFindById() {
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(3).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(4).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        Linija linija = linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(4).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        Assert.assertEquals(linija.getId_linija(), linijaService.findById(linija.getId_linija()).getId_linija());
    }

    @Test
    public void testListByDestinacii() {
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(3).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(4).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(4).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(2).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        linijaService.create(LocalDate.now(), 99,
                destinacijaService.listAll(Long.valueOf(1)).get(1).getId_destinacija(),
                destinacijaService.listAll(Long.valueOf(1)).get(0).getId_destinacija(),
                kompanijaService.listAll(Long.valueOf(1)).stream()
                        .map(kompanija -> kompanija.getId_kompanija()).collect(Collectors.toList()));
        Assert.assertEquals(2, linijaService
                .listLiniiByDestinacii(destinacijaService.listAll(Long.valueOf(1)).get(4).getId_destinacija(),
                        Long.valueOf(1)).size());
    }
}
