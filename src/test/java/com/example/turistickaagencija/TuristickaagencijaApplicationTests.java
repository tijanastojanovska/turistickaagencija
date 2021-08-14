package com.example.turistickaagencija;

import com.example.turistickaagencija.enumerations.Role;
import com.example.turistickaagencija.model.Destinacija;
import com.example.turistickaagencija.model.Kompanija;
import com.example.turistickaagencija.model.Linija;
import com.example.turistickaagencija.service.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TuristickaagencijaApplicationTests {

    MockMvc mockMvc;
    private static boolean dataInitialized = false;
    private static Destinacija destinacija1;
    private static Destinacija destinacija2;
    private static Kompanija kompanija;


    @Autowired
    AdresaService adresaService;

    @Autowired
    AuthService authService;

    @Autowired
    DestinacijaService destinacijaService;

    @Autowired
    KompanijaService kompanijaService;

    @Autowired
    LinijaService linijaService;

    @Autowired
    RezervaciiService rezervaciiService;

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
    }

    @BeforeEach
    public void setup(WebApplicationContext wac) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        initData();
    }

    private void initData() {
        if (!dataInitialized) {
            destinacija1 = destinacijaService.create("Skopje","NMK",41.99646, 21.43141);
            destinacija2 = destinacijaService.create("Belgrade","RS",44.787197, 20.457273);

            kompanija = kompanijaService.create("Kompanija 1");
            kompanijaService.create("Kompanija 2");

            String user = "user";
            String admin = "admin";

            userService.register(user, user, user, user, user, Role.ROLE_USER);
            userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }


    @Test
    public void testGetLinii() throws Exception {
        MockHttpServletRequestBuilder productRequest = MockMvcRequestBuilders.get("/linii/1");
        this.mockMvc.perform(productRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("linii"))
                .andExpect(MockMvcResultMatchers.model().attribute("bodyContent", "listLinii"))
                .andExpect(MockMvcResultMatchers.view().name("master-template"));
    }

    @Test
    public void testDelete() throws Exception {
        List<Long> kompanii = new ArrayList<>();
        kompanii.add(kompanija.getId_kompanija());
        Linija linija = this.linijaService.create(LocalDate.of(2021, 11, 11),100,
                destinacija1.getId_destinacija(),
                destinacija2.getId_destinacija(), kompanii);
        MockHttpServletRequestBuilder linijaDeleteRequest = MockMvcRequestBuilders
                .post("/linii/" + linija.getId_linija() +"/delete/");

        this.mockMvc.perform(linijaDeleteRequest)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/linii/1"));
    }

    @Test
    public void testCreate() throws Exception {
        List<Long> kompanii = new ArrayList<>();
        kompanii.add(kompanija.getId_kompanija());
        Linija linija = this.linijaService.create(LocalDate.of(2021, 11, 11),100,
                destinacija1.getId_destinacija(),
                destinacija2.getId_destinacija(), kompanii);
        MockHttpServletRequestBuilder createLinijaRequest = MockMvcRequestBuilders
                .post("/linii");

        this.mockMvc.perform(createLinijaRequest
                        .param("vreme", linija.getVreme().toString())
                        .param("cena", String.valueOf(linija.getCena()))
                        .param("pocetna", linija.getPocetna().getId_destinacija().toString())
                        .param("krajna", linija.getKrajna().getId_destinacija().toString())
                        .param("kompanii", kompanija.getId_kompanija().toString()))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.redirectedUrl("/linii/1"));
    }

}
