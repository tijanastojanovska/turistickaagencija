package com.example.turistickaagencija.junit;

import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.repository.DestinacijaRepository;
import com.example.turistickaagencija.service.DestinacijaService;
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
public class DestinacijaServiceTest {

    @Autowired
    DestinacijaService destinacijaService;

    @Autowired
    DestinacijaRepository destinacijaRepository;

    @BeforeEach
    public void empty() {
        destinacijaRepository.deleteAll();
    }

    @Test
    public void testCreate() {
        Destinacija destinacija = destinacijaService.create("Destinacija1", "Drzava1", 41.21, 21.41);
        Assert.assertNotNull(destinacija);
        Assert.assertEquals(1, destinacijaService.listAll(Long.valueOf(1)).size());
    }

    @Test
    public void testDelete() {
        Destinacija destinacija = destinacijaService.create("Destinacija1", "Drzava1", 41.21, 21.41);
        destinacijaService.delete(destinacija.getId_destinacija());
        Assert.assertEquals(0, destinacijaService.listAll(Long.valueOf(1)).size());
    }

    @Test
    public void testUpdate() {
        Destinacija destinacija = destinacijaService.create("Destinacija1", "Drzava1", 41.21, 21.41);
        destinacija = destinacijaService.update(destinacija.getId_destinacija(), destinacija.getIme_destinacija(),
                "Drzava2", destinacija.getLatitude(), destinacija.getLongitude());
        Assert.assertEquals("Drzava2", destinacija.getDrzhava());
    }

    @Test
    public void testFindById() {
        Destinacija destinacija = destinacijaService.create("Destinacija1", "Drzava1", 41.21, 21.41);
        Assert.assertEquals(destinacija.getId_destinacija(), destinacijaService.findById(destinacija.getId_destinacija()).getId_destinacija());
    }

    @Test
    public void testListAllPagination() {
        destinacijaService.create("Skopje", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Belgrade", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Zagreb", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Ljubljana", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Podgorica", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Sarajevo", "Drzava1", 41.21, 21.41);
        Assert.assertEquals(5, destinacijaService.listAll(Long.valueOf(1)).size());
        Assert.assertEquals(1, destinacijaService.listAll(Long.valueOf(2)).size());
    }

    @Test
    public void testListAllByDrzavaPagination() {
        destinacijaService.create("Skopje", "Drzava1", 41.21, 21.41);
        destinacijaService.create("Belgrade", "Drzava2", 41.21, 21.41);
        destinacijaService.create("Zagreb", "Drzava3", 41.21, 21.41);
        destinacijaService.create("Ljubljana", "Drzava4", 41.21, 21.41);
        destinacijaService.create("Podgorica", "Drzava2", 41.21, 21.41);
        destinacijaService.create("Sarajevo", "Drzava6", 41.21, 21.41);
        Assert.assertEquals(2 ,destinacijaService.listAllByDrzhava("Drzava2", Long.valueOf(1)).size());
    }
}
