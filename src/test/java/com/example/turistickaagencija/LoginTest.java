package com.example.turistickaagencija;

import com.example.turistickaagencija.seleniumClasses.LiniiPage;
import com.example.turistickaagencija.seleniumClasses.LoginPage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    private WebDriver driver;

    @BeforeTest
    public void setup() {
        driver = getDriver();
    }


    @Test
    //dali ja prikazuva login
    public void OpenPage() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
    }

    private void assertTrue(boolean loaded) {
    }
    //vnes na nevalidni podatoci
    @Test
    public void NevalidniPodatoci() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("nepostoecki", "korisnik");
        String errorMessage = loginPage.getErrorMessage();
        assertEquals(errorMessage, "BadCredentials");

    }
    //ne vnesuvam podatoci
    @Test
    public void NevneseniPodatoci() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("", "");
        assertTrue(loginPage.isLoaded());
    }
    //vnes na validni podatoci
    @Test
    public void UspeshenLogin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        assertTrue(loginPage.isLoaded());
        loginPage.login("tijana", "Test123!");
        assertTrue(new LiniiPage(driver).isLoaded());

    }



    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/stoja/OneDrive/Documents/GitHub/turistickaagencija/src/main/resources/drivers/chromedriver.exe");
        return new ChromeDriver();
    }

    @AfterTest
    public void teardown() {
        driver.quit();
    }

}
