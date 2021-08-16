package com.example.turistickaagencija;

import com.example.turistickaagencija.model.Adresa;
import com.example.turistickaagencija.service.AdresaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class AdresaServiceTest {

    @Autowired
    private AdresaService adresaService;


    @Test
    public void testService() {
        Adresa adresa1 = adresaService.create("Adresa1", Long.valueOf(41), Long.valueOf(21));
        Adresa adresa2 = adresaService.create("Adresa2", Long.valueOf(41), Long.valueOf(21));
        List<Adresa> lista = new ArrayList<>();
        lista.add(adresa1);
        lista.add(adresa2);
        Assert.assertEquals(adresaService.listAll().size(), lista.size());
    }
}
