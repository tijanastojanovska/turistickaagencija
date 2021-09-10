package com.example.turistickaagencija.junit;

import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Kompanija;
import com.example.turistickaagencija.repository.KompanijaRepository;
import com.example.turistickaagencija.service.KompanijaService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class KompanijaServiceTest {

    @Autowired
    KompanijaService kompanijaService;

    @Autowired
    KompanijaRepository kompanijaRepository;

    @BeforeEach
    public void init() {
        kompanijaRepository.deleteAll();
    }

    @Test
    public void testCreate() {
        Kompanija kompanija = kompanijaService.create("Kompanija");
        Assert.assertNotNull(kompanija);
        Assert.assertEquals(1, kompanijaService.listAll(Long.valueOf(1)).size());
    }

    @Test
    public void testDelete() {
        Kompanija kompanija = kompanijaService.create("Kompanija");
        kompanijaService.delete(kompanija.getId_kompanija());
        Assert.assertEquals(0, kompanijaService.listAll(Long.valueOf(1)).size());
    }

    @Test
    public void testUpdate() {
        Kompanija kompanija = kompanijaService.create("Kompanija");
        kompanija = kompanijaService.update(kompanija.getId_kompanija(), "Druga kompanija");
        Assert.assertEquals("Druga kompanija", kompanija.getIme_kompanija());
    }

    @Test
    public void testListAll() {
        kompanijaService.create("Kompanija1");
        kompanijaService.create("Kompanija2");
        kompanijaService.create("Kompanija3");
        kompanijaService.create("Kompanija4");
        kompanijaService.create("Kompanija5");
        kompanijaService.create("Kompanija6");
        kompanijaService.create("Kompanija7");
        kompanijaService.create("Kompanija8");
        Assert.assertEquals(5, kompanijaService.listAll(Long.valueOf(1)).size());
        Assert.assertEquals(3, kompanijaService.listAll(Long.valueOf(2)).size());
    }
}
